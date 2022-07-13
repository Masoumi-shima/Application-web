package com.example.WebApplication.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/")
    public ModelAndView loadMainPage()
    {
        return new ModelAndView("index");
    }
    @GetMapping({ "/ajouter-membre"})
    public ModelAndView loadPage()
    {
        ModelAndView modelAndView = new ModelAndView("form");
        Member member = new Member();
        modelAndView.addObject("member", member);
        return modelAndView;
    }

    @GetMapping("/modifier-membre")
    public ModelAndView modifyMember(@RequestParam String id)
    {
        ModelAndView modelAndView = new ModelAndView("form");
        Member member = memberRepository.findById(id).get();
        System.out.println("Gender: " + member.getGender());
        modelAndView.addObject("member", member);
        return modelAndView;
    }

    @GetMapping("/deleteMember")
    public ModelAndView deleteMember(@RequestParam String id)
    {
        memberRepository.deleteById(id);
        return new ModelAndView("redirect:/liste-membre");
    }
    @GetMapping("/liste-membre")
    public ModelAndView getMembersList()
    {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("members", memberRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/ajouter-membre")
    public ModelAndView submitForm(@Validated @ModelAttribute("member") Member member,BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            ModelAndView modelAndView = new ModelAndView("form", HttpStatus.BAD_REQUEST);
            modelAndView.addObject("member", member);
            return modelAndView;
        }
        else
        {
            if (member.getId() != null && member.getId().equals(""))
            {
                member = new Member(member);
            }
            memberService.addNewMember(member);
            String memberId = member.getId();
            return new ModelAndView("redirect:/confirmation?id=" + memberId);
        }
    }

    @GetMapping({"/confirmation"})
    public ModelAndView showConfirmation(@RequestParam String id)
    {
        Member member = memberRepository.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("confirmation");
        modelAndView.addObject("member", member);
        return modelAndView;
    }
}
