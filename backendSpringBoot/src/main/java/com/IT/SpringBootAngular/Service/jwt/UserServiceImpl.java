package com.IT.SpringBootAngular.Service.jwt;

import com.IT.SpringBootAngular.Entitys.Employee;
import com.IT.SpringBootAngular.Entitys.HRadmin;
import com.IT.SpringBootAngular.Repo.EmployeeRepo;
import com.IT.SpringBootAngular.Repo.HRadminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {
    private final HRadminRepo hRadminRepo;
    private final EmployeeRepo employeeRepo;

    @Autowired
    public UserServiceImpl(HRadminRepo hRadminRepo, EmployeeRepo employeeRepo) {
        this.hRadminRepo = hRadminRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Write logic to fetch customer from DB
        HRadmin hRadmin = hRadminRepo.findByEmail(email).orElse(null);
        if(hRadmin != null){
            Set<GrantedAuthority> authorities = hRadmin.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                    .collect(Collectors.toSet());
            return new HRadmin(
                    hRadmin.getEmail(),
                    hRadmin.getPassword(),
                    authorities,
                    hRadmin.getId()
                    );
        }
        Employee employee = employeeRepo.findByEmail(email).orElse(null);
        if(employee!= null){
            Set<GrantedAuthority> authorities = employee.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                    .collect(Collectors.toSet());
            return new Employee(
                    employee.getEmail(),
                    employee.getPassword(),
                    authorities,
                    employee.get_id()
                    );
        }
        return null;
    }
}