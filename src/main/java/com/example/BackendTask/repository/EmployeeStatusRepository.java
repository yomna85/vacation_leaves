package com.example.BackendTask.repository;

import com.example.BackendTask.entity.EmployeeStatus;
import com.example.BackendTask.rest.entitymapper.EmployeeStatusMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmployeeStatusRepository extends JpaRepository<EmployeeStatus,Integer>  {
    @Query(value = "SELECT es FROM EmployeeStatus es WHERE es.code= :code")
    Optional<EmployeeStatus> findByCode(@Param("code") String code);
}
