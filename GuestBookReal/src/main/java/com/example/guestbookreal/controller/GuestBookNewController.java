package com.example.guestbookreal.controller;

import com.example.guestbookreal.dto.GuestbookDto;
import com.example.guestbookreal.dto.PageRequestDto;
import com.example.guestbookreal.dto.PageResultDto;
import com.example.guestbookreal.entity.Guestbook;
import com.example.guestbookreal.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        model.addAttribute("pageRequestDto",pageRequestDto);
        model.addAttribute("result",service.getList(pageRequestDto));
    }

    @GetMapping("/register")
    public void register(){
    }

    @PostMapping("/register")
    public String register(GuestbookDto guestbookDto, RedirectAttributes redirectAttributes){

        Long gno = service.register(guestbookDto);

        redirectAttributes.addFlashAttribute("msg", gno);
        return "redirect:/guestbook/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long gno, @ModelAttribute("requestDTO") PageRequestDto requestDto, Model model){
        GuestbookDto dto = service.read(gno);

        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(long gno, RedirectAttributes redirectAttributes){
        service.remove(gno);

        redirectAttributes.addFlashAttribute("msg",gno);

        return "redirect:/guestbook/list";
    }

    @PostMapping("/modify")
    public String modify(GuestbookDto dto, @ModelAttribute("requestDTO") PageRequestDto requestDto, RedirectAttributes redirectAttributes){
        service.modify(dto);

        redirectAttributes.addAttribute("page",requestDto.getPage());
        redirectAttributes.addAttribute("type",requestDto.getType());
        redirectAttributes.addAttribute("keyword",requestDto.getKeyword());
        redirectAttributes.addAttribute("gno",dto.getGno());

        return "redirect:/guestbook/read";
    }
}
