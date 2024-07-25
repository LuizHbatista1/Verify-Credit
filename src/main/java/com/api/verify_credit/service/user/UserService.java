package com.api.verify_credit.service.user;


import com.api.verify_credit.domain.user.User;
import com.api.verify_credit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Long id){

        return this.userRepository.findById(id).orElseThrow(()-> new RuntimeException());


    }


}
