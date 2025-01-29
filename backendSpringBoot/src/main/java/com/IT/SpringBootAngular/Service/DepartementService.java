package com.IT.SpringBootAngular.Service;

import com.IT.SpringBootAngular.Entitys.Departement;
import com.IT.SpringBootAngular.Entitys.Employee;
import com.IT.SpringBootAngular.Entitys.HRadmin;
import com.IT.SpringBootAngular.Entitys.Response;
import com.IT.SpringBootAngular.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @Autowired
    private EmployeeRepo repo;
    @Autowired
    private SalaireRepo Srepo;
    @Autowired
    private ReclamationRepo reclamationRepo;
    @Autowired
    private HRadminRepo adminRepo;
    @Autowired
    private DepartementRepo departementRepo;
    @Autowired
    private EmpService empService;




    public Response addDepartement(String id, Departement departement){
        HRadmin admin = adminRepo.findById(id).orElse(null);
        if(admin==null)
            return null;
        departement.sethRadmin(admin);
        departementRepo.save(departement);
        admin.addDepartement(departement);
        adminRepo.save(admin);
        return new Response("departement "+departement.getDepartName()+" has been saved");
    }

    public List<Departement> getAllDepartements(String id){
        HRadmin hRadmin = adminRepo.findById(id).orElse(null);
        return hRadmin.getDepartements();
    }



    public Response deleteDepartement(String admin_id, String departement_id) {
        HRadmin admin = adminRepo.findById(admin_id).orElse(null);
        if (admin == null)
            return new Response("admin not found");
        Departement departement = departementRepo.findById(departement_id).orElse(null);
        if (departement == null)
            return new Response("departement not found");

        admin.removeDepartement(departement);
        adminRepo.save(admin);
        if(departement.getEmployeeList()!=null){
         for(Employee emp : departement.getEmployeeList()){
            admin.removeEmployee(emp);
            Srepo.delete(emp.getSalaire());
            repo.delete(emp);
        }
            adminRepo.save(admin);
        }
        departementRepo.delete(departement);

        return new Response(departement.getDepartName() + " has been removed");
    }

    //---------------------------------
    public String editDepartement(String admin_id,String departement_id,Departement updateddepartement){
        HRadmin admin = adminRepo.findById(admin_id).orElse(null);
        if(admin==null)
         return "admin not found";

        Departement departement = departementRepo.findById(departement_id).orElse(null);
        if(departement==null)
            return "departement not found";

        if(departement.gethRadmin()!=null){
         updateddepartement.sethRadmin(departement.gethRadmin());}

        updateddepartement.setId(departement_id);
        if(departement.getEmployeeList()!=null)
         updateddepartement.setEmployeeList(departement.getEmployeeList());

        departementRepo.save(updateddepartement);
        admin.removeDepartement(departement);
        admin.addDepartement(updateddepartement);
        departementRepo.delete(departement);
        return "depart "+updateddepartement.getDepartName()+"has been edited and saved";

    }
    public String editDepartement2(String admin_id , String departement_id , Departement updateddepartement){
        HRadmin admin = adminRepo.findById(admin_id).orElse(null);
        if(admin==null)
            return "admin not found";

        Departement departement = departementRepo.findById(departement_id).orElse(null);
        if(departement==null)
            return "departement not found";

        departement.setDepartName(updateddepartement.getDepartName());
        departementRepo.save(departement);
        return departement.getDepartName() + "has been updated and saved successfully";
    }


}
