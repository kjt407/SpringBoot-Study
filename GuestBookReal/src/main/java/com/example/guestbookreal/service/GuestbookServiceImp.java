package com.example.guestbookreal.service;

import com.example.guestbookreal.dto.GuestbookDto;
import com.example.guestbookreal.dto.PageRequestDto;
import com.example.guestbookreal.dto.PageResultDto;
import com.example.guestbookreal.entity.Guestbook;
import com.example.guestbookreal.repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImp implements GuestbookService {

    private final GuestBookRepository guestBookRepository;

    @Override
    public Long register(GuestbookDto dto) {
        log.warn("DTO------------------");
        log.warn(dto);
        Guestbook entity = dtoToEntity(dto);

        guestBookRepository.save(entity);
        log.warn(entity);

        return entity.getGno();
    }

    @Override
    public PageResultDto<GuestbookDto, Guestbook> getList(PageRequestDto requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
        Page<Guestbook> result = guestBookRepository.findAll(pageable);

        Function<Guestbook, GuestbookDto> fn = (entity -> entityToDTO(entity));

        return new PageResultDto<>(result,fn);
    }
}
