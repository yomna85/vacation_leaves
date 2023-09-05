package com.example.BackendTask.rest.controller;

import com.example.BackendTask.rest.handler.EmployeeRoleHandler;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-role")
@AllArgsConstructor
public class EmployeeRoleController {
    private EmployeeRoleHandler employeeRoleHandler;

    @DeleteMapping("/{id}")
    @Operation(summary = "delete  By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return employeeRoleHandler.delete(id);
    }
}

