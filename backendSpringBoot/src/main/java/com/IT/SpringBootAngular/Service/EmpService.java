package com.IT.SpringBootAngular.Service;

import com.IT.SpringBootAngular.Entitys.*;
import com.IT.SpringBootAngular.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.zip.Adler32;

@Service
public class EmpService {
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




    //actions by admin and departements

    public Response addEmployeeByDepartemnt(String admin_id , String departement_id , Employee employee){
        HRadmin admin = adminRepo.findById(admin_id).orElse(null);
        Departement departement = departementRepo.findById(departement_id).orElse(null);
        if(admin==null || departement==null)
            return new Response("admin or departement not found");

        employee.setAdmin(admin);
        employee.setDepartement(departement);
        employee.setRoles(Collections.singleton(Role.USER));
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        departement.addEmployee(employee);
        admin.addEmployee(employee);

        Srepo.save(employee.getSalaire());
//        userRepo.save(employee.getUser());

        adminRepo.save(admin);
        departementRepo.save(departement);

        employeeRepo.save(employee);
        return new Response("employee has been saved",employee.get_id());
    }

    public Response deleteEmployeeBydepartement(String admin_id, String departement_id , String empolyee_id){
        HRadmin admin = adminRepo.findById(admin_id).orElse(null);
        Departement departement = departementRepo.findById(departement_id).orElse(null);
        if(admin==null || departement==null)
            return new Response("admin or departement not found");

        Employee employee = employeeRepo.findById(empolyee_id).orElse(null);
        if(employee==null)
            return new Response("no such an employee");

        admin.removeEmployee(employee);
        departement.removeEmployee(employee);

        adminRepo.save(admin);
        departementRepo.save(departement);

//        userRepo.delete(employee.getUser());
        Srepo.delete(employee.getSalaire());
        employeeRepo.delete(employee);
        return new Response("employee ",employee.get_id()," has been deleted");
    }

    public List<Employee> getAllEmployeeByDepartement(String admin_id , String departement_id){
        HRadmin admin = adminRepo.findById(admin_id).orElse(null);
        Departement departement = departementRepo.findById(departement_id).orElse(null);
        if(admin==null || departement==null)
            return null;

         return departement.getEmployeeList();



    }
    //------------------------------
    public String editEmployeeByDepartement(String admin_id , String departement_id , String employee_id , Employee updatedemployee) {
        HRadmin admin = adminRepo.findById(admin_id).orElse(null);
        Departement departement = departementRepo.findById(departement_id).orElse(null);
        if(admin==null || departement==null)
            return "no admin or departement found";
        Employee employee = employeeRepo.findById(employee_id).orElse(null);
        if(employee==null)
            return "employe not found with such an id"+employee_id;
        updatedemployee.setAdmin(admin);
        updatedemployee.setDepartement(departement);

        return null;
    }
    //-----------admin controller------------------
    public List<Employee> getAllEmployee(String id){
        HRadmin admin = adminRepo.findById(id).orElse(null);
        if(admin == null)
            return null;
        return admin.getEmployees();
    }

    //--------------employee controller

    public Employee getEmployeeById(String id){
        Employee employee = employeeRepo.findById(id).orElse(null);
        return employee;
    }

    public String editEmployee(String id,Employee updatedemp){
        Employee employee = employeeRepo.findById(id).orElse(null);
        Salaire savedSalary = employee.getSalaire();
        return null;

    }
}
