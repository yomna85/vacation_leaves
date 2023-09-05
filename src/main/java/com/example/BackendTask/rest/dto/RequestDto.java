package com.example.BackendTask.rest.dto;

import com.example.BackendTask.rest.dto.common.RestDto;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RequestDto extends RestDto {
    private LocalDate date;
    private LocalDate actionDate;
    private  RequestStatusDto requestStatus;
    private EmployeeDto employee;


}
