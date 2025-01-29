package com.IT.SpringBootAngular.dto;


import com.IT.SpringBootAngular.Entitys.Role;

import java.util.Collections;
import java.util.Set;

public class SignupRequest {
    private String email;
    private String name;
    private Role role;
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

//    public Set<Role> getRole() {return role;}
//    public void setRole(Role role) {this.role = Collections.singleton(role);}
}
