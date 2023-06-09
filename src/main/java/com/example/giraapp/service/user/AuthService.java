package com.example.giraapp.service.user;


import com.example.giraapp.helper.LoggedUser;
import com.example.giraapp.model.binding.UserLoginModel;
import com.example.giraapp.model.binding.UserRegisterModel;
import com.example.giraapp.model.entity.User;
import com.example.giraapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final PasswordEncoder encoder;

    @Autowired
    public AuthService(UserRepository userRepository, LoggedUser loggedUser, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.encoder = encoder;
    }


    public void registerUser(UserRegisterModel userRegisterModel) {
        this.userRepository.saveAndFlush(User.builder()
                .username(userRegisterModel.getUsername())
                .password(encoder.encode(userRegisterModel.getPassword()))
                .email(userRegisterModel.getEmail())
                .build());
    }


    public void loginUser(UserLoginModel userLoginModel) {
        User user = this.userRepository.findByEmail(userLoginModel.getEmail()).orElse(new User());

        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
        this.loggedUser.setEmail(user.getEmail());
    }


    public void logoutUser() {
        this.loggedUser.clearUser();
    }


    public boolean isAuthentic(String email, String password) {
        User user = this.userRepository.findByEmail(email).orElse(new User());
        String encodedPassword = user.getPassword();

        return encoder.matches(password, encodedPassword);
    }
}

