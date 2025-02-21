package com.IT.SpringBootAngular.Service;

// Importation des classes nécessaires
import com.IT.SpringBootAngular.Entitys.*;
import com.IT.SpringBootAngular.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

@Service // Annotation indiquant que cette classe est un service géré par Spring
public class EmpService {

    // Injection des dépendances des repositories
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private SalaireRepo Srepo;

    @Autowired
    private ReclamationRepo reclamationRepo;

    @Autowired
    private HRadminRepo adminRepo;

    @Autowired
    private DepartementRepo departementRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ---------------- Actions des administrateurs et départements ---------------- //

    /**
     * Ajoute un employé à un département spécifique sous la supervision d'un administrateur.
     * @param admin_id ID de l'administrateur
     * @param departement_id ID du département
     * @param employee Objet Employee à ajouter
     * @return Un objet Response indiquant le succès ou l'échec de l'opération
     */
    public Response addEmployeeByDepartemnt(String admin_id, String departement_id, Employee employee) {
        HRadmin admin = adminRepo.findById(admin_id).orElse(null);
        Departement departement = departementRepo.findById(departement_id).orElse(null);

        if (admin == null || departement == null)
            return new Response("Admin or departement not found");

        // Associer l'employé à l'admin et au département
        employee.setAdmin(admin);
        employee.setDepartement(departement);
        employee.setRoles(Collections.singleton(Role.USER)); // Définition du rôle de l'employé
        employee.setPassword(passwordEncoder.encode(employee.getPassword())); // Hachage du mot de passe

        // Ajout de l'employé dans les collections de l'admin et du département
        departement.addEmployee(employee);
        admin.addEmployee(employee);

        // Sauvegarde des informations
        Srepo.save(employee.getSalaire()); // Sauvegarde du salaire de l'employé
        adminRepo.save(admin);
        departementRepo.save(departement);
        employeeRepo.save(employee);

        return new Response("Employee has been saved", employee.get_id());
    }

    /**
     * Supprime un employé d'un département spécifique sous la supervision d'un administrateur.
     * @param admin_id ID de l'administrateur
     * @param departement_id ID du département
     * @param employee_id ID de l'employé à supprimer
     * @return Un objet Response indiquant le succès ou l'échec de l'opération
     */
    public Response deleteEmployeeBydepartement(String admin_id, String departement_id, String employee_id) {
        HRadmin admin = adminRepo.findById(admin_id).orElse(null);
        Departement departement = departementRepo.findById(departement_id).orElse(null);

        if (admin == null || departement == null)
            return new Response("Admin or departement not found");

        Employee employee = employeeRepo.findById(employee_id).orElse(null);
        if (employee == null)
            return new Response("No such employee found");

        // Retirer l'employé des collections
        admin.removeEmployee(employee);
        departement.removeEmployee(employee);

        // Sauvegarde des modifications
        adminRepo.save(admin);
        departementRepo.save(departement);

        Srepo.delete(employee.getSalaire()); // Suppression du salaire associé
        employeeRepo.delete(employee); // Suppression de l'employé

        return new Response("Employee " + employee.get_id() + " has been deleted");
    }

    /**
     * Récupère la liste des employés d'un département spécifique sous la supervision d'un administrateur.
     * @param admin_id ID de l'administrateur
     * @param departement_id ID du département
     * @return Liste des employés du département ou null en cas d'erreur
     */
    public List<Employee> getAllEmployeeByDepartement(String admin_id, String departement_id) {
        HRadmin admin = adminRepo.findById(admin_id).orElse(null);
        Departement departement = departementRepo.findById(departement_id).orElse(null);

        if (admin == null || departement == null)
            return null;

        return departement.getEmployeeList();
    }

    /**
     * Modifie les informations d'un employé appartenant à un département spécifique sous la supervision d'un administrateur.
     * @param admin_id ID de l'administrateur
     * @param departement_id ID du département
     * @param employee_id ID de l'employé à modifier
     * @param updatedEmployee Nouvel objet Employee contenant les mises à jour
     * @return Un message de succès ou d'erreur
     */
    public String editEmployeeByDepartement(String admin_id, String departement_id, String employee_id, Employee updatedEmployee) {
        HRadmin admin = adminRepo.findById(admin_id).orElse(null);
        Departement departement = departementRepo.findById(departement_id).orElse(null);

        if (admin == null || departement == null)
            return "No admin or departement found";

        Employee employee = employeeRepo.findById(employee_id).orElse(null);
        if (employee == null)
            return "Employee not found with ID: " + employee_id;

        // Mise à jour des informations de l'employé
        updatedEmployee.setAdmin(admin);
        updatedEmployee.setDepartement(departement);

        // (Attention : ici, il manque la sauvegarde de updatedEmployee dans employeeRepo)

        return "Employee information updated successfully";
    }

    // ---------------- Actions administrateur ---------------- //

    /**
     * Récupère la liste de tous les employés supervisés par un administrateur donné.
     * @param id ID de l'administrateur
     * @return Liste des employés ou null si l'admin n'existe pas
     */
    public List<Employee> getAllEmployee(String id) {
        HRadmin admin = adminRepo.findById(id).orElse(null);
        if (admin == null)
            return null;
        return admin.getEmployees();
    }

    // ---------------- Actions employé ---------------- //

    /**
     * Récupère les informations d'un employé à partir de son ID.
     * @param id ID de l'employé
     * @return Objet Employee ou null si l'employé n'existe pas
     */
    public Employee getEmployeeById(String id) {
        return employeeRepo.findById(id).orElse(null);
    }

    /**
     * Modifie les informations d'un employé spécifique.
     * @param id ID de l'employé
     * @param updatedEmployee Nouvel objet Employee contenant les mises à jour
     * @return Un message de confirmation ou d'erreur
     */
    public String editEmployee(String id, Employee updatedEmployee) {
        Employee employee = employeeRepo.findById(id).orElse(null);
        if (employee == null)
            return "Employee not found";

        Salaire savedSalary = employee.getSalaire();

        // (Attention : il manque ici l'application des mises à jour et la sauvegarde de l'employé)

        return "Employee information updated successfully";
    }
}
