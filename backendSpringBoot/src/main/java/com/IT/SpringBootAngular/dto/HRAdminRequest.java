package com.IT.SpringBootAngular.dto;

import com.IT.SpringBootAngular.Entitys.Role;

public class HRAdminRequest {
    private String firstname;
    private String lastname;
    private String entreprise;
    private String email;
    private String password;
    private Role role;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Role setRole(Role role) {
        this.role = role;
        return role;
    }
}