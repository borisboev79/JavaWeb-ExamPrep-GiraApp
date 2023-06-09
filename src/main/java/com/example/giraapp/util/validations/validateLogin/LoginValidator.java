package com.example.giraapp.util.validations.validateLogin;

import com.example.giraapp.model.binding.UserLoginModel;
import com.example.giraapp.model.entity.User;
import com.example.giraapp.service.user.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class LoginValidator implements ConstraintValidator<ValidateLogin, UserLoginModel> {

    private final UserService userService;
    private final PasswordEncoder encoder;

    @Autowired
    public LoginValidator(UserService userService, PasswordEncoder encoder) {

        this.userService = userService;
        this.encoder = encoder;
    }


    @Override
    public void initialize(ValidateLogin constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginModel userLoginModel, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> optionalUser = userService.findByEmail(userLoginModel.getEmail());

        if (optionalUser.isEmpty()){
            return false;
        }

        String  rawPassword = userLoginModel.getPassword();
        String  encodedPassword  = optionalUser.get().getPassword();

        return encoder.matches(rawPassword, encodedPassword);

    }
}
