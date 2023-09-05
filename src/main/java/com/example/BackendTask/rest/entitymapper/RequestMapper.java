package com.example.BackendTask.rest.entitymapper;

import com.example.BackendTask.entity.Request;
import com.example.BackendTask.entity.VacationLeave;
import com.example.BackendTask.rest.dto.RequestDto;
import com.example.BackendTask.service.EmployeeService;
import com.example.BackendTask.service.RequestStatusService;
import com.example.BackendTask.service.VacationLeaveService;
import com.example.BackendTask.service.VacationTypeService;
import com.example.BackendTask.utils.HibernateUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public  abstract class RequestMapper {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeService employeeService;


    @Autowired
    private RequestStatusMapper requestStatusMapper;
    @Autowired
    private RequestStatusService requestStatusService;

    @Mappings({
            @Mapping(source = "requestStatus", target = "requestStatus", ignore = true),
            @Mapping(source = "employee", target = "employee", ignore = true)


    })

    public abstract RequestDto toDto(Request request);

    public abstract List<RequestDto> toDto(List<Request> requestList);

    @AfterMapping
    public void toDtoAfterMapping(Request entity, @MappingTarget RequestDto dto) {
        if (HibernateUtils.isConvertible(entity.getRequestStatus())) {
            dto.setRequestStatus(requestStatusMapper.toDto(entity.getRequestStatus()));
        }
        if (HibernateUtils.isConvertible(entity.getEmployee())) {
            dto.setEmployee(employeeMapper.toDto(entity.getEmployee()));
        }


    }
    @InheritInverseConfiguration
    public abstract Request toEntity(RequestDto requestDto);

    public abstract List<Request> toEntity(List<RequestDto> requestDtoList);


    @AfterMapping
    public void toEntityAfterMapping(RequestDto dto, @MappingTarget Request entity) {
        if (dto.getRequestStatus() != null) {
            entity.setRequestStatus(requestStatusService.getById(dto.getRequestStatus().getId()).get());
        }
        if (dto.getEmployee() != null) {
            entity.setEmployee(employeeService.getById(dto.getEmployee().getId()).get());
        }


    }
    @InheritInverseConfiguration
    public abstract Request updateEntityFromDto(RequestDto requestDto, @MappingTarget Request request);

}
