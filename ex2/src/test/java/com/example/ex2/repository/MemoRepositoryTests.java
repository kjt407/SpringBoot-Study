package com.example.ex2.repository;

import com.example.ex2.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests{

    @Autowired
    MemoRepository memoRepository;


//    @Test
//    public void testInsertDummies(){
////        IntStream.rangeClosed(1,100).forEach(i->{
//        Memo memo = Memo.builder().memoText("builder를 이용한 값 주입").build();
//        memoRepository.save(memo);
//        memoRepository.findAll();
////        });
//    }


//    @Test
//    public void testSelect(){
//        Long mno = 402L;
//
//        Optional<Memo> result=  memoRepository.findById(mno);
//        memoRepository.findAll();
//
//        if(result.isPresent()){
//            Memo memo = result.get();
//            System.out.println(memo);
//        }
//    }


//    @Test
//    public void testUpdate(){
//        Memo memo = Memo.builder().mno(403L).memoText("새로 업데이트한 내용이야").build();
//        memoRepository.save(memo);
//    }


    @Test
    public void testDelete(){
        memoRepository.deleteById(404L);
        System.out.println("404번 ROW가 지워졌을까요? : "+memoRepository.findAll());
    }

}
