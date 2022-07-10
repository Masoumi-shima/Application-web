package com.example.WebApplication.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class MemberController
{
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberService memberService, MemberRepository memberRepository)
    {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @GetMapping({"/api/v1/member","/"})
    public ModelAndView loadPage()
    {
        ModelAndView modelAndView = new ModelAndView("form");
        Member member = new Member();
        modelAndView.addObject("member", member);
        return modelAndView;
    }

    @PostMapping("/api/v1/member")
    public ModelAndView submitForm(@ModelAttribute("member") Member member) {
        memberService.addNewMember(member);
        return new ModelAndView("confirmation");
    }
    @ResponseBody
    @PostMapping("/api/v1/member")
    public ResponseEntity<Member> addNewMember(@Validated @RequestBody Member member)
    {
        memberService.addNewMember(member);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

}
