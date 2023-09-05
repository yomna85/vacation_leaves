package com.example.BackendTask.rest.entitymapper;

import com.example.BackendTask.entity.RequestStatus;
import com.example.BackendTask.rest.dto.RequestStatusDto;
import com.example.BackendTask.rest.entitymapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RequestStatusMapper extends JPAEntityMapper<RequestStatus, RequestStatusDto> {

}
