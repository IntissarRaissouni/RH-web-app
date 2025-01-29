package com.IT.SpringBootAngular.Entitys;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Document(collection = "HRadmin")
public class HRadmin implements UserDetails {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String entreprise;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    @DBRef
    private List<Employee> employees;
    @DBRef
    private List<Departement> departements;

    public HRadmin() {

    }

    public HRadmin(String id, String firstname, String lastname, String entreprise, String email, String password, List<Employee> employees, List<Departement> departements) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.entreprise = entreprise;
        this.email = email;
        this.password = password;
        this.employees = employees;
        this.departements = departements;
    }

    public HRadmin(String email, String password, Set<GrantedAuthority> authorities, String id) {
        this.email=email;
        this.password=password;
        this.roles = authorities.stream()
                .map(role -> Role.valueOf(role.getAuthority().replace("ROLE_", "")))
                .collect(Collectors.toSet());
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public List<Departement> getDepartements() {
        return this.departements;
    }

    public void setDepartements(List<Departement> departements) {
        this.departements = departements;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee){
        if(this.getEmployees()==null){
            this.employees = new ArrayList<>();
        }
        this.employees.add(employee);
    }

    public void removeEmployee(Employee emp){
        if(this.employees !=null)
            this.employees.remove(emp);
    }


    public void addDepartement(Departement departement){
        if(this.getDepartements()==null)
            this.departements=new ArrayList<>();
        this.departements.add(departement);
    }
    public void removeDepartement(Departement departement){
        if(this.departements!=null)
            this.departements.remove(departement);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role->new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}