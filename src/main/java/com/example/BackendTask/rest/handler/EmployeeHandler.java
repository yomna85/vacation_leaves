package com.example.BackendTask.rest.handler;

import com.example.BackendTask.entity.Employee;
import com.example.BackendTask.entity.EmployeeRole;
import com.example.BackendTask.entity.Role;
import com.example.BackendTask.rest.dto.EmployeeDto;
import com.example.BackendTask.rest.dto.common.PaginatedResultDto;
import com.example.BackendTask.rest.entitymapper.EmployeeMapper;
import com.example.BackendTask.rest.entitymapper.common.PaginationMapper;
import com.example.BackendTask.rest.exception.ErrorCodes;
import com.example.BackendTask.rest.exception.ResourceAlreadyExistsException;
import com.example.BackendTask.rest.exception.ResourceNotFoundException;
import com.example.BackendTask.rest.exception.ResourceRelatedException;
import com.example.BackendTask.service.EmployeeRoleService;
import com.example.BackendTask.service.EmployeeService;
import com.example.BackendTask.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class EmployeeHandler {
    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;
    private RoleService roleService;
    private EmployeeRoleService employeeRoleService;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<Employee> employeesPage = employeeService.getAll(page, size);
        List<EmployeeDto> employeesDtoList = employeeMapper.toDto(employeesPage.getContent());
        PaginatedResultDto<EmployeeDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(employeesDtoList);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(employeesPage));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> save(EmployeeDto dto) {
       Optional<Employee> existedByUsername = employeeService.getByUserName(dto.getUserName());
        Role role = roleService.getByCode("EMPLOYEE");
        if (existedByUsername.isPresent()) {
            throw new ResourceAlreadyExistsException(Employee.class.getSimpleName(),
                    "username", dto.getUserName(), ErrorCodes.DUPLICATE_USERNAME);
        }
        Employee employee = employeeMapper.toEntity(dto);
        Employee savedEmployee = employeeService.save(employee);
        EmployeeRole employeeRole = new EmployeeRole();
        employeeRole.setEmployee(savedEmployee);
        employeeRole.setRole(role);
        employeeRoleService.save(employeeRole);
        EmployeeDto employeeDto = employeeMapper.toDto(employee);
        return ResponseEntity.created(URI.create("/employee/" + employee.getId())).body(employeeDto);
    }



    public ResponseEntity<?> update(Integer id, EmployeeDto dto) {
        Employee employee = employeeService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), id));
        Optional<Employee> existedByUsername = employeeService.getByUserName(dto.getUserName());

        if (existedByUsername.isPresent() && !existedByUsername.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException(Employee.class.getSimpleName(),
                    "username", dto.getUserName(), ErrorCodes.DUPLICATE_USERNAME);
        }

        Employee entity = employeeMapper.updateEntityFromDto(dto, employee);
        employeeService.update(entity);
        return ResponseEntity.ok().build();

    }
//    public ResponseEntity<?> update( EmployeeDto dto) {
//        String employeeCode = SecurityContextHolder.getContext().getAuthentication().getName();
//        Employee employee = employeeService.getByCode(employeeCode)
//                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), employeeCode));
//        Optional<Employee> existedByEmployeeCode = employeeService.getByCode(dto.getEmployeeCode());
//
//        if (existedByEmployeeCode.isPresent() && !existedByEmployeeCode.get().getId().equals(employeeCode)) {
//            throw new ResourceAlreadyExistsException(Employee.class.getSimpleName(),
//                    "employeeCode", dto.getEmployeeCode(), ErrorCodes.DUPLICATE_RESOURCE);
//        }
//
//        Employee entity = employeeMapper.updateEntityFromDto(dto, employee);
//        employeeService.update(entity);
//        return ResponseEntity.ok().build();
//    }

    public ResponseEntity<?> getById(Integer id) {
        Employee employee = employeeService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), id));
        return ResponseEntity.ok(employeeMapper.toDto(employee));
    }

    public ResponseEntity<?> getAllInfo() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee = employeeService.getByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), userName));
        EmployeeDto employeeDto = employeeMapper.toDto(employee);
        return ResponseEntity.ok(employeeDto);
    }

    public ResponseEntity<?> delete(Integer id) {
        Employee employee = employeeService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), id));
        try {
            employeeService.delete(employee);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Employee.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE);
        }
        return ResponseEntity.noContent().build();
    }
    public ResponseEntity<?> getAllEmployee(Integer page, Integer size) {
        Page<Employee> employeePage =  employeeService.getAllEmployees(page, size);
        List<EmployeeDto> employeeDtoList =  employeeMapper.toDto(employeePage.getContent());
        PaginatedResultDto<EmployeeDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(employeeDtoList);
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(employeePage));
        return ResponseEntity.ok(paginatedResultDto);
    }



}
