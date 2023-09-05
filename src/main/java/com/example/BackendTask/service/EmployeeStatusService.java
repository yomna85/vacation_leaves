package com.example.BackendTask.service;

import com.example.BackendTask.entity.EmployeeStatus;
import com.example.BackendTask.repository.EmployeeStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeStatusService {
    private EmployeeStatusRepository employeeStatusRepository;



    public Page<EmployeeStatus> getAll(Integer page, Integer size) {
        return employeeStatusRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<EmployeeStatus> getById(Integer id) {
        return employeeStatusRepository.findById(id);
    }

    public EmployeeStatus save(EmployeeStatus employeeStatus) {
        return employeeStatusRepository.save(employeeStatus);
    }

    public EmployeeStatus Update(EmployeeStatus employeeStatus) {
        return employeeStatusRepository.save(employeeStatus);
    }

    public void delete(EmployeeStatus employeeStatus) {
        employeeStatusRepository.delete(employeeStatus);
    }

    public Optional<EmployeeStatus> getByCode(String code) {
        return employeeStatusRepository.findByCode(code);
    }


}
