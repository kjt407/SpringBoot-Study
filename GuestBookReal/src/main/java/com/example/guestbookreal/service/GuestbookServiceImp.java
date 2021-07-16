package com.example.guestbookreal.service;

import com.example.guestbookreal.dto.GuestbookDto;
import com.example.guestbookreal.dto.PageRequestDto;
import com.example.guestbookreal.dto.PageResultDto;
import com.example.guestbookreal.entity.Guestbook;
import com.example.guestbookreal.entity.QGuestbook;
import com.example.guestbookreal.repository.GuestBookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<Guestbook> result = guestBookRepository.findAll(booleanBuilder,pageable);

        Function<Guestbook, GuestbookDto> fn = (entity -> entityToDTO(entity));

        return new PageResultDto<>(result,fn);
    }

    @Override
    public GuestbookDto read(Long gno) {
        Optional<Guestbook> result = guestBookRepository.findById(gno);

        return result.isPresent()? entityToDTO(result.get()) : null;
    }

    @Override
    public void remove(Long gno) {
        guestBookRepository.deleteById(gno);
    }

    @Override
    public void modify(GuestbookDto dto) {
        Optional<Guestbook> result = guestBookRepository.findById(dto.getGno());

        if(result.isPresent()){
            Guestbook entity = result.get();

            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());

            guestBookRepository.save(entity);
        }
    }

    private BooleanBuilder getSearch(PageRequestDto requestDto) {
        String type = requestDto.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = requestDto.getKeyword();
        BooleanExpression expression = qGuestbook.gno.gt(0L);

        booleanBuilder.and(expression);

        if(type == null || type.trim().length() == 0 ){
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("t")){
            conditionBuilder.or(qGuestbook.title.contains(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qGuestbook.content.contains(keyword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qGuestbook.writer.contains(keyword));
        }

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }
}
