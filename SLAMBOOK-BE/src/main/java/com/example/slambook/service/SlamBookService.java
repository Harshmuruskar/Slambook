package com.example.slambook.service;

import com.example.slambook.dto.CreateSlamBookRequest;
import com.example.slambook.dto.SlamBookResponse;
import com.example.slambook.dto.UpdateSlamBookRequest;

public interface SlamBookService {
    SlamBookResponse create(CreateSlamBookRequest request);
    SlamBookResponse getById(Long id);
    SlamBookResponse update(Long id, UpdateSlamBookRequest request);
    void delete(Long id);
}
