package com.clover.form.service;

import com.clover.form.model.Employee;
import com.clover.form.model.UserDetailsImpl;
import com.clover.form.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginDetailsServiceImpl implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("query.......");
        Employee employee = employeeRepository.findByEmail(username)
                .orElseThrow(
                        ()->new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(employee);
    }
}
