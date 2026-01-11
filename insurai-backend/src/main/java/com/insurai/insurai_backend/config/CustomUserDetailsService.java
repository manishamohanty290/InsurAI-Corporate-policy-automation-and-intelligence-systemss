package com.insurai.insurai_backend.config;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.insurai.insurai_backend.model.Admin;
import com.insurai.insurai_backend.model.Employee;
import com.insurai.insurai_backend.repository.AdminRepository;
import com.insurai.insurai_backend.repository.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final EmployeeRepository employeeRepository;

    public CustomUserDetailsService(AdminRepository adminRepository, EmployeeRepository employeeRepository) {
        this.adminRepository = adminRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {

        Admin admin = adminRepository.findByEmail(email).orElse(null);

        if (admin != null) {
            return new User(
                    admin.getEmail(),
                    admin.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(admin.getSpringRole()))
            );
        }

        Employee emp = employeeRepository.findByEmail(email).orElse(null);

        if (emp != null) {

            return new User(
                    emp.getEmail(),
                    emp.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_" + emp.getRole().name()))
            );
        }

        throw new UsernameNotFoundException("User not found: " + email);
    }
}
