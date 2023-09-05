package com.example.BackendTask.entity;

import com.example.BackendTask.entity.common.JPAEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "employee")
public class Employee extends JPAEntity {
    private String userName;
    private String password;
    private String employeeCode;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String maritalStatus;
    private String mobileNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_status_id")
    private EmployeeStatus employeeStatus;






}
