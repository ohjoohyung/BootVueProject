package com.example.demo.entitiy;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String userName;

    private String title;

    @Lob //varchar보다 큰 데이터를 담을 때 사용
    private String content;

    @CreationTimestamp
    private LocalDate writeDate;

    @UpdateTimestamp
    private LocalDate modifyDate;
    
    @Builder
    public Board(String userName, String title, String content) {
        this.setUserName(userName);
        this.setTitle(title);
        this.setContent(content);
    }

}
