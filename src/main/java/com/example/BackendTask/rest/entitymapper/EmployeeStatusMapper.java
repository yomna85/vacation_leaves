package com.example.BackendTask.rest.entitymapper;

import com.example.BackendTask.entity.EmployeeStatus;
import com.example.BackendTask.rest.dto.EmployeeStatusDto;
import com.example.BackendTask.rest.entitymapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeStatusMapper extends JPAEntityMapper<EmployeeStatus, EmployeeStatusDto> {

}
