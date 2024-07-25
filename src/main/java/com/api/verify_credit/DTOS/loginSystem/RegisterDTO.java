package com.api.verify_credit.DTOS.loginSystem;

import com.api.verify_credit.domain.user.RoleType;

public record RegisterDTO (String firstName, String lastName, String email, String password, String confirmPassword, String document, Integer numberAccount, Integer numberBank , RoleType role){
}
