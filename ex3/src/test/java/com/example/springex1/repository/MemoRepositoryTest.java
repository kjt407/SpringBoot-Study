package com.example.springex1.repository;

import com.example.springex1.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

//    동적 proxy 이름이 출력되는것을 확인가능
//    @Test
//    public void testClass(){
//        System.out.println(memoRepository.getClass().getName());
//    }
    
//    삽입작업 테스트
//    @Test
//    public void testInsertDummies(){
//
//        IntStream.rangeClosed(1,10).forEach(i -> {
//            Memo memo = Memo.builder().memoText("sample..."+i).build();
//            memoRepository.save(memo);
//        });
//    }

//    조회작업 테스트 getOne / findById 실행주기의 차이
//    @Test
//    public void testSelectDummies(){
//        Long mno = 100L;
//
//        Optional<Memo> result = memoRepository.findById(mno);
//
//        System.out.println("------------------------------------");
//
//        if(result.isPresent()){
//            Memo memo = result.get();
//            System.out.println("memo text :"+memo.getMemoText());
//        }
//    }

//    수정작업 테스트 insert와 동일 / 해당 entity가 존재할 경우에 update
//    @Test
//    public void testUpdateDummies(){
//        LongStream.rangeClosed(1L,100L).forEach(l -> {
//            Memo memo = Memo.builder().mno(l).memoText("수정된 텍스트 입니다 ㅡㅡ").build();
//            System.out.println(memoRepository.save(memo));
//        });
////        Memo memo = Memo.builder().mno(211L).memoText("수정된 텍스트 입니다 ㅡㅡ").build();
////        System.out.println(memoRepository.save(memo));
//    }

//    삭제작업 테스트 삭제할 항목이 없다면 Exception을 발생시킴
//    @Test
//    public void testUpdateDummies(){
//        try {
//            LongStream.rangeClosed(1L, 100L).forEach(l -> {
//                memoRepository.deleteById(l);
//            });
//        } catch (Exception e){
//            System.out.println("예외발생"+e);
//        }
//
//        List<Memo> results = memoRepository.findAll();
//
//        results.forEach(memo -> {
//            System.out.println("번호: "+memo.getMno()+"/ 내용: "+memo.getMemoText());
//        });
//    }

//    페이징 처리
//    @Test
//    public void testPageDefault(){
//
//        Pageable pageable = PageRequest.of(0,10);
//
//        Page<Memo> results = memoRepository.findAll(pageable);
//
//        System.out.println("Totalpages: "+results.getTotalPages());
//        System.out.println("TotalElements: "+results.getTotalElements());
//        System.out.println("PageNumber: "+results.getNumber());
//        System.out.println("페이지당 데이터 개수: "+results.getSize());
//        System.out.println("다음페이지 : "+results.hasNext());
////        results.forEach(memo -> {
////            System.out.println("번호: "+memo.getMno()+"/ 내용: "+memo.getMemoText());
////        });
//    }

//    쿼리메서드 사용법
//    @Test
//    public void testPageDefault(){
//
//        Sort sort = Sort.by("mno").descending();
//        Pageable pageable = PageRequest.of(0,10, sort);
//
//        Page<Memo> results = memoRepository.findByMnoBetween(200L,250L,pageable);
//        System.out.println("Totalpages: "+results.getTotalPages());
//        System.out.println("TotalElements: "+results.getTotalElements());
//        System.out.println("PageNumber: "+results.getNumber());
//        System.out.println("페이지당 데이터 개수: "+results.getSize());
//        System.out.println("다음페이지 : "+results.hasNext());
//
//
//        results.forEach(memo -> {
//            System.out.println("번호: "+memo.getMno()+"/ 내용: "+memo.getMemoText());
//        });
//    }

//    삭제작업 쿼리메서드
//    @Transactional
//    @Commit
//    @Test
//    public void testPageDefault(){
//        memoRepository.deleteMemoByMnoGreaterThan(250L);
//    }

//    @Test
//    public void testPageDefault(){
//        IntStream.rangeClosed(1,100).forEach(i->{
//            Memo memo = Memo.builder().memoText(i+"번째 필드").build();
//            memoRepository.save(memo);
//        });
//    }

////    쿼리 어노테이션 테스트 @Query 조회
//    @Test
//    public void testAnnotation(){
//        System.out.println(memoRepository.getListDesc());
//    }

//    쿼리 어노테이션 테스트 @Query 파라미터 바인딩
//    @Test
//    public void testAnnotation(){
////        System.out.println(memoRepository.updateMemoText(213L, "변경된 텍스트입니다"));
//        System.out.println(memoRepository.updateMemoText(Memo.builder().mno(213L).memoText("#빈즈 바인딩").build()));
//        // 결과값으로 1 or 0 을 반환하여 성공 실패여부를 알려줌
//    }

//    쿼리 어노테이션 테스트 @Query 조회
//    @Test
//    public void testAnnotation(){
//        Sort sort = Sort.by("mno").descending();
//        Page<Memo> results = memoRepository.getListWithQuery(220L, PageRequest.of(0,10, sort));
//
//        results.forEach(memo -> {
//            System.out.println(memo.getMno()+" / "+memo.getMemoText());
//        });
//    }


//    쿼리 어노테이션 테스트 Object 반환형 지정 (원한는 값만 불러오기)
    @Test
    public void testAnnotation(){
        List<Object[]> results = memoRepository.getObjectQuery(220L);

        for (Object[] row : results) {
            Long mno = (Long) row[0];
            Date date = (Date) row[1];

            System.out.println("번호: "+mno+" / 날짜: "+date);
        }
    }
}
