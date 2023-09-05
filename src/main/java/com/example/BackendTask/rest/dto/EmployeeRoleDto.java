package com.example.BackendTask.rest.dto;

import com.example.BackendTask.rest.dto.common.RestDto;
import lombok.Data;

@Data
public class EmployeeRoleDto extends RestDto {
    private EmployeeDto employee;
    private RoleDto role;
}
