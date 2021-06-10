package com.example.guestbook2.repository;

import com.querydsl.core.BooleanBuilder;
import com.example.guestbook2.entity.GuestBook;
import com.example.guestbook2.entity.QGuestBook;
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
    private GuestBookRepository repository;
    
//    @Test
//    public void insertDummies(){
//
//        IntStream.rangeClosed(1,300).forEach(i->{
//            GuestBook guestBook = GuestBook.builder().title("제목 "+i).content("내용 "+i).writer("쫑태").build();
//            System.out.println(repository.save(guestBook));
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

        QGuestBook qGuestBook = QGuestBook.guestBook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();
        qGuestBook.title.contains(keyword);
        builder.and(expression);

        Page<GuestBook> result = repository.findAll(builder, pageable);

        result.stream().forEach(guestbook ->{
            System.out.println(guestbook);
        });

    }
}
