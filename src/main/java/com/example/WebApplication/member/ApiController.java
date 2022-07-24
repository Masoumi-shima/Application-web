package com.example.WebApplication.member;

import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Patch;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<Member> updateMember(@PathVariable("permitNumber") String permitNumber,@Validated  @RequestBody Member updatedMember)
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

//    @PatchMapping(path = "/api/membres/:{permitNumber}", consumes = "application/json-patch+json")
//    public ResponseEntity<Member> updateMemberPartially(@PathVariable("permitNumber") String permitNumber,
//                                                        @RequestBody String json)
//    {
//        Optional<Member> member = memberRepository.findById(permitNumber);
//        if (!memberService.isPermitNumberValid(permitNumber))
//        {
//            return ResponseEntity.badRequest().build();
//        }
//        if (member.isPresent())
//        {
//            fields.forEach((key, value) ->
//            {
//                Field field = ReflectionUtils.findField(Member.class, (String) key);
//                field.setAccessible(true);
//                ReflectionUtils.setField(field, member.get(), value);
//            });
//            memberRepository.save(member.get());
//            return ResponseEntity.ok(member.get());
//        }
//        else
//        {
//            return ResponseEntity.notFound().build();
//        }
//    }

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
        return new ResponseEntity<Member>(member,HttpStatus.CREATED);
    }
}


