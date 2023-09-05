package com.example.BackendTask.rest.handler;

import com.example.BackendTask.constant.RequestStatusCodeEnum;
import com.example.BackendTask.entity.Employee;
import com.example.BackendTask.entity.Request;
import com.example.BackendTask.rest.dto.RequestDto;
import com.example.BackendTask.rest.dto.common.PaginatedResultDto;
import com.example.BackendTask.rest.entitymapper.RequestMapper;
import com.example.BackendTask.rest.entitymapper.common.PaginationMapper;
import com.example.BackendTask.rest.exception.ResourceNotFoundException;
import com.example.BackendTask.service.EmployeeRoleService;
import com.example.BackendTask.service.EmployeeService;
import com.example.BackendTask.service.RequestService;
import com.example.BackendTask.service.RequestStatusService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RequestHandler {
    private PaginationMapper paginationMapper;
    private RequestMapper requestMapper;
    private RequestService requestService;
    private RequestStatusService requestStatusService;
    private EmployeeService employeeService;
    private EmployeeRoleService employeeRoleService;

    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<Request> requestsPage = requestService.getAll(RequestStatusCodeEnum.NEW_REQUEST.getStatusCode(), page, size);
        List<RequestDto> dtos = requestMapper.toDto(requestsPage.getContent());
        PaginatedResultDto<RequestDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(requestsPage));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<?> getById(Integer id) {
        Request request = requestService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Request.class.getSimpleName(), id));
        RequestDto dto = requestMapper.toDto(request);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> save(RequestDto requestDto) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        Employee employee = employeeService.getByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), userName));
        Request request = requestMapper.toEntity(requestDto);
        request.setDate(LocalDate.now());
        request.setEmployee(employee);
        request.setRequestStatus(requestStatusService.getByCode(RequestStatusCodeEnum.NEW_REQUEST.getStatusCode()).get());
        requestService.save(request);
        RequestDto dto = requestMapper.toDto(request);
        return ResponseEntity.created(URI.create("/request/" + request.getId())).body(dto);
    }


}
