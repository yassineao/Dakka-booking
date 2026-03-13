package com.Yassine.dev.api.termine.dto;

import com.Yassine.dev.api.termine.entity.Termine;
import com.Yassine.dev.api.termine.enums.Status;
import com.Yassine.dev.api.termine.mapper.TermineMapper;
import com.Yassine.dev.api.termine.repository.TermineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TermineService {

    private final TermineRepository repository;

    public TermineService(TermineRepository repository) {
        this.repository = repository;
    }

    public TermineResponse create(TermineCreateRequest request) {
        Termine termine = TermineMapper.toEntity(request);
        Termine saved = repository.save(termine);
        return TermineMapper.toResponse(saved);
    }

    public List<TermineResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(TermineMapper::toResponse)
                .toList();
    }

    public TermineResponse getById(UUID id) {
        Termine termine = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Termin not found"));
        return TermineMapper.toResponse(termine);
    }

    public TermineResponse update(UUID id, TermineUpdateRequest request) {
        Termine termine = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Termin not found"));

        TermineMapper.updateEntity(request, termine);

        Termine updated = repository.save(termine);
        return TermineMapper.toResponse(updated);
    }

    public TermineResponse updateStatus(UUID id, Status status) {
        Termine termine = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Termin not found"));

        termine.setStatus(status);

        Termine updated = repository.save(termine);
        return TermineMapper.toResponse(updated);
    }

    public void delete(UUID id) {
        Termine termine = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Termin not found"));

        repository.delete(termine);
    }

    public List<TermineResponse> updateBatch(List<TermineUpdateRequest> requests) {
        return requests.stream()
                .map(r -> update(r.getId(), r))
                .toList();
    }
}