package com.example.BackendTask.repository;

import com.example.BackendTask.entity.EmployeeRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.BackendTask.entity.Role;
import com.example.BackendTask.entity.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Integer> {
    @Query(value = "SELECT er FROM EmployeeRole er JOIN FETCH er.role WHERE er.employee.id= :employeeId",
            countQuery = "SELECT COUNT(er) FROM EmployeeRole er WHERE er.employee.id= :employeeId")
    Page<EmployeeRole> findEmployeeRoleByEmployeeId(@Param("employeeId") Integer employeeId, Pageable pageable);

    @Query(value = "SELECT er FROM EmployeeRole er WHERE er.id = :id")
    Optional<EmployeeRole> findById(@Param("id") Integer id);

    @Query(value = "SELECT er FROM EmployeeRole er JOIN FETCH er.role WHERE er.employee.id= :employeeId",
            countQuery = "SELECT COUNT(er) FROM EmployeeRole er WHERE er.employee.id= :employeeId")
    List<EmployeeRole> findByEmployeeId(@Param("employeeId") Integer employeeId);

    @Query(value = "SELECT er.role.id FROM EmployeeRole er WHERE er.employee.id= :employeeId AND er.role.id= :roleId")
    Optional<Integer> findRoleIdByEmployeeIdAndRoleId(@Param("employeeId") Integer employeeId, @Param("roleId") Integer roleId);

//    @Query(value = "SELECT er.role FROM EmployeeRole er WHERE er.employee.employeeCode= :employeeCode")
//    List<Role> findByEmployeeCode(@Param("employeeCode") String employeeCode);
    @Query(value = "SELECT ur.role FROM EmployeeRole ur WHERE ur.employee.userName= :userName")
    List<Role> findByUserName(@Param("userName") String userName);



}
