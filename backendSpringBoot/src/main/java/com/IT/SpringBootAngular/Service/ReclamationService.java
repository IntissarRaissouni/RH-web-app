package com.IT.SpringBootAngular.Service;

import com.IT.SpringBootAngular.Entitys.Employee;
import com.IT.SpringBootAngular.Entitys.HRadmin;
import com.IT.SpringBootAngular.Entitys.Reclamation;
import com.IT.SpringBootAngular.Entitys.Response;
import com.IT.SpringBootAngular.Repo.EmployeeRepo;
import com.IT.SpringBootAngular.Repo.HRadminRepo;
import com.IT.SpringBootAngular.Repo.ReclamationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveAction;

@Service
public class ReclamationService {
    @Autowired
    private ReclamationRepo reclamationRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private HRadminRepo adminRepo;

    public Map<String,Reclamation> getAllReclamations(String id){
        HRadmin hRadmin = adminRepo.findById(id).orElse(null);
        if(hRadmin== null)
            return null;
        if(hRadmin.getEmployees()==null)
            return null;
        Map<String,Reclamation> reclamationMap = new HashMap<>();
        String name ;
        for (Employee e : hRadmin.getEmployees()){
            if(e.getReclamation()!=null){
            for (Reclamation r : e.getReclamation()){
                name = e.getFirstName()+" "+e.getLastName();
                reclamationMap.put(name,r);
            }}
        }


        return reclamationMap;

    }
    public Reclamation getReclamationById(String id){
        return reclamationRepo.findById(id).orElse(null);
    }

    public Response saveReclamation(String employee_id, Reclamation reclamation) {
        Employee employee = employeeRepo.findById(employee_id).orElse(null);
        if (employee != null) {
            employee.addReclamation(reclamation);
            employeeRepo.save(employee);
            reclamationRepo.save(reclamation);
            return new Response("Reclamation saved successfully");
        } else {
            return new Response("Employee not found");
        }
    }

    public List<Reclamation> getreclamations(String id){
        Employee employee = employeeRepo.findById(id).orElse(null);
        if(employee == null)
            return null;
        return employee.getReclamation();
    }


    public Response deleteReclamationByEmployee(String  employee_id, String reclamation_id){
        Employee employee = employeeRepo.findById(employee_id).orElse(null);
        Reclamation removedReclamation = reclamationRepo.findById(reclamation_id).orElse(null);
        if (employee == null || removedReclamation == null) {
            return new Response("Employee or Reclamation not found");
        }
        employee.removeReclamation(removedReclamation);
        reclamationRepo.deleteById(reclamation_id);
        employeeRepo.save(employee);
        return new Response("reclamation "+reclamation_id+ " of "+employee_id+" : deleted");
    }



}
