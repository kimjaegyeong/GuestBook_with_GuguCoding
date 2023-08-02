package org.zerock.guestbook_with_gugucoding.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/sample")
public class SampleController {
    @GetMapping({"/exLayout1","/exLayout2","/exTemplate","exSidebar"})
    public void exLayout1(){
        log.info("exLayout...................");
    }
}
