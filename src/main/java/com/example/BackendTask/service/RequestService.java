package com.example.BackendTask.service;

import com.example.BackendTask.entity.Request;
import com.example.BackendTask.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RequestService {
    private RequestRepository requestRepository;

    public Page<Request> getAll(String status, Integer page, Integer size) {
        return requestRepository.findAllExceptNewRequest(status, PageRequest.of(page, size));
    }
    public Request getReferenceById(Integer id) {
        return requestRepository.getById(id);
    }
    public Optional<Request> getById(Integer id) {
        return requestRepository.findById(id);
    }

    public Request save(Request request) {
        return requestRepository.save(request);
    }

    public Request update(Request request) {
        return requestRepository.save(request);
    }

    public void delete(Request request) {
        requestRepository.delete(request);
    }

}
