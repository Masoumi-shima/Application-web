package com.example.WebApplication.member.service;

import com.example.WebApplication.member.Member;
import com.example.WebApplication.member.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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

    public Member update(String permitNumber, Member member)
    {
        member.setPermitNumber(permitNumber);
        addNewMember(member);
        return member;
    }

    public Member applyPatchToMember(JsonPatch patch, Member member)
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            JsonNode jsonNode = patch.apply(objectMapper.convertValue(member, JsonNode.class));
            Member updatedMember = objectMapper.treeToValue(jsonNode, Member.class);
            if (isEmailTaken(updatedMember) &&
                    !memberRepository.findByEmail(updatedMember.getEmail())
                            .get()
                            .getPermitNumber()
                            .equals(updatedMember.getPermitNumber()))
            {
                throw new HttpClientErrorException(HttpStatus.CONFLICT);
            }
            else
            {
                addNewMember(updatedMember);
                return updatedMember;
            }
        }
        catch (JsonPatchException | JsonProcessingException exception)
        {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
    }

    public boolean isEmailTaken(Member member)
    {
        Optional<Member> optional = memberRepository.findByEmail(member.getEmail());

        return optional.isPresent();
    }

    public boolean isPermitNumberValid(String permitNumber)
    {
        Pattern pattern = Pattern.compile("[A]\\d{4}");
        Matcher matcher = pattern.matcher(permitNumber);
        return matcher.matches();
    }
}
