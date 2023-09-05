package com.example.BackendTask.rest.entitymapper;

import com.example.BackendTask.entity.Employee;
import com.example.BackendTask.rest.dto.EmployeeDto;
import com.example.BackendTask.service.EmployeeStatusService;
import com.example.BackendTask.utils.HibernateUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)

public  abstract class EmployeeMapper {
    @Autowired
    private EmployeeStatusMapper employeeStatusMapper;
    @Autowired
    private EmployeeStatusService employeeStatusService;


    @Mappings({
            @Mapping(source = "employeeStatus", target = "employeeStatus", ignore = true),


    })

    public abstract EmployeeDto toDto(Employee employee);

    public abstract List<EmployeeDto> toDto(List<Employee> employees);

    @AfterMapping
    public void toDtoAfterMapping(Employee entity, @MappingTarget EmployeeDto dto) {
        if (HibernateUtils.isConvertible(entity.getEmployeeStatus())) {
            dto.setEmployeeStatus(employeeStatusMapper.toDto(entity.getEmployeeStatus()));
        }


    }
    @InheritInverseConfiguration
    public abstract Employee toEntity(EmployeeDto billDetailsDto);

    public abstract List<Employee> toEntity(List<EmployeeDto> list);
    @AfterMapping
    public void toEntityAfterMapping(EmployeeDto dto, @MappingTarget Employee entity) {
        if (dto.getEmployeeStatus() != null) {
            entity.setEmployeeStatus(employeeStatusService.getById(dto.getEmployeeStatus().getId()).get());
        }


    }
    @InheritInverseConfiguration
    public abstract Employee updateEntityFromDto(EmployeeDto billDetailsDto, @MappingTarget Employee billDetails);




}
