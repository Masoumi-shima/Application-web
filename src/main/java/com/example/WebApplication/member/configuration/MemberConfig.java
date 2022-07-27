package com.example.WebApplication.member.configuration;

import com.example.WebApplication.member.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberConfig
{
    @Bean
    CommandLineRunner commandLineRunner(MemberRepository memberRepository)
    {
        return args -> {
        };
    }
}
