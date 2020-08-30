package com.example.demo.entitiy;


import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="user")
public class User {

    @Id
    private String userId;

    private String userPwd;

    private String userName;

    @Builder
    public User(String userId, String userPwd, String userName) {
        this.setUserId(userId);
        this.setUserPwd(userPwd);
        this.setUserName(userName);
    }
}
