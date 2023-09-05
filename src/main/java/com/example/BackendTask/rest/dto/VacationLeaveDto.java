package com.example.BackendTask.rest.dto;

import com.example.BackendTask.entity.VacationType;
import com.example.BackendTask.rest.dto.common.RestDto;
import lombok.Data;

@Data
public class VacationLeaveDto extends RestDto {
    private EmployeeDto employee;
    private RequestDto request;
    private VacationTypeDto vacationType;
}
