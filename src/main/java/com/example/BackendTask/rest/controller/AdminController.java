package com.example.BackendTask.rest.controller;

import com.example.BackendTask.rest.dto.EmployeeDto;
import com.example.BackendTask.rest.handler.EmployeeHandler;
import com.example.BackendTask.rest.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Tag(name = "admin", description = "Rest Api For Admin")
public class AdminController {

    EmployeeHandler employeeHandler;



    @GetMapping
    @Operation(summary = "get all", description = "this API to get all employee in pages")
    public ResponseEntity<?> getAllEmployee( @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return employeeHandler.getAllEmployee( page, size);

    }

    @GetMapping("/{id}")
    @Operation(summary = "get Employee by id", description = "this API to get employee by id")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return employeeHandler.getById(id);
    }

    @PutMapping("/{id}/employee")
    @Operation(summary = "update employee", description = "this API to update an existing employee")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class) @RequestBody EmployeeDto employeeDto,
                                    @PathVariable Integer id) {
        return employeeHandler.update(id, employeeDto);
    }

    @DeleteMapping("/{id}/employee")
    @Operation(summary = "delete employee ", description = "this API to delete employee")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return employeeHandler.delete(id);
    }

}
