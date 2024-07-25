package com.api.verify_credit.service.auth;

import com.api.verify_credit.DTOS.loginSystem.RegisterDTO;
import com.api.verify_credit.domain.user.User;
import com.api.verify_credit.repositories.UserRepository;
import com.api.verify_credit.service.user.UserService;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public AuthService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username);
        return user;
    }

    public UserDetails signUp(RegisterDTO data) throws Exception {
        if (userRepository.findByEmail(data.email()) != null) {
            throw new InvalidJwtException("User Already Exist!",null ,null);
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        String encryptedConfirmPassword = new BCryptPasswordEncoder().encode(data.confirmPassword());
        User newUser = new User(data.firstName() , data.lastName(), data.email(), encryptedPassword , encryptedConfirmPassword ,data.document()  ,data.numberAccount() , data.numberBank(), data.role());
        userService.verifyIfPasswordLengthIsMoreThatEight(data);
        userService.verifyIfPasswordIsEqualsConfirmPassword(data);
        return userRepository.save(newUser);
    }



}
