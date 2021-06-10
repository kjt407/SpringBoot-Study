package com.example.guestbookreal.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/guestbook")
@Controller
@Log4j2
public class GuestBookNewController {

    @GetMapping({"/","/list"})
    public String list(){
        return "/guestbook/list";
    }
}
