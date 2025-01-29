package com.IT.SpringBootAngular.dto;

import com.IT.SpringBootAngular.Entitys.Departement;
import com.IT.SpringBootAngular.Entitys.HRadmin;
import com.IT.SpringBootAngular.Entitys.Role;
import com.IT.SpringBootAngular.Entitys.Salaire;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.Date;
import java.util.Set;


public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String position;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private String address;
    private String city;
    private String state;
    private Date hireDate;
//    private boolean isActive;
    private String password;
    private Role role;
    private HRadmin admin;
    private Salaire salaire;
//    private Departement department;




    public EmployeeRequest() {}

    public EmployeeRequest(String firstName, String lastName, String position, String email,
                           String phoneNumber, Date birthDate, String address, String city,
                           String state, Date hireDate, String password,
                           Role role, Salaire salaire, HRadmin admin) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.address = address;
        this.city = city;
        this.state = state;
//        this.department = department;
        this.hireDate = hireDate;
        this.password = password;
        this.role = role;
        this.salaire = salaire;
        this.admin = admin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

//    public Departement getDepartment() {
//        return department;
//    }
//
//    public void setDepartment() {
//        this.department = department;
//    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

//    public boolean isActive() {
//        return isActive;
//    }
//
//    public void setActive(boolean active) {
//        isActive = active;
//    }

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

    public Salaire getSalaire() {
        return salaire;
    }

    public void setSalaire(Salaire salaire) {
        this.salaire = salaire;
    }

    public HRadmin getAdmin() {
        return admin;
    }

    public void setAdmin(HRadmin admin) {
        this.admin = admin;
    }
}
