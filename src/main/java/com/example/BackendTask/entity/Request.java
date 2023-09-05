package com.example.BackendTask.entity;

import com.example.BackendTask.entity.common.JPAEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "request")
public class Request extends JPAEntity {
    @Column(name = "date")
    private LocalDate date;
    private LocalDate actionDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_status_id")
    private RequestStatus requestStatus;


}
