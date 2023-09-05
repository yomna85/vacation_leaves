package com.example.BackendTask.service;

import com.example.BackendTask.entity.RequestStatus;
import com.example.BackendTask.repository.RequestStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RequestStatusService {
    private RequestStatusRepository requestStatusRepository;

    public Page<RequestStatus> getAll(Integer page, Integer size) {
        return requestStatusRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<RequestStatus> getById(Integer id) {
        return requestStatusRepository.findById(id);
    }

    public RequestStatus save(RequestStatus requestStatus) {
        return requestStatusRepository.save(requestStatus);
    }

    public RequestStatus Update(RequestStatus requestStatus) {
        return requestStatusRepository.save(requestStatus);
    }

    public void delete(RequestStatus requestStatus) {
        requestStatusRepository.delete(requestStatus);
    }

    public Optional<RequestStatus> getByCode(String code) {
        return requestStatusRepository.findByCode(code);}
}
