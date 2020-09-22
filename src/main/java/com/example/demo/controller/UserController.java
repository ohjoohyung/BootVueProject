package com.example.demo.controller;

import com.example.demo.config.security.JwtTokenProvider;
import com.example.demo.entitiy.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class UserController {

/*    @Autowired
    private UserService userService;

    @PostMapping("/join")
    public User join(@RequestBody User user) {
        return userService.insertUser(user);
    }*/

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    //회원가입
    @PostMapping("/join")
    public Long join(@RequestBody Map<String, String> user) {
        return userRepository.save(User.builder()
                .email(user.get("email"))
                .name(user.get("name"))
                .password(passwordEncoder.encode(user.get("password")))
                .roles(Collections.singletonList("ROLE_USER")) //최초 가입시 USER로 설정
                .build()).getId();
    }

    //로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) { ;
        User member = userRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getName(), member.getRoles());
    }
}
