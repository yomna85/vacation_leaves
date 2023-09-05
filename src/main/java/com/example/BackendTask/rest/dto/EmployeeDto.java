package com.example.BackendTask.rest.dto;

import com.example.BackendTask.rest.dto.common.RestDto;
import com.example.BackendTask.rest.validation.InsertValidation;
import com.example.BackendTask.rest.validation.UpdateValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Data
public class EmployeeDto extends RestDto {

    private String userName;

    private String password;

    private String employeeCode;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String gender;
    private String maritalStatus;

    private String phoneNumber;
    private EmployeeStatusDto employeeStatus;




}
