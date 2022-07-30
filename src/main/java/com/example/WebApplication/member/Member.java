package com.example.WebApplication.member;

import com.example.WebApplication.member.generators.PermitNumberGenerator;
import com.example.WebApplication.validations.EnumValidator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Data
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permit_num_seq")
    @GenericGenerator(name = "permit_num_seq",
            strategy = "com.example.WebApplication.member.generators.PermitNumberGenerator",
            parameters = {
                    @Parameter
                            (name = PermitNumberGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter
                            (name = PermitNumberGenerator.VALUE_PREFIX_PARAMETER, value = "A"),
                    @Parameter
                            (name = PermitNumberGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    private String permitNumber;
    @NotEmpty(message = "Entrez votre prénom.")
    @Column(nullable = false)
    private String firstName;
    @NotEmpty(message = "Entrez votre nom.")
    @Column(nullable = false)
    private String lastName;
    @NotNull(message = "Entrez votre date de naissance.")
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    @NotEmpty(message = "Entrez votre adresse courriel.")
    @Email(message = "L'adresse courriel n'est pas valide.")
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private boolean passedExam;
    @EnumValidator(enumClass = Gender.class)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    public Member(String firstName,
                  String lastName,
                  LocalDate birthDate,
                  String email,
                  Gender gender,
                  boolean passedExam)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.gender = gender;
        this.passedExam = passedExam;
    }

    public Member(Member member)
    {
        this (member.firstName,
                member.lastName,
                member.birthDate,
                member.email,
                member.gender,
                member.passedExam);
    }
}
