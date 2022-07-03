package com.example.WebApplication.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //        TODO: Gérer l'erreur 500 pour la duplication de l'adresse courriel
//        String membersEmail = member.getEmail();
//        Optional<Member> findByEmail = memberRepository.findByEmail(member.getEmail());
//            if (findByEmail.isPresent())
//            {
//                throw new RuntimeException
//                ("Email : Une personne avec cette adresse courriel est déjà enregistrée !");
//            }
    }
}
