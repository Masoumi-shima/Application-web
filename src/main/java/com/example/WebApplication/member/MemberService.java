package com.example.WebApplication.member;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
