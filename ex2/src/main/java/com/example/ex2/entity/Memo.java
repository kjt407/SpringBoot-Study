package com.example.ex2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tbl_memo")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {
    @Id // Primaty Key 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MariaDB autoincrease
    private Long mno;

    @Column(length = 200, nullable = false) //memeText 문자열 길이와 null가능 여부 지정
    private String memoText;
}

