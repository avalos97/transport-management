package com.ingenio.transportmanagementservice.component.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ingenio.transportmanagementservice.domain.repository.ClientRepository;

public class UniqueEmailClient implements ConstraintValidator<IUniqueEmailClient, String> {

    @Autowired
    ClientRepository repository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && !repository.findByEmail(email).isPresent();
    }
}
