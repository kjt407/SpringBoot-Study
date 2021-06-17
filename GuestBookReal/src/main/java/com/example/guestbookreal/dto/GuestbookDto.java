package com.example.guestbookreal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuestbookDto {
    private Long gno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate,modDate;
}
