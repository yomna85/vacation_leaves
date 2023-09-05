package com.example.BackendTask.repository;

import com.example.BackendTask.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query(value = " SELECT r FROM Role r WHERE r.enabled = :enabled")
    Page<Role> findByEnabled(@Param("enabled") Boolean enabled , Pageable pageable );

    @Query(value = "SELECT r FROM Role r WHERE r.id= :roleId")
    Role getById(@Param("roleId") Integer roleId);

    @Query(value = "SELECT r FROM Role r WHERE r.code= :code")
    Role findByCode(@Param("code") String code);
}
