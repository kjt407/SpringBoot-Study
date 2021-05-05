package com.example.ex2.repository;

import com.example.ex2.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository 인터페이스 정의
public interface MemoRepository extends JpaRepository<Memo,Long>/*<Entity 클래스타입,@ID 자료형>*/ {
    // JpaRepository를 생성하는 것만으로도 모든작업은 끝!
    // 해당 인터페이스를 선언 하는것 만으로도 자동으로 Spring Bean이 등록됨
}
