package com.IT.SpringBootAngular.Service;

import com.IT.SpringBootAngular.Entitys.HRadmin;
import com.IT.SpringBootAngular.dto.EmployeeRequest;
import com.IT.SpringBootAngular.dto.HRAdminRequest;
import com.IT.SpringBootAngular.dto.SignupRequest;

public interface AuthService {
    boolean createUser(SignupRequest signupRequest);
    boolean createHRAdmin(HRAdminRequest hrAdminRequest);
    boolean addEmployee(EmployeeRequest employeeRequest);


}
