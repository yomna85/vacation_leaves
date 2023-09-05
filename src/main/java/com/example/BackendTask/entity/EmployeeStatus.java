package com.example.BackendTask.entity;

import com.example.BackendTask.entity.common.LookupEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "employee_status")
public class EmployeeStatus extends LookupEntity  {

}
