package com.example.demo.entitiy;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//Spring security는 UserDetails 객체를 통해 권한 정보를 관리하기 때문에
//UserDetails를 구현하고 추가 정보 재정의해야 함
@Builder //builder 사용할 수 있게함
@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor //파라미터 없는 생성자
@AllArgsConstructor //파라미터 모두 있는 생성자
@Table(name = "user") //user테이블과 매핑
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 300, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String name;

    //권한은 여러 개 세팅될 수 있으니 Collection으로 선언
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    //security에서 사용하는 회원 구분 id
    @Override
    public String getUsername() {
        return email;
    }

    //계정이 만료되지 않았는지
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠기지 않았는지
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //계정 패스워드가 만료되지 않았는지
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 사용 가능한지
    @Override
    public  boolean isEnabled() {
        return true;
    }


}