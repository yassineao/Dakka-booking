package com.Yassine.dev.api.termine.controller;


import com.Yassine.dev.api.termine.dto.TermineCreateRequest;
import com.Yassine.dev.api.termine.dto.TermineResponse;
import com.Yassine.dev.api.termine.dto.TermineService;
import com.Yassine.dev.api.termine.dto.TermineUpdateRequest;
import com.Yassine.dev.api.termine.enums.Status;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/termine")
public class TermineController {

    private final TermineService service;

    public TermineController(TermineService service) {
        this.service = service;
    }

    @PostMapping
    public TermineResponse create(@Valid @RequestBody TermineCreateRequest request) {


        return service.create(request);
    }

    @GetMapping
    public List<TermineResponse> getAll() {
        System.out.println("=====================================");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TermineResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PatchMapping("/{id}")
    public TermineResponse update(@PathVariable UUID id,
                                  @RequestBody TermineUpdateRequest request) {
        return service.update(id, request);
    }
    @PatchMapping("/batch")
    public List<TermineResponse> updateBatch(@RequestBody List<TermineUpdateRequest> requests) {
        return service.updateBatch(requests);
    }

    @PatchMapping("/{id}/status")
    public TermineResponse updateStatus(@PathVariable UUID id,
                                        @RequestParam Status status) {
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
