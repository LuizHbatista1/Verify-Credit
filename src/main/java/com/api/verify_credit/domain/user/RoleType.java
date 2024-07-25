package com.api.verify_credit.domain.user;



public enum RoleType {

    ADMIN("admin"),
    COMMON("common");

    private String role;

    RoleType (String role){

        this.role = role;

    }

    public String getValue(){

        return role;

    }


}
