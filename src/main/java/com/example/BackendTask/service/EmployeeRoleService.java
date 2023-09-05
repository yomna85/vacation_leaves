package com.example.BackendTask.service;

import com.example.BackendTask.entity.Employee;
import com.example.BackendTask.entity.EmployeeRole;
import com.example.BackendTask.entity.Role;
import com.example.BackendTask.repository.EmployeeRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeRoleService {
    private EmployeeRoleRepository employeeRoleRepository;
    public EmployeeRole save(EmployeeRole employeeRole){
        return employeeRoleRepository.save(employeeRole);
    }

    public void deleteByEmployeeId(Integer employeeId) {
        List<EmployeeRole> employeeRoles = employeeRoleRepository.findByEmployeeId(employeeId);
        employeeRoleRepository.deleteAll(employeeRoles);
    }
    public List<Role> getRoleByUsername(String username){
        return employeeRoleRepository.findByUserName(username);
    }

    public void delete(EmployeeRole employeeRole) {
        employeeRoleRepository.delete(employeeRole);
    }

    public Page<EmployeeRole> getByEmployeeId(Integer employeeId, Integer page, Integer size){
        return employeeRoleRepository.findEmployeeRoleByEmployeeId(employeeId, PageRequest.of(page, size));
    }
//    public List<Role> getRoleByEmployeeCode(String employeeCode){
//        return employeeRoleRepository.findByEmployeeCode(employeeCode);
//    }

    public Optional<Integer> getRoleIdByEmployeeIdAndRoleId(Integer employeeId,Integer roleId){
        return employeeRoleRepository.findRoleIdByEmployeeIdAndRoleId(employeeId,roleId);
    }

    public Optional<EmployeeRole> getById(Integer id){
        return employeeRoleRepository.findById(id);
    }



    }

