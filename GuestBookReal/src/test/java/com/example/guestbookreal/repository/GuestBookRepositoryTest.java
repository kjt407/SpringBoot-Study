package com.example.guestbookreal.repository;

import com.example.guestbookreal.entity.Guestbook;
import com.example.guestbookreal.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;

@SpringBootTest
public class GuestBookRepositoryTest {

    @Autowired
    private GuestBookRepository guestBookRepository;

//    @Test
//    public void insertDummies(){
//
//        IntStream.rangeClosed(1,300).forEach(i->{
//            Guestbook guestBook = Guestbook.builder().title("제목 "+i).content("내용 "+i).writer("쫑태").build();
//            System.out.println(guestBookRepository.save(guestBook));
//        });
//    }

//    @Test
//    public void changeDummies(){
//        Optional<GuestBook> result = repository.findById(300L);
//
//        if(result.isPresent()){
//            GuestBook guestBook = result.get();
//            guestBook.changeTitle("변경된 제목");
//            guestBook.changeContent("변경된 내용");
//
//            repository.save(guestBook);
//        }
//    }

    @Test
    public void testQuery1(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());

        QGuestbook qGuestBook = QGuestbook.guestbook;

        String keyword = "제목";

        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = qGuestBook.title.contains(keyword);
        BooleanExpression expression2 = qGuestBook.content.contains("1");
        BooleanExpression expression_all = expression.or(expression2);

        builder.and(expression_all);
        builder.and(qGuestBook.gno.lt(100));

        Page<Guestbook> result = guestBookRepository.findAll(builder,pageable);

        System.out.println(result.getTotalElements());
        result.stream().forEach(guestbook ->{
            System.out.println(guestbook+"adsfasdf");
        });

    }
}
