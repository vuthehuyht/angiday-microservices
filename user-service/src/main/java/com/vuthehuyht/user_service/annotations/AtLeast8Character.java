package com.vuthehuyht.user_service.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = AtLeast8CharacterValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AtLeast8Character {
    String message() default "AT_LEAST_8_CHARACTERS";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
