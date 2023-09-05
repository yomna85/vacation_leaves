package com.example.BackendTask.repository;

import com.example.BackendTask.entity.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestStatusRepository extends JpaRepository<RequestStatus,Integer> {
    @Query(value = "SELECT r FROM RequestStatus r WHERE r.code= :code")
    Optional<RequestStatus> findByCode(@Param("code") String code);
}