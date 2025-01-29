package com.IT.SpringBootAngular.Entitys;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.stream.Collectors;

@Document(collection = "employees")
public class Employee implements UserDetails {
    @Id
    private String _id = new ObjectId().toString();
    ;
    private String firstName;
    private String lastName;
    private String position;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private String address;
    private String city;
    private String state;
    private String department;
    private Date hireDate;
    private boolean isActive;
    private String gender;
    private String password;
    @DBRef
    private Attendance attendance;
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    @DBRef
    private Salaire salaire;
    @DBRef
    private List<Reclamation> reclamation;
    @DBRef
    @JsonIgnore
    private HRadmin admin;
    @DBRef
    @JsonIgnore
    private Departement departement;
    @DBRef
    @JsonIgnore
    private  List<Demande> demandeList;

    @DBRef
    @JsonIgnore
    private List<DemandeConge> demandeCongeList;

    public Employee() {

    }
    public Employee(String firstName, String lastName, String position, String email,
                    String phoneNumber, Date birthDate, String address, String city,
                    String state, String department, Date hireDate,  boolean isActive,
                    Salaire salaire , String gender, List<Reclamation> reclamation ,Departement departement ,
                    HRadmin admin, String password, Set<Role> roles) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.address = address;
        this.city = city;
        this.department = department;
        this.hireDate = hireDate;
        this.isActive = isActive;
        this.gender = gender;
        this.salaire = salaire;
        this.reclamation = reclamation;
        this.admin = admin;
        this.departement = departement;
        this.password = password;
        this.roles = roles;
    }

    public Employee(String email, String password, Set<GrantedAuthority> authorities, String id) {
        this.email = email;
        this.password = password;
        this.roles = authorities.stream()
                .map(role-> Role.valueOf(role.getAuthority().replace("ROLE_","")))
                .collect(Collectors.toSet());
        this._id = id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Reclamation> getReclamation() {
        return reclamation;
    }

    public void setReclamation(List<Reclamation> reclamation) {
        this.reclamation = reclamation;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Salaire getSalaire() {
        return salaire;
    }

    public void setSalaire(Salaire salaire) {
        this.salaire = salaire;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addReclamation(Reclamation r){
        if (this.reclamation == null) {
            this.reclamation = new ArrayList<>();
        }
        this.reclamation.add(r);
    }
    public void adddemande(Demande d){
        if(this.demandeList == null){
            this.demandeList = new ArrayList<>();
        }
        this.demandeList.add(d);
    }
    public void adddemandeConge(DemandeConge d){
        if(this.demandeCongeList == null){
            this.demandeCongeList = new ArrayList<>();
        }
        this.demandeCongeList.add(d);
    }
    public void removeDemandeConge(DemandeConge d){
        this.demandeCongeList.remove(d);
    }

    public void removeDemande(Demande d){
        this.demandeList.remove(d);
    }
    public void removeReclamation(Reclamation r){
        this.reclamation.remove(r);
    }

    public HRadmin getAdmin() {
        return admin;
    }

    public void setAdmin(HRadmin admin) {
        this.admin = admin;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public List<Demande> getDemandeList() {
        return demandeList;
    }

    public void setDemandeList(List<Demande> demandeList) {
        this.demandeList = demandeList;
    }

    public List<DemandeConge> getDemandeCongeList() {
        return demandeCongeList;
    }

    public void setDemandeCongeList(List<DemandeConge> demandeCongeList) {
        this.demandeCongeList = demandeCongeList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role->new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }

    public void setPassword(String password) {
        this.password = password;
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


    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }
}