package com.ingenio.transportmanagementservice.component.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsername.class)
public @interface IUniqueUsername {

    String message() default "El nombre de usuario ya está en uso.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
