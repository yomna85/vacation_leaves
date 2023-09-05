package com.example.BackendTask.entity.common;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(of = "id", callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class JPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Integer version;

    @Column(name = "created_by", updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String updatedBy;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime updateDate;

    public JPAEntity() {
    }
    public JPAEntity(Integer id) {
        this.id=id;
    }
    public JPAEntity(Integer id,Integer version,String createdBy,String updatedBy,LocalDateTime createdDate,LocalDateTime updateDate) {
        this.id=id;
        this.version=version;
        this.createdBy=createdBy;
        this.updatedBy=updatedBy;
        this.createdDate=createdDate;
        this.updateDate=updateDate;
    }
}
