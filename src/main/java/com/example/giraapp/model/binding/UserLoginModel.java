package com.example.giraapp.model.binding;

import com.example.giraapp.util.validations.validateLogin.ValidateLogin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ValidateLogin
public class UserLoginModel {

    @NotNull
    @Email
    private String email;

    @Size(min = 3, max = 20)
    @NotNull
    private String password;


}
