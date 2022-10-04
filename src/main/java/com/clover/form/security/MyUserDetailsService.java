//package com.clover.form.security;
//
//import com.clover.form.model.Employee;
//import com.clover.form.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import javax.transaction.Transactional;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        System.out.println("Load username called "+username);
//        Employee employee = employeeRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//        System.out.println("Data>>>>>>>>>>>>> "+employee);
//        return CustomUserDetails.build(employee);
////        return new User("foo","foo",new ArrayList<>());
//    }
//}
