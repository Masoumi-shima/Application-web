package com.example.WebApplication.member.repository;

import com.example.WebApplication.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>
{
    Optional<Member> findByEmail(String email);
}
