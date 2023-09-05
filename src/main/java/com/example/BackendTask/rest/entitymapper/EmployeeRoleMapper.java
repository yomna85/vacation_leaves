package com.example.BackendTask.rest.entitymapper;

import com.example.BackendTask.entity.EmployeeRole;
import com.example.BackendTask.rest.dto.EmployeeRoleDto;
import com.example.BackendTask.service.EmployeeService;
import com.example.BackendTask.service.RoleService;
import com.example.BackendTask.utils.HibernateUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)

public abstract class EmployeeRoleMapper {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleService roleService;
    @Mappings({
            @Mapping(source = "employee" , target = "employee" ),
            @Mapping(source = "role" , target = "role" )
    })
    public abstract EmployeeRoleDto toDto(EmployeeRole employeeRole);

    public abstract List<EmployeeRoleDto> toDto(List<EmployeeRole> list);

    @AfterMapping
    public void toDtoAfterMapping(EmployeeRole entity, @MappingTarget EmployeeRoleDto dto) {
        if (HibernateUtils.isConvertible(entity.getEmployee())) {
            dto.setEmployee(employeeMapper.toDto(entity.getEmployee()));
        }
        if (HibernateUtils.isConvertible(entity.getRole())) {
            dto.setRole(roleMapper.toDto(entity.getRole()));
        }
    }
    @InheritInverseConfiguration
    public abstract EmployeeRole toEntity(EmployeeRoleDto employeeRoleDto);

    public abstract List<EmployeeRole> toEntity(List<EmployeeRoleDto> list);

    @AfterMapping
    public void toEntityAfterMapping(EmployeeRoleDto dto, @MappingTarget EmployeeRole entity) {
        if (dto.getEmployee() != null) {
            entity.setEmployee(employeeService.getById(dto.getId()).get());
        }
        if (dto.getRole() != null) {
            entity.setRole(roleService.getById(dto.getId()).get());
        }
    }
    @InheritInverseConfiguration
    public abstract EmployeeRole updateEntityFromDto(EmployeeRoleDto dto, @MappingTarget EmployeeRole entity);

}
