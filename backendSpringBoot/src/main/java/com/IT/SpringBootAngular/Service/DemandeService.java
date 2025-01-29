package com.IT.SpringBootAngular.Service;
import java.util.*;

import com.IT.SpringBootAngular.Entitys.*;
import com.IT.SpringBootAngular.Repo.DemandeCongeRepo;
import com.IT.SpringBootAngular.Repo.EmployeeRepo;
import com.IT.SpringBootAngular.Repo.HRadminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.IT.SpringBootAngular.Repo.DemandeRepo;
import com.IT.SpringBootAngular.Entitys.Demande;

@Service
public class DemandeService {
    @Autowired
    private DemandeRepo demandeRepo;
    @Autowired
    private  EmployeeRepo employeeRepo;
    @Autowired
    private  HRadminRepo adminRepo;
    @Autowired
    private DemandeCongeRepo demandeCongeRepo;

    //demande emession for employee
    public Response SaveDemand(String employee_id,Demande demande){
        Employee employee = employeeRepo.findById(employee_id).orElse(null);
        if(employee !=null){
            employee.adddemande(demande);
            employeeRepo.save(employee);
            demandeRepo.save(demande);
            return new Response("demande saved !");
        }else{
            return new Response("employee not found");
        }


    }

    public Response deleteDemande(String employee_id , String demande_id){
        Employee employee = employeeRepo.findById(employee_id).orElse(null);
        Demande demande = demandeRepo.findById(demande_id).orElse(null);
        if (employee == null || demande == null){
            return new Response("employee or demande not found");
        }
        employee.removeDemande(demande);
        demandeRepo.deleteById(demande_id);
        employeeRepo.save(employee);
        return new Response("demande removed");
    }
    public List<Demande> getresingdemands(String employee_id) {
        Employee employee = employeeRepo.findById(employee_id).orElse(null);
        if (employee == null) {
            return null;
        }
        List<Demande> demandeList = employee.getDemandeList();
        if (demandeList == null || demandeList.isEmpty()) {
            return null;
        }
        return demandeList;
    }

    // //vacation demand for employee
    public Response SaveDemandConge(String employee_id, DemandeConge demande){
        Employee employee = employeeRepo.findById(employee_id).orElse(null);
        if(employee !=null){
            employee.adddemandeConge(demande);
            employeeRepo.save(employee);
            demandeCongeRepo.save(demande);
            return new Response("demande saved !");
        }else{
            return new Response("employee not found");
        }


    }

    public Response deleteDemandeConge(String employee_id , String demande_id){
        Employee employee = employeeRepo.findById(employee_id).orElse(null);
        DemandeConge demande = demandeCongeRepo.findById(demande_id).orElse(null);
        if (employee == null ){
            return new Response("employee not found");
        }
        if( demande == null){
            return new Response("demand null");
        }
        employee.removeDemandeConge(demande);
        demandeRepo.deleteById(demande_id);
        employeeRepo.save(employee);

        return new Response("demand removed");
    }

    public List<DemandeConge> getvocationdemands(String employee_id) {
        Employee employee = employeeRepo.findById(employee_id).orElse(null);
        if (employee == null) {
            return null;
        }
        List<DemandeConge> demandeCongeList = employee.getDemandeCongeList();
        if (demandeCongeList == null || demandeCongeList.isEmpty()) {
            return null;
        }
        return demandeCongeList;
    }


    //admin handles
    //resing
    public Map<String, Demande> getAllResingDemands(String id) {
        Optional<HRadmin> hrAdminOptional = adminRepo.findById(id);

        if (hrAdminOptional.isEmpty() || hrAdminOptional.get().getEmployees() == null) {
            return Collections.emptyMap(); // Return an empty map instead of null
        }

        Map<String, Demande> demandsMap = new HashMap<>();
        String name;

        HRadmin hRadmin = hrAdminOptional.get();

        for (Employee e : hRadmin.getEmployees()) {
            if (e.getDemandeList() != null) {
                for (Demande d : e.getDemandeList()) {
                    if (d != null && "No action".equals(d.getState())) {
                        name = e.getFirstName() + " " + e.getLastName();
                        demandsMap.put(name, d);
                    }
                }
            }
        }

        return demandsMap;
    }
    //accept demand
    public Response acceptresingDemand(String admin_id , String demande_id){
        HRadmin hRadmin = adminRepo.findById(admin_id).orElse(null);
        if(hRadmin == null)
            return new Response("admin not found");
        Demande demande = demandeRepo.findById(demande_id).orElse(null);
        if(demande==null){
            return new Response("demand not found");
        }
        demande.setState("accepted");
        return new Response("demand accepted");


    }
    public Response refuseresingDemande(String admin_id , String demande_id){
        HRadmin hRadmin = adminRepo.findById(admin_id).orElse(null);
        if(hRadmin == null)
            return new Response("admin not found");
        Demande demande = demandeRepo.findById(demande_id).orElse(null);
        if (demande == null)
            return new Response("demande not found");

        demande.setState("refused");
        demandeRepo.save(demande);
        return new Response("demand refused");
    }



    //vacation
    public Map<String,DemandeConge> getAllVacationDemands(String id){
        HRadmin hRadmin = adminRepo.findById(id).orElse(null);
        if(hRadmin== null)
            return null;
        if(hRadmin.getEmployees()==null)
            return null;
        Map<String,DemandeConge> demandsMap = new HashMap<>();
        String name;
        for(Employee e : hRadmin.getEmployees()){
            if(e.getDemandeCongeList()!=null) {
                for (DemandeConge d : e.getDemandeCongeList()) {
                    if(Objects.equals(d.getState(), "No action")){
                        name = e.getFirstName()+" "+e.getLastName();
                        demandsMap.put(name,d);
                    }
                }
            }

        }
        return demandsMap;
    }


    //accept
    public Response acceptvacationDemand(String admin_id , String demande_id){
        HRadmin hRadmin = adminRepo.findById(admin_id).orElse(null);
        if(hRadmin == null)
            return new Response("admin not found");
        DemandeConge demande = demandeCongeRepo.findById(demande_id).orElse(null);
        if(demande == null){
            return new Response("demand not found");
        }
        demande.setState("accepted");
        demandeCongeRepo.save(demande);
        return new Response("demand accepted");


    }


    public Response refusevacationDemand(String admin_id , String demande_id){
        HRadmin hRadmin = adminRepo.findById(admin_id).orElse(null);
        if(hRadmin == null)
            return new Response("admin not found");
        DemandeConge demande = demandeCongeRepo.findById(demande_id).orElse(null);
        if(demande == null){
            return new Response("demand not found");
        }

        demande.setState("refused");
        demandeCongeRepo.save(demande);
        return new Response("demand refused");


    }
}