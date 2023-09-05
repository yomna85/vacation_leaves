package com.example.BackendTask.rest.entitymapper;

import com.example.BackendTask.entity.Role;
import com.example.BackendTask.rest.dto.RoleDto;
import com.example.BackendTask.rest.entitymapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends JPAEntityMapper<Role, RoleDto> {
}
