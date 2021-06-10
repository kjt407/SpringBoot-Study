package com.example.ex4.controller;

import com.example.ex4.dto.SampleDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {

    @GetMapping("/ex1")
    public void ex1(){
        log.info("ex1...........");
    }

    @GetMapping({"/ex2", "/loop", "/exLink"})
    public void ex2(Model model){
        List<SampleDto> list = IntStream.rangeClosed(1,20).asLongStream().mapToObj(i->{
           SampleDto dto = SampleDto.builder()
           .sno(i)
           .first("First.."+i)
           .last("Last.."+i)
           .regTime(LocalDateTime.now())
           .build();
           return dto;
        }).collect(Collectors.toList());

        model.addAttribute("list", list);
    }

    @GetMapping("/exInline")
    public String exInline(RedirectAttributes redirectAttributes){
        SampleDto dto = SampleDto.builder()
                .sno(100L)
                .first("First.."+100)
                .last("Last.."+100)
                .regTime(LocalDateTime.now())
                .build();

        redirectAttributes.addFlashAttribute("result","success");
        redirectAttributes.addFlashAttribute("dto",dto);

        return "redirect:/sample/ex4";
    }

    @GetMapping({"/exInline3", "/ex3"})
    public void ex3(Model model){
        log.info("ex3...........");
        SampleDto dto = SampleDto.builder()
                .sno(200L)
                .first("First.."+100)
                .last("Last.."+100)
                .regTime(LocalDateTime.now())
                .build();

        model.addAttribute("result","success");
        model.addAttribute("dto",dto);
    }

    @GetMapping({"/exLayout1", "/exLayout2", "/exTemplate", "/exSidebar"})
    public void exLayout1(){
        log.info("exLayout.............");
    }

}
