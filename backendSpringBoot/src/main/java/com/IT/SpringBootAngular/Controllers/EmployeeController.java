package com.IT.SpringBootAngular.Controllers;

import com.IT.SpringBootAngular.Entitys.*;
import com.IT.SpringBootAngular.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*") //frontend connection
@RequestMapping("/employee/{id}")
public class EmployeeController {
    @Autowired
    private EmpService employeeService;
    @Autowired
    private ReclamationService reclamationService;
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private DemandeService demandeService;


    //get employee by id
    @GetMapping("/getinfo")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable String id){
        Employee message = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(message);
    }

    //edit his own info salary not included
    @PutMapping("Reclamations/edit/{r_id}")
    public ResponseEntity<String> editEmployee(@PathVariable (name = "id") String id,
                                               @PathVariable (name = "r_id") String reclamation_id,
                                               @RequestBody Reclamation reclamation){
        return null;
    }
    //add reclamation
    @PostMapping("/claims/add")
    public ResponseEntity<Response> addRecalamation(@PathVariable String id,@RequestBody Reclamation reclamation){
        Response message = reclamationService.saveReclamation(id,reclamation);
        return ResponseEntity.ok(message);
    }
    @GetMapping("/claims/getAll")
    public ResponseEntity<List<Reclamation>> getAllReclamations(@PathVariable String id){
        List<Reclamation> message = reclamationService.getreclamations(id);
        return ResponseEntity.ok(message);
    }
    @DeleteMapping("/claims/delete/{r_id}")
    public ResponseEntity<Response> deleteReclamation(@PathVariable (name="id") String employee_id,@PathVariable (name="r_id") String reclamation_id ){
        Response message = reclamationService.deleteReclamationByEmployee(employee_id,reclamation_id);
        return ResponseEntity.ok(message);
    }
    //-------demandes part---------------
    //resign
    @PostMapping("request/resign/add")
    public ResponseEntity<Response> addResingRequest(@PathVariable (name = "id") String id , @RequestBody Demande demande){
        Response message = demandeService.SaveDemand(id,demande);
        return ResponseEntity.ok(message);
    }
    @DeleteMapping("/request/resign/delete/{r_id}")
    public  ResponseEntity<Response> deleteResingRequest(@PathVariable (name = "id") String e_id,@PathVariable (name = "r_id") String r_id){
        Response message = demandeService.deleteDemande(e_id,r_id);
        return ResponseEntity.ok(message);
    }
    @GetMapping("/request/resigns")
    public  ResponseEntity<List<Demande>> getresingDemands(@PathVariable String id){
        List<Demande> message = demandeService.getresingdemands(id);
        return ResponseEntity.ok(message);
    }

    //conge
    @PostMapping("requests/leave/add")
    public ResponseEntity<Response> addvacationrequest(@PathVariable String id , @RequestBody DemandeConge demandeConge){
        Response message = demandeService.SaveDemandConge(id,demandeConge);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("request/leaves/delete/{r_id}")
    public ResponseEntity<Response> deletevacationrequest(@PathVariable (name = "id") String id , @PathVariable (name = "r_id") String request_id){
        Response message = demandeService.deleteDemandeConge(id,request_id);
        return ResponseEntity.ok(message);
    }


    @GetMapping("/request/leaves")
    public  ResponseEntity<List<DemandeConge>> getVocationDemands(@PathVariable String id){
        List<DemandeConge> message = demandeService.getvocationdemands(id);
        return ResponseEntity.ok(message);
    }

    //--------------attendance part-----------------
    @PutMapping("/attendance/checkin")
    public ResponseEntity<Response> checkin(@PathVariable (name = "id") String id, @RequestBody Attendance attendance){
        Response message = attendanceService.checkin(id,attendance);
        return ResponseEntity.ok(message);
    }
    @PutMapping("/attendance/checkout")
    public ResponseEntity<Response> checkout(@PathVariable (name = "id") String id , @RequestBody Attendance attendance){
        Response message = attendanceService.checkout(id,attendance);
        return ResponseEntity.ok(message);
    }


}
