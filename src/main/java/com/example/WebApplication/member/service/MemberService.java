package com.example.WebApplication.member.service;

import com.example.WebApplication.member.Member;
import com.example.WebApplication.member.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MemberService
{
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    public void addNewMember(Member member)
    {
            memberRepository.save(member);
    }

    public boolean isEmailTaken(Member member)
    {
        Optional<Member> optional = memberRepository.findByEmail(member.getEmail());
        return optional.isPresent();
    }

    public Member update(String permitNumber, Member updatedMember)
    {
        Member member = memberRepository.findById(permitNumber).get();
        member.setFirstName(updatedMember.getFirstName());
        member.setLastName(updatedMember.getLastName());
        member.setBirthDate(updatedMember.getBirthDate());
        member.setEmail(updatedMember.getEmail());
        member.setGender(updatedMember.getGender());
        member.setPassedExam(updatedMember.isPassedExam());
        memberRepository.save(member);
        return member;
    }

    public boolean isPermitNumberValid(String permitNumber)
    {
        Pattern pattern = Pattern.compile("[A]\\d{4}");
        Matcher matcher = pattern.matcher(permitNumber);
        return matcher.matches();
    }

    public Member applyPatchToMember(JsonPatch patch, Member member)
            throws JsonPatchException, JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        JsonNode jsonNode = patch.apply(objectMapper.convertValue(member, JsonNode.class));
        return objectMapper.treeToValue(jsonNode, Member.class);
    }
}
