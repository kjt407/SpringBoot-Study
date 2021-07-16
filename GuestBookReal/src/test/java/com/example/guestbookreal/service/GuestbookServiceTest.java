package com.example.guestbookreal.service;

import com.example.guestbookreal.dto.GuestbookDto;
import com.example.guestbookreal.dto.PageRequestDto;
import com.example.guestbookreal.dto.PageResultDto;
import com.example.guestbookreal.entity.Guestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceTest {

    @Autowired
    private GuestbookService service;

    @Test
    public void testRegister(){
        GuestbookDto guestbookDto = GuestbookDto.builder()
                .title("샘플 1")
                .content("샘플 1")
                .writer("종태")
                .build();
        System.out.println(service.register(guestbookDto));
    }

    @Test
    public void testList(){

        PageRequestDto pageRequestDto = PageRequestDto.builder().page(1).size(100).type("tc").keyword("22").build();
        PageResultDto<GuestbookDto, Guestbook> resultDto = service.getList(pageRequestDto);

        for(GuestbookDto guestbookDto : resultDto.getDtoList()){
            System.out.println(guestbookDto);
        }

        resultDto.getPageList().forEach(i-> System.out.println(i));
    }
}
