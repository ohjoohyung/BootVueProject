package com.example.demo.entitiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;

    private String userName;

    @CreationTimestamp
    private LocalDate writeDate;

    @UpdateTimestamp
    private LocalDate modifyDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")
    private Board board;

    /*@ManyToOne
    @JoinColumn(name = "id")
    private User user;
*/
}
