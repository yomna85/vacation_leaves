package com.example.BackendTask.service;

import com.example.BackendTask.entity.Role;
import com.example.BackendTask.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;

    public Page<Role> getAll(Integer page, Integer size) {
        return roleRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Role> getAllByEnabled(Boolean enabled ,Integer page, Integer size) {
        return roleRepository.findByEnabled(enabled,PageRequest.of(page, size));
    }

    public Role getByIdLazy(Integer id) {
        return roleRepository.getById(id);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> getById(Integer id) {
        return roleRepository.findById(id);
    }

    public void update(Role role) {
        roleRepository.save(role);
    }

    public void delete(Role role) {
        roleRepository.delete(role);
    }

    public Role getReferenceById(Integer id) {
        return roleRepository.getById(id);
    }

    public Role getByRoleId(Integer roleId){
        return roleRepository.getById(roleId);
    }

    public Role getByCode(String code){
        return roleRepository.findByCode(code);
    }
}
