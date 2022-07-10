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

    @GetMapping({ "/"})
    public ModelAndView loadPage()
    {
        ModelAndView modelAndView = new ModelAndView("form");
        Member member = new Member();
        modelAndView.addObject("member", member);
        return modelAndView;
    }

    @PostMapping("/api/v1/member")
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
            memberService.addNewMember(member);
            String memberId = member.getId();
            return new ModelAndView("redirect:/api/v1/confirmation?id=" + memberId);
        }
    }

    @GetMapping({"/api/v1/confirmation"})
    public ModelAndView showConfirmation(@RequestParam String id)
    {
        Member member = memberRepository.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("confirmation");
        modelAndView.addObject("member", member);
        return modelAndView;
    }
//    @ResponseBody
//    @PostMapping("/api/v1/member")
//    public ResponseEntity<Member> addNewMember(@Validated @RequestBody Member member)
//    {
//        memberService.addNewMember(member);
//        return new ResponseEntity<>(member, HttpStatus.OK);
//    }

}
