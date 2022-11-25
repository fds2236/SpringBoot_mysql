package com.shop.controller;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }
    //오버로딩
    @PostMapping("/new")
    public String memberForm(MemberFormDto memberFormDto){
        // DB에 저장해야할 값
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        memberService.saveMember(member);
        return "redirect:/"; // redirect
        // forwarding 서버가 받았다가 해당 서버가 다시 다른 경로로 옮겨주는 것(포트)
    }
}
