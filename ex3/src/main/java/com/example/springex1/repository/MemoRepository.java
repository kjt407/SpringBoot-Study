package com.example.springex1.repository;

import com.example.springex1.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memo,Long> {
    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);
    void deleteMemoByMnoGreaterThan(Long num);

    @Query("select m from Memo m order by  m.mno desc")
    List<Memo> getListDesc();

//    : 방식으로 파라미터 바인딩
//    @Transactional
//    @Modifying
//    @Query("update Memo m set m.memoText = :memoText where m.mno = :mno")
//    int updateMemoText(@Param("mno") Long mno, @Param("memoText") String memoText);

//    :#{#} 방식의 자바빈 방식 바인딩
    @Transactional
    @Modifying
    @Query("update Memo m set m.memoText = :#{#param.memoText} where m.mno = :#{#param.mno}")
    int updateMemoText(@Param("param") Memo memo);

//   어노테이션 쿼리의 페이징 처리
    @Query(value = "select m from Memo m where m.mno > :mno",
            countQuery = "select count(m) from Memo m where m.mno > :mno"
    )
    Page<Memo> getListWithQuery(Long mno, Pageable pageable);

//   어노테이션 Object 반환 (원하는 결과값만 골라서 반환 가능) 해당 타입에 맞는 자료형으로 자동 반환됨
    @Query("select m.mno, current_date from Memo m where m.mno > :mno")
    List<Object[]> getObjectQuery(Long mno);

}
