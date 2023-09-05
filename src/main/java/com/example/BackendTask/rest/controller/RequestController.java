package com.example.BackendTask.rest.controller;

import com.example.BackendTask.rest.dto.RequestDto;
import com.example.BackendTask.rest.dto.VacationLeaveDto;
import com.example.BackendTask.rest.handler.EmployeeHandler;
import com.example.BackendTask.rest.handler.RequestHandler;
import com.example.BackendTask.rest.handler.VacationLeaveHandler;
import com.example.BackendTask.rest.validation.InsertValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/request")
@AllArgsConstructor
@Tag(name = "Request", description = "Rest Api For Request")
public class RequestController {
    private RequestHandler requestHandler;
    private EmployeeHandler employeeHandler;
    private VacationLeaveHandler vacationLeaveHandler;


    @GetMapping
    @Operation(summary = "Get All Requests", description = "this API to get all request in pages")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return requestHandler.getAll(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Request", description = "this API to get request by id")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return requestHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add Request", description = "this API to add new request")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody RequestDto requestDto) {
        return requestHandler.save(requestDto);
    }


    @PostMapping("/{id}/vacation_Leave")
    @Operation(summary = "add Vacation Leave", description = "this API to add new Vacation Leave")
    public ResponseEntity<?> addVacation(@PathVariable(value = "id") Integer id, @Validated(InsertValidation.class) @RequestBody VacationLeaveDto vacationLeaveDto) {
        return vacationLeaveHandler.save(id, vacationLeaveDto);

}}
