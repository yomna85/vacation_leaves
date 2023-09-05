package com.example.BackendTask.rest.controller;

import com.example.BackendTask.rest.dto.VacationLeaveDto;
import com.example.BackendTask.rest.handler.VacationLeaveHandler;
import com.example.BackendTask.rest.validation.InsertValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/VacationLeave")
@AllArgsConstructor
@Tag(name = "VacationLeave", description = "Rest Api For VacationLeave")
public class VacationLeaveController {
    private VacationLeaveHandler vacationLeaveHandler;

//
//    @PostMapping("/{id}/vacation_Leave")
//    @Operation(summary = "add Vacation Leave", description = "this API to add new Vacation Leave")
//    public ResponseEntity<?> addVacation(@PathVariable(value = "id") Integer id, @Validated(InsertValidation.class) @RequestBody VacationLeaveDto vacationLeaveDto) {
//        return vacationLeaveHandler.save(id, vacationLeaveDto);
//
//    }


}

