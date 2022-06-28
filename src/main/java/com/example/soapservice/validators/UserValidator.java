package com.example.soapservice.validators;

import com.example.soapservice.exceptions.ValidationException;
import com.example.soapservice.soap.users.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (!isValidPassword(user.getPassword())){
            errors.add("Incorrect password");
        }
        if (!errors.isEmpty()) {
            throw  new ValidationException(errors);
        }
    }

    public boolean isValidPassword(String password) {
        String regex = "(?=.*[0-9])(?=.*[A-Z])";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.find();
    }
}
