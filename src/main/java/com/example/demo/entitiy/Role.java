package com.example.demo.entitiy;

import lombok.*;

import javax.persistence.*;

@Table(name = "roles")
@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private  ERole name;
}
