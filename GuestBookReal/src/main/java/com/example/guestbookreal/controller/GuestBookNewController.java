package com.example.guestbookreal.controller;

import com.example.guestbookreal.dto.PageRequestDto;
import com.example.guestbookreal.dto.PageResultDto;
import com.example.guestbookreal.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/guestbook")
@Controller
@Log4j2
@RequiredArgsConstructor
public class GuestBookNewController {
    private final GuestbookService service;


    @GetMapping("/")
    public String index(){
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDto pageRequestDto, Model model){
        log.info("list..........."+pageRequestDto);

        model.addAttribute("result",service.getList(pageRequestDto));
    }
}
