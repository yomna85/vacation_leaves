package com.example.BackendTask.entity;


import com.example.BackendTask.entity.common.LookupEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "request_status")
public class RequestStatus extends LookupEntity {
}
