package com.example.BackendTask.rest.handler;

import com.example.BackendTask.entity.Role;
import com.example.BackendTask.rest.dto.RoleDto;
import com.example.BackendTask.rest.dto.common.PaginatedResultDto;
import com.example.BackendTask.rest.entitymapper.RoleMapper;
import com.example.BackendTask.rest.entitymapper.common.PaginationMapper;
import com.example.BackendTask.rest.exception.ErrorCodes;
import com.example.BackendTask.rest.exception.ResourceNotFoundException;
import com.example.BackendTask.rest.exception.ResourceRelatedException;
import com.example.BackendTask.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
@AllArgsConstructor
public class RoleHandler {
    private RoleService roleService;
    private RoleMapper roleMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<Role> rolePage = roleService.getAll(page, size);
        List<RoleDto> dtos = roleMapper.toDto(rolePage.getContent());
        PaginatedResultDto<RoleDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(dtos);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(rolePage));
        return ResponseEntity.ok(paginatedResult);
    }


    public ResponseEntity<?> save(RoleDto roleDto) {
        RoleDto roleDtoCreated = roleMapper.toDto(roleService.save(roleMapper.toEntity(roleDto)));
        return ResponseEntity.created(URI.create("/role/" + roleDto.getId())).body(roleDtoCreated);

    }

    public ResponseEntity<?> getById(Integer id) {
        Role role = roleService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), id));
        return ResponseEntity.ok(roleMapper.toDto(role));
    }

    public ResponseEntity<?> delete(Integer id) {
        Role role = roleService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), id));
        try {
            roleService.delete(role);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Role.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE);
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> update(Integer id, RoleDto roleDto) {
        Role role = roleService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), id));
        Role entity = roleMapper.updateEntityFromDto(roleDto, role);
        roleService.update(entity);
        return ResponseEntity.ok().build();
    }
}
