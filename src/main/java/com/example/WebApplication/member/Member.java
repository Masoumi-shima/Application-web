package com.example.WebApplication.member;

import com.example.WebApplication.validations.EnumValidator;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Table
public class Member
{
    @Id
    @SequenceGenerator(name = "member_sequence",
            sequenceName = "member_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "member_sequence")
    private Long id;

    @NotEmpty(message = "firstName| Entrez votre prénom.")
    @Column(nullable = false)
    private String firstName;

    @NotEmpty(message = "lastName| Entrez votre nom.")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "birthDate| Entrez votre date de naissance.")
    @Column(nullable = false)
    private LocalDate birthDate;

    @NotEmpty(message = "email| Entrez votre adresse courriel.")
    @Email(message = "email| L'addresse courriel n'est pas valide.")
    @Column(nullable = false,
            unique = true)

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

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public boolean getPassedExam()
    {
        return passedExam;
    }

    public void setPassedExam(boolean passedExam)
    {
        this.passedExam = passedExam;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }
}
