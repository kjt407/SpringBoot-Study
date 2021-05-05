package com.example.ex2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tbl_memo")
@ToString
@Getter // Lombok 의존성을 사용할경우 Gettter Setter toString 과 같은 메서드를 어노테이션 하나만으로 끝내버릴 수 있다
@Builder // Builder 어노테이션을 사용할 경우 번거롭게 setter 또는 생성자를 통해서 값을 주입시킬 필요가 없다
@AllArgsConstructor
@NoArgsConstructor
public class Memo {
    @Id // Primaty Key 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MariaDB autoincrease - IDENTITY일 사용하는 데이터베이스가 키생성을 결정
    private Long mno;

    @Column(length = 200, nullable = false, columnDefinition = "기본값으로 설정된 텍스트입니다") //memeText 문자열 길이와 null가능 여부 지정
    private String memoText;
}

