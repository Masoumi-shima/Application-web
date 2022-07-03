package com.example.WebApplication.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path= "api/v1/member")
public class MemberController
{
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping
    @ResponseBody
    public ResponseEntity<Member> addNewMember(@Validated @RequestBody Member member)
    {
        memberService.addNewMember(member);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
}
