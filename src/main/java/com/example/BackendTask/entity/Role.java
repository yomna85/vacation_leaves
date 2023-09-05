package com.example.BackendTask.entity;

import com.example.BackendTask.entity.common.LookupEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Data
public class Role extends LookupEntity {
}
