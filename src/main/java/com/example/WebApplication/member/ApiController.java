package com.example.WebApplication.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ApiController
{
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @Autowired
    public ApiController(MemberRepository memberRepository, MemberService memberService)
    {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    @GetMapping("/api/membres")
    public ResponseEntity<List<Member>> getMembers()
    {
        List<Member> members = memberRepository.findAll();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/api/membres/:{id}")
    public ResponseEntity<Member> getAMember(@PathVariable("id") String id)
    {
        if (memberRepository.findById(id).isPresent())
        {
            Member member = memberRepository.findById(id).get();
            return ResponseEntity.ok(member);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/membres/:{id}")
    public ResponseEntity<Member> updateMember(@PathVariable("id") String id, @RequestBody Member updatedMember)
    {
        if (memberRepository.findById(id).isPresent())
        {
            Member member = memberService.update(id, updatedMember);
            return ResponseEntity.ok(member);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/membre")
    public
}


