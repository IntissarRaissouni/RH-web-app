package com.IT.SpringBootAngular.Controllers;

import com.IT.SpringBootAngular.Entitys.*;
import com.IT.SpringBootAngular.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.VariableOperators;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin/{id}")
public class HRadminController {

    @Autowired
    private HRadminService adminService;
    @Autowired
    private EmpService employeeService;
    @Autowired
    private ReclamationService reclamationService;
    @Autowired
    private DemandeService demandeService;

    @Autowired
    private AttendanceService attendanceService;
    //handle admin profile
    @GetMapping("/get")
    public ResponseEntity<HRadmin> getAdminById(@PathVariable String id){
        HRadmin message = adminService.getAdminById(id);
        return ResponseEntity.ok(message);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAdmin(@PathVariable String id){
        String message = adminService.deleteAdmin(id);
        return ResponseEntity.ok(message);
    }
    @GetMapping("/employee/getAll")
    public ResponseEntity<List<Employee>> getAllEmployee(@PathVariable String id){

        List<Employee>message = employeeService.getAllEmployee(id);
        return ResponseEntity.ok(message);
    }

    //get all reclamation by employee name
    @GetMapping("/reclamations")
    public ResponseEntity<Map<String,Reclamation>> getAllReclamations(@PathVariable String id){
        Map<String,Reclamation> message = reclamationService.getAllReclamations(id);
        return ResponseEntity.ok(message);
    }
    //---------------------------------------------------------------------------
    //get all resign demand by employee name
    @GetMapping("/request/resing/getAll")
    public ResponseEntity<Map<String, Demande>> getAllResignRequests(@PathVariable String id){
        Map<String,Demande> message = demandeService.getAllResingDemands(id);
        return ResponseEntity.ok(message);
    }
    //accept
    @PutMapping("/request/resign/accept/{r_id}")
    public ResponseEntity<Response> acceptResignDemande(@PathVariable (name = "id") String id , @PathVariable (name = "r_id") String r_id){
        Response messaga = demandeService.acceptresingDemand(id,r_id);
        return ResponseEntity.ok(messaga);
    }
    //refuse
    @PutMapping("/request/resign/refuse/{r_id}")
    public ResponseEntity<Response> refuseResignDemande(@PathVariable (name = "id") String id , @PathVariable (name = "r_id") String r_id){
        Response message = demandeService.refuseresingDemande(id,r_id);
        return ResponseEntity.ok(message);
    }


    //get all vacation demands of employees
    @GetMapping("/request/leave/getAll")
    public ResponseEntity<Map<String, DemandeConge>> getAllVacationDemands(@PathVariable String id){
        Map<String,DemandeConge> message = demandeService.getAllVacationDemands(id);
        return ResponseEntity.ok(message);
    }

    //accept
    @PutMapping("/request/vacation/accept/{r_id}")
    public ResponseEntity<Response> acceptVacationDemande(@PathVariable (name = "id") String id , @PathVariable (name = "r_id") String r_id){
        Response message = demandeService.acceptvacationDemand(id,r_id);
        return ResponseEntity.ok(message);
    }

    //refuse
    @PutMapping("/request/vacation/refuse/{r_id}")
    public ResponseEntity<Response> refuseVacationDemande(@PathVariable (name = "id") String id , @PathVariable (name = "r_id") String r_id){
        Response message = demandeService.refusevacationDemand(id,r_id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/attendances")
    public ResponseEntity<Map<String, LocalDateTime>> getAttendance(@PathVariable String id){
        Map<String,LocalDateTime> message = attendanceService.getAttendance(id);
        return ResponseEntity.ok(message);
    }



}