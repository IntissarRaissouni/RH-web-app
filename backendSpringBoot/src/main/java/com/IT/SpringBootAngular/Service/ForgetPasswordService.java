package com.IT.SpringBootAngular.Service;

import com.IT.SpringBootAngular.Entitys.Employee;
import com.IT.SpringBootAngular.Entitys.HRadmin;
import com.IT.SpringBootAngular.Repo.EmployeeRepo;
import com.IT.SpringBootAngular.Repo.HRadminRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForgetPasswordService {
    @Autowired
    private HRadminRepo hradminRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private JavaMailSender mailSender;


    public void sendResetPasswordEmail(String email) throws MessagingException {
        // Check if the email belongs to an employee
        Optional<Employee> employeeOptional = employeeRepo.findByEmail(email);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            // Send the reset password email
            sendEmail(employee.getEmail());
            return;
        }
        // Check if the email belongs to an admin
        Optional<HRadmin> adminOptional = hradminRepo.findByEmail(email);
        if (adminOptional.isPresent()) {
            HRadmin admin = adminOptional.get();
            sendEmail(admin.getEmail());
            return;
        }
        // Email not found, handle accordingly
    }
    private void sendEmail(String email) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(email);
        helper.setSubject("Password Reset");
        helper.setText(
                """
                      <div>
                        <a href="http://localhost:8087/set-password?email=%s" target="_blank">Click this link to set password</a>
                      </div>
                """.formatted(email),true);
        mailSender.send(mimeMessage);
    }
}
