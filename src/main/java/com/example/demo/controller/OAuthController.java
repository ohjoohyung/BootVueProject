package com.example.demo.controller;

import com.example.demo.config.security.JwtTokenProvider;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

@RequestMapping("/api")
@Controller
public class OAuthController {

    @Value("${naverClientSecret}")
    private String naverClientSecret;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    //네이버 로그인
    @GetMapping("/naver/login")
    public String naverLogin(@RequestParam(value = "code") String code,
                             @RequestParam(value = "state") String state) {
        String clientId = "7mm5SCA3KFMUmaEjph6u";
        String apiURL;


        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=" + clientId;
        apiURL += "&client_secret=" + naverClientSecret;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;
        String access_token = "";
        String refresh_token = "";
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection(); //웹을 통해 데이터를 주고받을 때 사용
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode == 200) { //정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }else { //에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            if(responseCode == 200) { //성공적으로 토큰을 가져온다면
                int id;
                String email, name, tmp;
                JsonParser parser = new JsonParser();
                JsonElement accessElement = parser.parse(res.toString());
                access_token = accessElement.getAsJsonObject().get("access_token").getAsString();
                tmp = getUserInfo(access_token);
                JsonElement userInfoElement = parser.parse(tmp);
                email = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();
                name = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("name").getAsString();

                List<String> list = new ArrayList<>();
                list.add("ROLE_USER");
                access_token = jwtTokenProvider.createToken(email, name, list);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:http://localhost:8081/agreement?token=" + access_token;

    }

    private String getUserInfo(String access_token) throws IOException {
        String header = "Bearer " + access_token;
        try {
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode == 200) { //정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }else { //에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            return res.toString();
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
    }
}
