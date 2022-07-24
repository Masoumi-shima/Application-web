package com.example.WebApplication.member;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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

    public Member update(String id, Member updatedMember)
    {
        Member member = memberRepository.findById(id).get();
        member.setFirstName(updatedMember.getFirstName());
        member.setLastName(updatedMember.getLastName());
        member.setBirthDate(updatedMember.getBirthDate());
        member.setEmail(updatedMember.getEmail());
        member.setGender(updatedMember.getGender());
        member.setPassedExam(updatedMember.isPassedExam());
        memberRepository.save(member);
        return member;
    }
}
