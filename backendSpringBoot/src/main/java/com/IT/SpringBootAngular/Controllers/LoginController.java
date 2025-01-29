package com.IT.SpringBootAngular.Controllers;

import com.IT.SpringBootAngular.Repo.HRadminRepo;
import com.IT.SpringBootAngular.Service.jwt.UserServiceImpl;
import com.IT.SpringBootAngular.dto.LoginRequest;
import com.IT.SpringBootAngular.dto.LoginResponse;
import com.IT.SpringBootAngular.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final JwtUtil jwtUtil;
    private final HRadminRepo hRadminRepo;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, UserServiceImpl userService, JwtUtil jwtUtil, HRadminRepo hRadminRepo) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.hRadminRepo = hRadminRepo;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails userDetails;
        try {
            userDetails= userService.loadUserByUsername(loginRequest.getEmail());
        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(jwt,getRolesFromUserDetails(userDetails)));
}

    private List<String> getRolesFromUserDetails(UserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}