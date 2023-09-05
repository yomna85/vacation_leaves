package com.example.BackendTask.service;

import com.example.BackendTask.entity.Employee;
import com.example.BackendTask.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
private EmployeeRepository employeeRepository;
private EmployeeRoleService employeeRoleService;
@Autowired
private PasswordEncoder passwordEncoder;


    public Page<Employee> getAll(Integer page, Integer size) {
        return employeeRepository.findAll(PageRequest.of(page, size));
    }
    public Employee save(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }
    public void update(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
    }
    public Optional<Employee> getById(Integer id) {
        return employeeRepository.findById(id);
    }


    @Transactional
    public void delete(Employee employee) {
        employeeRoleService.deleteByEmployeeId(employee.getId());
        employeeRepository.delete(employee);
    }
    public Page<Employee> getAllEmployees(Integer page, Integer size) {
        return employeeRepository.findAllEmployees(PageRequest.of(page, size));
    }
    public Optional<Employee> getByUserName(String username) {
        return employeeRepository.findByUserName(username);
    }

}
