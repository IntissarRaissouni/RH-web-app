package com.IT.SpringBootAngular.Service;

import com.IT.SpringBootAngular.Entitys.*;
import com.IT.SpringBootAngular.Repo.DepartementRepo;
import com.IT.SpringBootAngular.Repo.EmployeeRepo;
import com.IT.SpringBootAngular.Repo.HRadminRepo;
import com.IT.SpringBootAngular.dto.EmployeeRequest;
import com.IT.SpringBootAngular.dto.HRAdminRequest;
import com.IT.SpringBootAngular.dto.SignupRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService{
    private final HRadminRepo hRadminRepo;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepo employeeRepo;
    private final DepartementRepo departementRepo;

    @Autowired
    public AuthServiceImpl(HRadminRepo hRadminRepo, PasswordEncoder passwordEncoder, EmployeeRepo employeeRepo, DepartementRepo departementRepo) {
        this.hRadminRepo = hRadminRepo;
        this.passwordEncoder = passwordEncoder;
        this.employeeRepo = employeeRepo;
        this.departementRepo = departementRepo;
    }

    @Override
    public boolean createHRAdmin(HRAdminRequest hrAdminRequest) {
        // Check if HR admin already exists by email
        if (hRadminRepo.existsByEmail(hrAdminRequest.getEmail())) {
            return false;
        }

        // Create a new User entity and copy properties from HRAdminRequest
        HRadmin hRadmin = new HRadmin();
        BeanUtils.copyProperties(hrAdminRequest, hRadmin);

        // Set user role
        Role adminRole = hrAdminRequest.setRole(Role.ADMIN);
        hRadmin.setRoles(Collections.singleton(adminRole));

        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(hrAdminRequest.getPassword());
        hRadmin.setPassword(hashedPassword);

        // Save the user
        hRadminRepo.save(hRadmin);
        return true;
    }

    @Override
    public boolean addEmployee(EmployeeRequest employeeRequest) {
        return false;
    }

    @Override
    public boolean createUser(SignupRequest signupRequest) {
        return true;
    }


    public boolean addEmployeeByadmin(String admin_id , String departement_id,
                                      EmployeeRequest employeeRequest) {
        if (employeeRepo.existsByEmail(employeeRequest.getEmail())) {
            return false;
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);

        HRadmin admin = hRadminRepo.findById(admin_id).orElse(null);
        employee.setAdmin(admin);

        Role EmplRole = employeeRequest.setRole(Role.USER);
        employee.setRoles(Collections.singleton(EmplRole));

        Salaire EmplSalaire = employeeRequest.getSalaire();
        employee.setSalaire(EmplSalaire);

        Departement EmplDept = departementRepo.findById(departement_id).orElse(null);
        employee.setDepartement(EmplDept);

        employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
        employeeRepo.save(employee);
        return true;
    }

}