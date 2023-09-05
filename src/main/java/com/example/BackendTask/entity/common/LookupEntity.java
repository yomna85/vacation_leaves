package com.example.BackendTask.entity.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@EqualsAndHashCode(of = { "englishName", "code"}, callSuper = true)
@ToString(of = { "englishName", "enabled", "code"}, callSuper = true)
public class LookupEntity extends JPAEntity{
    private String englishName;

    private String code;

    @Column(name = "is_enabled")
    private Boolean enabled;
}
