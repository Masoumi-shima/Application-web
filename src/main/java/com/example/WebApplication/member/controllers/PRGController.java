package com.example.WebApplication.member.controllers;

import com.example.WebApplication.member.Member;
import com.example.WebApplication.member.repository.MemberRepository;
import com.example.WebApplication.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class PRGController
{
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Autowired
    public PRGController(MemberService memberService, MemberRepository memberRepository)
    {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/")
    public ModelAndView getMainPage()
    {
        return new ModelAndView("index");
    }

    @GetMapping({ "/ajouter-membre"})
    public ModelAndView getForm()
    {
        Member member = new Member();
        ModelAndView modelAndView = new ModelAndView("form");
        modelAndView.addObject("member", member);
        return modelAndView;
    }

    @PostMapping("/ajouter-membre")
    public ModelAndView submitForm(@Validated @ModelAttribute("member") Member member,BindingResult bindingResult)
    {
        Optional<Member> existingMember = memberRepository.findByEmail(member.getEmail());
        if (bindingResult.hasErrors())
        {
            ModelAndView modelAndView = new ModelAndView("form", HttpStatus.BAD_REQUEST);
            modelAndView.addObject("member", member);
            return modelAndView;
        }
        else if (memberService.isEmailTaken(member) &&
                !(existingMember.get().getPermitNumber().equals(member.getPermitNumber())))
        {
            String msg = "Ce courriel est déjà enregistré.";
            ModelAndView modelAndView = new ModelAndView("form");
            modelAndView.addObject("msg", msg);
            return modelAndView;
        }
        else
        {
            if (member.getPermitNumber() != null && member.getPermitNumber().equals(""))
            {
                member = new Member(member);
            }
            memberService.addNewMember(member);
            String memberId = member.getPermitNumber();
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

    @GetMapping("/liste-membre")
    public ModelAndView getMembersList()
    {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("members", memberRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/modifier-membre")
    public ModelAndView modifyMember(@RequestParam String permitNumber)
    {
        Member member = memberRepository.findById(permitNumber).get();
        ModelAndView modelAndView = new ModelAndView("form");
        modelAndView.addObject("member", member);
        return modelAndView;
    }

    @GetMapping("/deleteMember")
    public ModelAndView deleteMember(@RequestParam String permitNumber)
    {
        memberRepository.deleteById(permitNumber);
        return new ModelAndView("redirect:/liste-membre");
    }
}
