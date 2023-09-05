package com.example.BackendTask.security;




import com.example.BackendTask.entity.Employee;
import com.example.BackendTask.entity.Role;
import com.example.BackendTask.repository.EmployeeRepository;
import com.example.BackendTask.repository.EmployeeRoleRepository;
import com.example.BackendTask.service.EmployeeRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    private EmployeeRepository employeeRepository;
    private EmployeeRoleService employeeRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("load user by username");
        Employee employee = employeeRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        List<Role> roles= employeeRoleService.getRoleByUsername(username);
        if (roles.isEmpty()){
            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password(employee.getPassword())
                    .disabled(true)
                    .build();
        }
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getCode()))
                .collect(Collectors.toList());
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(employee.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
