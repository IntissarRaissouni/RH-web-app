package com.IT.SpringBootAngular.Controllers;

import com.IT.SpringBootAngular.Service.AuthService;
import com.IT.SpringBootAngular.Service.HRadminService;
import com.IT.SpringBootAngular.dto.HRAdminRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/singup")
public class HomePageController {
    private final AuthService authService;
    @Autowired
    private HRadminService hrAdminService;

    @Autowired
    public HomePageController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<String> signupHRAdmin(@RequestBody HRAdminRequest hrAdminRequest) {
        boolean isHRAdminCreated = authService.createHRAdmin(hrAdminRequest);

        if (isHRAdminCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body("HR Admin created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create HR Admin");
        }
    }
    @DeleteMapping("/clear")
    public String clear(){
        return "everything is clear";
    }
}