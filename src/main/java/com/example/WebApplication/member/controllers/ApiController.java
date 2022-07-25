package com.example.WebApplication.member.controllers;

import com.example.WebApplication.member.Member;
import com.example.WebApplication.member.repository.MemberRepository;
import com.example.WebApplication.member.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Member> findAMember(@PathVariable("permitNumber") String permitNumber)
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
    public ResponseEntity<Member> updateMember(@PathVariable("permitNumber") String permitNumber,
                                               @Validated  @RequestBody Member updatedMember)
    {
        Optional<Member> existingMember = memberRepository.findById(permitNumber);
        Optional<Member> memberWithSameEmail = memberRepository.findByEmail(updatedMember.getEmail());
        if (!memberService.isPermitNumberValid(permitNumber))
        {
            return ResponseEntity.badRequest().build();
        }
        if (existingMember.isPresent())
        {
            if (memberWithSameEmail.isEmpty() || existingMember.get().getPermitNumber().equals(memberWithSameEmail.get().getPermitNumber()))
            {
                Member member = memberService.update(permitNumber ,updatedMember);
                return ResponseEntity.ok(member);
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(path = "/api/membres/:{permitNumber}", consumes = "application/json-patch+json")
    public ResponseEntity<Member> updateMemberPartially(@PathVariable("permitNumber") String permitNumber,
                                                        @Validated @RequestBody JsonPatch patch)
            throws JsonPatchException, JsonProcessingException
    {
        Optional<Member> existingMember = memberRepository.findById(permitNumber);
        if (!memberService.isPermitNumberValid(permitNumber))
        {
            return ResponseEntity.badRequest().build();
        }
        if (existingMember.isPresent())
        {
            Member updatedMember = memberService.applyPatchToMember(patch, existingMember.get());
            memberRepository.save(updatedMember);
            return new ResponseEntity<>(updatedMember, HttpStatus.OK);
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
        Optional<Member> existingMember = memberRepository.findByEmail(member.getEmail());

        if (memberService.isEmailTaken(member) &&
            !(existingMember.get().getPermitNumber().equals(member.getPermitNumber())))
    {
        return new ResponseEntity<Member>(member,HttpStatus.CONFLICT);
    }
        memberRepository.save(member);
        return new ResponseEntity<Member>(member,HttpStatus.CREATED);
    }
}
