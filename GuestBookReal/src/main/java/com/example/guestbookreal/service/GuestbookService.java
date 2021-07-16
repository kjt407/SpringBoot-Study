package com.example.guestbookreal.service;

import com.example.guestbookreal.dto.GuestbookDto;
import com.example.guestbookreal.dto.PageRequestDto;
import com.example.guestbookreal.dto.PageResultDto;
import com.example.guestbookreal.entity.Guestbook;

import java.util.Optional;

public interface GuestbookService {
    Long register(GuestbookDto dto);

    PageResultDto<GuestbookDto, Guestbook> getList(PageRequestDto requestDTO);

    GuestbookDto read(Long gno);

    void remove(Long gno);

    void modify(GuestbookDto dto);

    default Guestbook dtoToEntity(GuestbookDto dto) {
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    default GuestbookDto entityToDTO(Guestbook entity) {
        GuestbookDto dto = GuestbookDto.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
