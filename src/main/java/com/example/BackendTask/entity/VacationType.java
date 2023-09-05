package com.example.BackendTask.entity;

import com.example.BackendTask.entity.common.LookupEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vacation_type")
public class VacationType extends LookupEntity {

}
