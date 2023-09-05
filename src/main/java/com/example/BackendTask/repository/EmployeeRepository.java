package com.example.BackendTask.repository;

import com.example.BackendTask.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Transactional(readOnly = true)
    @Query(value = "select e from Employee e LEFT JOIN FETCH e.employeeStatus",
            countQuery = "SELECT count(e) FROM  Employee e")
    Page<Employee> findAll(Pageable pageable);

    @Query(value = "select e from Employee e LEFT JOIN FETCH e.employeeStatus where e.userName= :userName")
    Optional<Employee> findByUserName(@Param("userName") String userName);

    @Query(value = "SELECT e FROM Employee e JOIN FETCH e.employeeStatus",
            countQuery = "SELECT COUNT(e) FROM Employee e ")
    Page<Employee> findAllEmployees( Pageable pageable);


}
