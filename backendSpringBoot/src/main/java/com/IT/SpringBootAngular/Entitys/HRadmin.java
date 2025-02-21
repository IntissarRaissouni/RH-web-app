package com.IT.SpringBootAngular.Entitys;

// Importation des annotations et classes nécessaires
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

/**
 * Classe représentant un administrateur RH (HRadmin) qui implémente UserDetails
 * pour l'intégration avec Spring Security.
 */
@Document(collection = "HRadmin") // Annotation pour indiquer que cette classe est une collection MongoDB
public class HRadmin implements UserDetails {

    @Id // Indique que ce champ est l'identifiant unique du document
    private String id;
    private String firstname;
    private String lastname;
    private String entreprise;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING) // Spécifie que l'énumération est stockée sous forme de chaîne
    private Set<Role> roles;

    @DBRef // Références aux documents Employee et Departement dans MongoDB
    private List<Employee> employees;
    @DBRef
    private List<Departement> departements;

    /**
     * Constructeur sans paramètres requis par certains frameworks.
     */
    public HRadmin() {}

    /**
     * Constructeur avec paramètres pour initialiser un administrateur RH.
     */
    public HRadmin(String id, String firstname, String lastname, String entreprise, String email, String password,
                   List<Employee> employees, List<Departement> departements) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.entreprise = entreprise;
        this.email = email;
        this.password = password;
        this.employees = employees;
        this.departements = departements;
    }

    /**
     * Constructeur utilisé pour la gestion des rôles avec Spring Security.
     */
    public HRadmin(String email, String password, Set<GrantedAuthority> authorities, String id) {
        this.email = email;
        this.password = password;
        this.roles = authorities.stream()
                .map(role -> Role.valueOf(role.getAuthority().replace("ROLE_", "")))
                .collect(Collectors.toSet());
        this.id = id;
    }

    // Getters et Setters pour manipuler les attributs de l'entité

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getEntreprise() { return entreprise; }
    public void setEntreprise(String entreprise) { this.entreprise = entreprise; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public List<Employee> getEmployees() { return this.employees; }
    public void setEmployees(List<Employee> employees) { this.employees = employees; }

    public List<Departement> getDepartements() { return this.departements; }
    public void setDepartements(List<Departement> departements) { this.departements = departements; }

    /**
     * Ajoute un employé à la liste des employés gérés par cet administrateur.
     */
    public void addEmployee(Employee employee) {
        if (this.getEmployees() == null) {
            this.employees = new ArrayList<>();
        }
        this.employees.add(employee);
    }

    /**
     * Supprime un employé de la liste des employés gérés par cet administrateur.
     */
    public void removeEmployee(Employee emp) {
        if (this.employees != null)
            this.employees.remove(emp);
    }

    /**
     * Ajoute un département à la liste des départements gérés.
     */
    public void addDepartement(Departement departement) {
        if (this.getDepartements() == null)
            this.departements = new ArrayList<>();
        this.departements.add(departement);
    }

    /**
     * Supprime un département de la liste des départements gérés.
     */
    public void removeDepartement(Departement departement) {
        if (this.departements != null)
            this.departements.remove(departement);
    }

    /**
     * Implémentation de la méthode getAuthorities() pour Spring Security.
     * Convertit les rôles en objets GrantedAuthority nécessaires à l'authentification.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }

    /**
     * Implémentation des méthodes UserDetails pour la gestion de l'utilisateur par Spring Security.
     */
    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return email; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
