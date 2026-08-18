package com.example.slambook.controller;

import com.example.slambook.dto.ApiResponse;
import com.example.slambook.dto.CreateSlamBookRequest;
import com.example.slambook.dto.SlamBookResponse;
import com.example.slambook.dto.UpdateSlamBookRequest;
import com.example.slambook.service.SlamBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/slam")
@RequiredArgsConstructor
public class SlamBookController {

    private final SlamBookService slamBookService;

    @PostMapping
    public ResponseEntity<ApiResponse<SlamBookResponse>> create(@Valid @RequestBody CreateSlamBookRequest request) {
        SlamBookResponse response = slamBookService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("SLAM Book created successfully", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SlamBookResponse>> getById(@PathVariable Long id) {
        SlamBookResponse response = slamBookService.getById(id);
        return ResponseEntity.ok(ApiResponse.success("SLAM Book retrieved successfully", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SlamBookResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSlamBookRequest request) {
        SlamBookResponse response = slamBookService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success("SLAM Book updated successfully", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        slamBookService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("SLAM Book deleted successfully", null));
    }
}
