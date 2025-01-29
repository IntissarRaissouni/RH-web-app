package com.IT.SpringBootAngular.Controllers;

import com.IT.SpringBootAngular.Entitys.Departement;
import com.IT.SpringBootAngular.Entitys.Employee;
import com.IT.SpringBootAngular.Entitys.Response;
import com.IT.SpringBootAngular.Service.AuthServiceImpl;
import com.IT.SpringBootAngular.Service.DepartementService;
import com.IT.SpringBootAngular.Service.EmpService;
import com.IT.SpringBootAngular.dto.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/{id}/departement")
public class DepartementController {
    @Autowired
    private DepartementService departementService;
    @Autowired
    private EmpService empService;

    @Autowired
    private AuthServiceImpl authService;


    //handle Departements
    //---------------------------------------

    @PostMapping("/add")
    public ResponseEntity<Response> addDepartement(@PathVariable String id, @RequestBody Departement departement){
         Response message = departementService.addDepartement(id,departement);
         return  ResponseEntity.ok(message);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Departement>> getAllDepartement(@PathVariable (name = "id") String id){
        List<Departement> message = departementService.getAllDepartements(id);
        return ResponseEntity.ok(message);
    }
    //--------------------------------------

    @DeleteMapping("/delete/{d_id}")
    public ResponseEntity<Response> deleteDepartement(@PathVariable (name="id") String  admin_id ,
                                         @PathVariable (name = "d_id") String departement_id ){
        Response message =  departementService.deleteDepartement(admin_id,departement_id);
        return ResponseEntity.ok(message);
    }
    //Still
    @PutMapping("/edit/{d_id}")
    public ResponseEntity<String> editDepartement(@PathVariable  (name="id") String  admin_id ,
                                       @PathVariable (name = "d_id") String departement_id,@RequestBody Departement departement ){
      String message = departementService.editDepartement(admin_id,departement_id,departement);
        return ResponseEntity.ok(message);
    }


    //handle employees by departements

    @PostMapping("/{d_id}/employee/add")
    public ResponseEntity<Response> addEmployee(@PathVariable (name="id") String admin_id ,
                            @PathVariable (name = "d_id") String departement_id,
                            @RequestBody Employee employee){
        Response message =  empService.addEmployeeByDepartemnt(admin_id,departement_id,employee);
        return ResponseEntity.ok(message);
    }

//    @PostMapping("/{d_id}/employee/add")
//    public ResponseEntity<String> addEmployee(@PathVariable (name="id") String admin_id,
//                                              @PathVariable (name = "d_id") String departement_id,
//                                              @RequestBody EmployeeRequest employeeRequest){
////        employeeRequest.getAdmin();
////        employeeRequest.setDepartment();
//        boolean isEmployeeAdded = authService.addEmployeeByadmin(admin_id,departement_id,employeeRequest);
//        if(isEmployeeAdded){
//            return ResponseEntity.status(HttpStatus.CREATED).body("Employee added");
//        }else{
//            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee not added");
//        }
//    }


    @DeleteMapping("/{d_id}/employee/delete/{e_id}")
    public ResponseEntity<Response> deleteEmployee(@PathVariable (name="id") String admin_id ,
                                   @PathVariable (name = "d_id") String departement_id,
                                   @PathVariable (name ="e_id") String employee_id){
       Response message = empService.deleteEmployeeBydepartement(admin_id,departement_id,employee_id);
       return ResponseEntity.ok(message);
    }

    @GetMapping("/{d_id}/employee/getALL")
    public ResponseEntity<List<Employee>> getAllEmpoyee(@PathVariable (name="id") String  admin_id ,
                                        @PathVariable (name = "d_id") String departement_id ){
        List<Employee> message = empService.getAllEmployeeByDepartement(admin_id,departement_id);
        return ResponseEntity.ok(message);
    }


    //still
    @PutMapping("/{d_id}/employee/edit/{e_id}")
    public String editEmployee(@PathVariable (name="id") String admin_id ,
                                 @PathVariable (name = "d_id") String departement_id,
                                 @PathVariable (name = "e_id") String employee_id,
                                 @RequestBody Employee employee){
        return empService.editEmployeeByDepartement(admin_id,departement_id,employee_id,employee);
    }

}
