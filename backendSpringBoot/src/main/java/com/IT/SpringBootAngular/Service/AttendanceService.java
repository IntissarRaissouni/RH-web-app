package com.IT.SpringBootAngular.Service;

import com.IT.SpringBootAngular.Entitys.Attendance;
import com.IT.SpringBootAngular.Entitys.Employee;
import com.IT.SpringBootAngular.Entitys.HRadmin;
import com.IT.SpringBootAngular.Entitys.Response;
import com.IT.SpringBootAngular.Repo.EmployeeRepo;
import com.IT.SpringBootAngular.Repo.HRadminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.IT.SpringBootAngular.Repo.AttendanceRepo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private HRadminRepo hRadminRepo;

    public Response checkin(String id , Attendance attendance){
        attendanceRepo.save(attendance);
        Employee emp = employeeRepo.findById(id).orElse(null);
        if(emp !=null){
            emp.setAttendance(attendance);
            emp.setActive(true);
            employeeRepo.save(emp);
            return new Response("you are in");
        }
        return new Response("no employee");
    }
    public Response checkout(String id , Attendance attendance){
        attendanceRepo.save(attendance);
        Employee emp = employeeRepo.findById(id).orElse(null);
        if(emp!=null){
            emp.setAttendance(attendance);
            emp.setActive(false);
            employeeRepo.save(emp);
            return new Response("you are out");
        }
        return new Response("no employee");
    }

    //get attendance by the admin
    public Map<String, LocalDateTime> getAttendance(String admin_id){
        HRadmin admin = hRadminRepo.findById(admin_id).orElse(null);
        if(admin==null)
            return null;
        if(admin.getEmployees()==null)
            return null;
        Map<String , LocalDateTime> attendanceMap = new HashMap<>();
        for(Employee emp : admin.getEmployees()){
            String name = emp.getFirstName()+" "+emp.getLastName();
            if (emp.getAttendance()!=null)
                attendanceMap.put(name,emp.getAttendance().getTime());
        }
        return attendanceMap;
    }
}
