package com.coding404.myweb.controller;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
@RequestMapping("/topic")
public class TopicController {


    @Autowired
    @Qualifier("topicService")
    private TopicService topicService;

    // 날짜 변환
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")));
            }
        });
    }


    // 목록(전체)
    @GetMapping("/topicListAll")
    public String topicListAll(Model model) {

        ArrayList<TopicVO> list = topicService.getList();
        model.addAttribute("list", list);

        return "topic/topicListAll";
    }

    //목록(사용자)
    @GetMapping("/topicListMe")
    public String topicListMe(@RequestParam("topicWriter") String topicWriter, Model model) {

        String userId = "admin"; // 현재 로그인 아이디 admin으로 가정

        ArrayList<TopicVO> list = topicService.getListMe(topicWriter);
        model.addAttribute("list", list);

        return "topic/topicListMe";
    }

    // 글 등록
    @GetMapping("/topicReg")
    public String topicReg(Model model) {

        String topicWriter = "admin";

        LocalDateTime topicRegdate= LocalDateTime.now();

        model.addAttribute("topicRegdate", topicRegdate);
        model.addAttribute("topicWriter", topicWriter);

        return "topic/topicReg";
    }

    // 등록 요청
    @PostMapping("/registForm")
    public String registForm(@RequestParam("topicRegdate") String topicRegdateStr,
                             TopicVO vo,
                             RedirectAttributes ra) {

        LocalDateTime topicRegdate = LocalDateTime.parse(topicRegdateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        vo.setTopicRegdate(topicRegdate);

        int result = topicService.topicInsert(vo);

        if(result == 1) {
            ra.addFlashAttribute("msg", "정상 등록되었습니다.");
        } else {
            ra.addFlashAttribute("msg", "등록에 실패했습니다.");
        }

        return "redirect:/topic/topicListAll";
    }

    // 수정
    @GetMapping("/topicModify")
    public String topicModify() {
        return "topic/topicModify";
    }


    //상세
    @GetMapping("/topicDetail")
    public String topicDetail() {
        return "topic/topicDetail";
    }
}
