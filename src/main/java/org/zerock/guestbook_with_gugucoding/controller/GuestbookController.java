package org.zerock.guestbook_with_gugucoding.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.guestbook_with_gugucoding.dto.PageRequestDTO;
import org.zerock.guestbook_with_gugucoding.service.GuestbookService;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor
@Slf4j
public class GuestbookController {
    private final GuestbookService service;
    @GetMapping({"/"})
    public String index(){
        return "redirect:/guestbook/list";
    }
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){ //SpringMVC는 파라미터를 자동으로 수집해주는 기능이 있음
        //화면에서 page, size라는 파라미터를 전달하면 자동으로 PageRequestDTO객체에 수집될 것이다.
        model.addAttribute("result",service.getList(pageRequestDTO));

    }
    @GetMapping("/hello")
    public String hello(){
        return "guestbook/hello";
    }
}
