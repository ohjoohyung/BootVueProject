package com.example.demo.config.security;

//Jwt 토큰 생성 및 유효성 검증을 하는 컴포넌트

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private  String secretKey;

    private long tokenVaildMilisecond = 1000L * 30 * 60; //30분만 토큰 유효

    private final UserDetailsService userDetailsService;

    //객체 초기화, secretKey를 Base64로 인코딩
   @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //Jwt 토큰 생성
    public String createToken(String userPk, String userName, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userPk); //Jwt payload에 저장되는 정보 단위
        claims.put("roles", roles); //정보는 key, value 쌍으로 저장
        claims.put("name", userName);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) //데이터
                .setIssuedAt(now) //토큰 발행일자
                .setExpiration(new Date(now.getTime() + tokenVaildMilisecond)) //토큰 만료일자
                .signWith(SignatureAlgorithm.HS256, secretKey) //암호화 알고리즘, Jwt signature에 들어갈 secret값 세팅
                .compact();
   }

   //Jwt 토큰으로 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //Jwt 토큰에서 회원 구별 정보 추출
    public  String getUserPk(String token) {
       return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().getSubject();
    }

    //Request의 Header에서 token 파싱 : "X-AUTH-TOKEN: jwt 토큰"
    public String resolveToken(HttpServletRequest request) {
       return request.getHeader("X-AUTH-TOKEN");
    }

    //Jwt 토큰의 유효성 + 만료 일자 확인
    public boolean validateToken(String jwtToken) {
       try {
           Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
           return !claims.getBody().getExpiration().before(new Date());
       } catch (Exception e) {
           return false;
       }
    }
}
