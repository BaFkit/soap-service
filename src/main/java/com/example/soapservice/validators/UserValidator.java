package com.example.soapservice.validators;

import com.example.soapservice.entities.User;
import com.example.soapservice.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {

    public void validate(User user) {
        List<String> errors = new ArrayList<>();
        if (user.getLogin().isBlank()){
            errors.add("User login cannot be blank");
        }
        if (user.getName().isBlank()){
            errors.add("User name cannot be blank");
        }
        if (user.getPassword().isBlank()){
            errors.add("User password cannot be blank");
        }
        if (!errors.isEmpty()) {
            throw  new ValidationException(errors);
        }
    }
}
