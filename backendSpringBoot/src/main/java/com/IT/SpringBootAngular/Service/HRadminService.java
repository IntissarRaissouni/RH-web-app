package com.IT.SpringBootAngular.Service;

import com.IT.SpringBootAngular.Entitys.*;
import com.IT.SpringBootAngular.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HRadminService {
    @Autowired
    private HRadminRepo hrAdminRepo;
    @Autowired
    private ReclamationRepo reclamationRepo;
    @Autowired
    private SalaireRepo salairerepo;
    @Autowired
    private DepartementRepo departementRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private EmpService empService;
    @Autowired
    private DepartementService departementService;




    public HRadmin getAdminById(String id){
        return hrAdminRepo.findById(id).orElse(null);
    }
    public String deleteAdmin(String id){
        HRadmin admin = hrAdminRepo.findById(id).orElse(null);
        if(admin == null)
            return "acc not found";
//        userRepo.deleteById(admin.getUser().getId());
        if(admin.getDepartements()!=null)
            for(Departement d : admin.getDepartements()){
                departementService.deleteDepartement(id,d.getId());
            }

        hrAdminRepo.deleteById(id);
        return admin.getFirstname()+" has been deleted";
    }

    public HRadmin saveHRAdmin(HRadmin hradmin) {
//        GUser saveGUser = userRepo.save(hradmin.getUser());
//        hradmin.setUser(saveGUser);
        return hrAdminRepo.save(hradmin);
    }

    public void deleteHRAdmin(String id) {
        HRadmin hRadmin = hrAdminRepo.findById(id).orElse(null);
//        userRepo.delete(hRadmin.getUser());
        employeeRepo.deleteAll(hRadmin.getEmployees());
        departementRepo.deleteAll(hRadmin.getDepartements());
        hrAdminRepo.delete(hRadmin);
    }

















    public String saveEmployee(String admin_id , Employee employee){
        HRadmin hRadmin = hrAdminRepo.findById(admin_id).orElse(null);
        Departement departement = employee.getDepartement();
        if(hRadmin != null) {
//            GUser saveGUser = userRepo.save(employee.getUser());
//            Salaire saveSalire = salairerepo.save(employee.getSalaire());

//            employee.setUser(saveGUser);
            Salaire saveSalire = salairerepo.save(employee.getSalaire());
            employee.setSalaire(saveSalire);
            employee.setDepartement(departement);
            employee.setAdmin(hRadmin);
            departement.addEmployee(employee);
            hRadmin.addEmployee(employee);
            departementRepo.save(departement);
            hrAdminRepo.save(hRadmin);
            employeeRepo.save(employee);

            return employee.get_id();
        }else{
            return "admin not found";
        }


    }


    public String editEmpoloyee(String Aid ,String Eid, Employee updatedemployee){
        Employee employee = employeeRepo.findById(Eid).orElse(null);
        if(employee==null)
            return "employee not found";
        HRadmin admin = hrAdminRepo.findById(Aid).orElse(null);
        if(admin==null)
            return "admin not found";

        updatedemployee.setAdmin(admin);
        updatedemployee.setSalaire(employee.getSalaire());

//        updatedemployee.setUser(employee.getUser());


        updatedemployee.setReclamation(employee.getReclamation());
        updatedemployee.setDepartement(employee.getDepartement());

        employeeRepo.save(updatedemployee);
        return updatedemployee.get_id() + "updated";

    }

}
