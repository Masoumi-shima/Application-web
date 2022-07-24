package com.example.WebApplication.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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

    @GetMapping("/api/membres/:{permitNumber}")
    public ResponseEntity<Member> getAMember(@PathVariable("permitNumber") String permitNumber)
    {
        if (!memberService.isPermitNumberValid(permitNumber))
        {
            return ResponseEntity.badRequest().build();
        }

        if (memberRepository.findById(permitNumber).isPresent())
        {
            Member member = memberRepository.findById(permitNumber).get();
            return ResponseEntity.ok(member);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/membres/:{permitNumber}")
    public ResponseEntity<Member> updateMember(@PathVariable("permitNumber") String permitNumber, @RequestBody Member updatedMember)
    {
        if (!memberService.isPermitNumberValid(permitNumber))
        {
            return ResponseEntity.badRequest().build();
        }
        if (memberRepository.findById(permitNumber).isPresent())
        {
            Member member = memberService.update(permitNumber, updatedMember);
            return ResponseEntity.ok(member);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/membres/:{permitNumber}")
    public ResponseEntity<Member> deleteMember(@PathVariable("permitNumber") String permitNumber)
    {
        if (!memberService.isPermitNumberValid(permitNumber))
        {
            return ResponseEntity.badRequest().build();
        }
        if (memberRepository.findById(permitNumber).isPresent())
        {
            memberRepository.deleteById(permitNumber);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseBody
    @PostMapping("/api/membre")
    public ResponseEntity<Member> createMember(@Validated @RequestBody Member member)
    {
        memberRepository.save(member);
        return ResponseEntity.ok(member);
    }
}


