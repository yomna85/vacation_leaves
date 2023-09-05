package com.example.BackendTask.rest.handler;

import com.example.BackendTask.entity.Employee;
import com.example.BackendTask.entity.EmployeeRole;
import com.example.BackendTask.entity.Role;
import com.example.BackendTask.rest.dto.EmployeeRoleDto;
import com.example.BackendTask.rest.dto.RoleDto;
import com.example.BackendTask.rest.dto.common.PaginatedResultDto;
import com.example.BackendTask.rest.entitymapper.EmployeeRoleMapper;
import com.example.BackendTask.rest.entitymapper.RoleMapper;
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
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class EmployeeRoleHandler {
    EmployeeRoleService employeeRoleService;
    EmployeeService employeeService;
    EmployeeRoleMapper employeeRoleMapper;
    RoleMapper roleMapper;
    RoleService roleService;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> save(Integer employeeId, RoleDto dto) {
        Employee employee = employeeService.getById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), employeeId));
        Role role = roleService.getByCode(dto.getCode());
        Optional<Integer> existingRole = employeeRoleService.getRoleIdByEmployeeIdAndRoleId(employeeId, role.getId());
        if (existingRole.isPresent()) {
            throw new ResourceAlreadyExistsException(EmployeeRole.class.getSimpleName(),
                    "role", dto.getEnglishName(), ErrorCodes.DUPLICATE_ROLE);
        }
        EmployeeRole employeeRole = new EmployeeRole();
        employeeRole.setEmployee(employee);
        employeeRole.setRole(role);
        employeeRoleService.save(employeeRole);
        EmployeeRoleDto employeeRoleDto = employeeRoleMapper.toDto(employeeRole);
        return ResponseEntity.created(URI.create("/employee/" + employeeId + "/role")).body(employeeRoleDto);
    }
    public ResponseEntity<?> delete(Integer id) {
        EmployeeRole employeeRole = employeeRoleService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), id));
        try {
            employeeRoleService.delete(employeeRole);
        } catch (Exception exception) {
            throw new ResourceRelatedException(EmployeeRole.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE);
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> getEmployeeRoles(Integer employeeId, Integer page, Integer size) {
        employeeService.getById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), employeeId));
        Page<EmployeeRole> employeeRolesPage = employeeRoleService.getByEmployeeId(employeeId, page, size);
        List<EmployeeRoleDto> employeeRoleDtoList = employeeRoleMapper.toDto(employeeRolesPage.getContent());
        PaginatedResultDto<EmployeeRoleDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(employeeRoleDtoList);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(employeeRolesPage));
        return ResponseEntity.ok(paginatedResult);
    }

}
