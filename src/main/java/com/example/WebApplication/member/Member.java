package com.example.WebApplication.member;

import com.example.WebApplication.validations.EnumValidator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Table
@Data
public class Member
{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

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
    @Email(message = "L'addresse courriel n'est pas valide.")
    @Column(nullable = false)

    private String email;

    @Column(nullable = false)
    private boolean passedExam;

    @EnumValidator(enumClass = Gender.class)
    @Column(nullable = false)
    private Gender gender;

    public Member(String firstName,
                  String lastName,
                  LocalDate birthDate,
                  String email,
                  boolean passedExam,
                  Gender gender)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.passedExam = passedExam;
        this.gender = gender;
    }
}
