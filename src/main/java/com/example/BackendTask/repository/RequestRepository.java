package com.example.BackendTask.repository;

import com.example.BackendTask.entity.EmployeeStatus;
import com.example.BackendTask.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer> {
    @Query(value = "SELECT r from Request r JOIN FETCH r.requestStatus WHERE r.requestStatus.code=:status OR :status IS NULL ORDER BY r.id desc",
            countQuery = "SELECT COUNT(r) FROM Request r WHERE r.requestStatus.code=:status OR :status IS NULL")
    Page<Request> findAll(@Param("status")String status, Pageable pageable);

    @Query(value = "SELECT r from Request r JOIN FETCH r.requestStatus WHERE r.requestStatus.code <> :status ORDER BY r.id desc",
            countQuery = "SELECT COUNT(r) FROM Request r WHERE r.requestStatus.code <> :status ")
    Page<Request> findAllExceptNewRequest(@Param("status")String status, Pageable pageable);
}
