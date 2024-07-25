package com.api.verify_credit.service.user;


import com.api.verify_credit.DTOS.loginSystem.RegisterDTO;
import com.api.verify_credit.domain.user.User;
import com.api.verify_credit.infra.exceptions.IdNotFoundException;
import com.api.verify_credit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Long id){

        return this.userRepository.findById(id).orElseThrow(()-> new IdNotFoundException(id));

    }

    public void verifyIfPasswordIsEqualsConfirmPassword(RegisterDTO registerDTO) throws  Exception{

        if(registerDTO.password() != registerDTO.confirmPassword()) {

            throw new Exception("Invalid Password !");

        }

    }




    public void verifyIfPasswordLengthIsMoreThatEight(RegisterDTO registerDTO) throws Exception{

        if(registerDTO.password().length() < 8){

            throw new Exception("The password need to have minimum eight characters");

        }

    }

}
