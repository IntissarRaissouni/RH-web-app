package com.IT.SpringBootAngular.Controllers;

import com.IT.SpringBootAngular.Service.ForgetPasswordService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forgot-password")
public class ForgetPassword {
    @Autowired
    private ForgetPasswordService forgetPasswordService;

    @PutMapping
    public ResponseEntity<String> sendResetPasswordEmail(@RequestParam String email) throws MessagingException {
        forgetPasswordService.sendResetPasswordEmail(email);
        return ResponseEntity.ok("Reset password email sent successfully.");
    }
}
