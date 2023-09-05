package com.example.BackendTask.rest.controller;

import com.example.BackendTask.rest.dto.EmployeeDto;
import com.example.BackendTask.rest.dto.VacationLeaveDto;
import com.example.BackendTask.rest.handler.EmployeeHandler;
import com.example.BackendTask.rest.handler.VacationLeaveHandler;
import com.example.BackendTask.rest.validation.InsertValidation;
import com.example.BackendTask.rest.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
@AllArgsConstructor
@RequestMapping("/employee")
@Tag(name = "employee",description = "Rest API for Employee")
public class EmployeeController {
    private EmployeeHandler employeeHandler;
    private VacationLeaveHandler vacationLeaveHandler;

    @PostMapping
    @Operation(summary = "add employee", description = "this API to add new employee")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody EmployeeDto employeeDto) {
        return employeeHandler.save(employeeDto);
    }


    @GetMapping("/{id}/vacationLeave")
    @Operation(summary = "get all", description = "this API to get all Vacation Leave in pages")
    public ResponseEntity<?> getAllVacationLeaveByEmployeeId(@PathVariable(value = "id") Integer employeeId, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return vacationLeaveHandler.getAllVacationByEmployee(employeeId, page, size);

    }

    @GetMapping("/{id}")
    @Operation(summary = "get Vacation Leave by id", description = "this API to get Vacation Leave by id")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return vacationLeaveHandler.getById(id);
    }

    @PutMapping("/{id}/vacationLeave")
    @Operation(summary = "update vacation Leave", description = "this API to update an existing vacation Leave")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class) @RequestBody VacationLeaveDto vacationLeaveDto,
                                    @PathVariable Integer id) {
        return vacationLeaveHandler.update(id, vacationLeaveDto);
    }

    @DeleteMapping("/{id}/vacationLeave")
    @Operation(summary = "delete vacation Leave ", description = "this API to delete vacation Leave")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return vacationLeaveHandler.delete(id);
    }


}
