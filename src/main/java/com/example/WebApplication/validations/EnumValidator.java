package com.example.WebApplication.validations;

import com.example.WebApplication.member.Gender;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumValidator.ValueOfEnumValidator.class)
public @interface EnumValidator
{
    String message() default "Choisissez une option: MALE, FEMALE, OTHER";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<Gender> enumClass();

    public class ValueOfEnumValidator implements ConstraintValidator<EnumValidator, Gender>
    {
        @Override
        public boolean isValid(Gender gender, ConstraintValidatorContext context)
        {
            return gender != null;
        }
    }
}
