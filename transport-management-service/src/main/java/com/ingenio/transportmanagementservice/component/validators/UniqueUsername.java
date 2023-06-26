package com.ingenio.transportmanagementservice.component.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ingenio.transportmanagementservice.domain.repository.UserRepository;

public class UniqueUsername implements ConstraintValidator<IUniqueUsername, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null || userRepository == null) {
            return true;
        }

        return !userRepository.existsByUsername(username);
    }
}