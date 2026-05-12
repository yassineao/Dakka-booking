package com.Yassine.dev.api.termine.dto;

import com.Yassine.dev.api.termine.entity.Termine;
import com.Yassine.dev.api.termine.enums.Occasion;
import com.Yassine.dev.api.termine.enums.Status;
import com.Yassine.dev.api.termine.repository.TermineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TermineServiceTest {

    @Mock
    private TermineRepository repository;

    @InjectMocks
    private TermineService service;

    private Termine testTermine;
    private TermineCreateRequest createRequest;
    private TermineUpdateRequest updateRequest;
    private UUID testId;

    @BeforeEach
    void setUp() {
        testId = UUID.randomUUID();

        testTermine = new Termine();
        testTermine.setId(testId);
        testTermine.setName("Test Event");
        testTermine.setHallOrLocation("Grand Hall");
        testTermine.setOccasion(Occasion.HOCHZEIT);
        testTermine.setStatus(Status.AUSSTEHEND);
        testTermine.setStartDate(LocalDateTime.now());
        testTermine.setEndDate(LocalDateTime.now().plusHours(2));

        createRequest = new TermineCreateRequest();
        createRequest.setName("New Event");
        createRequest.setHallOrLocation("Small Hall");
        createRequest.setOccasion(Occasion.GEBURTSTAG);
        createRequest.setStartDate(LocalDateTime.now());
        createRequest.setEndDate(LocalDateTime.now().plusHours(1));

        updateRequest = new TermineUpdateRequest();
        updateRequest.setId(testId);
        updateRequest.setName("Updated Event");
        updateRequest.setStatus(Status.BESTÄTIGT);
    }

    @Test
    void create_ShouldReturnTermineResponse() {
        // Given
        when(repository.save(any(Termine.class))).thenReturn(testTermine);

        // When
        TermineResponse response = service.create(createRequest);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(testId);
        assertThat(response.getName()).isEqualTo("Test Event");
        verify(repository).save(any(Termine.class));
    }

    @Test
    void getAll_ShouldReturnListOfTermineResponses() {
        // Given
        when(repository.findAll()).thenReturn(List.of(testTermine));

        // When
        List<TermineResponse> responses = service.getAll();

        // Then
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getId()).isEqualTo(testId);
        verify(repository).findAll();
    }

    @Test
    void getById_ShouldReturnTermineResponse_WhenExists() {
        // Given
        when(repository.findById(testId)).thenReturn(Optional.of(testTermine));

        // When
        TermineResponse response = service.getById(testId);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(testId);
        verify(repository).findById(testId);
    }

    @Test
    void getById_ShouldThrowException_WhenNotExists() {
        // Given
        when(repository.findById(testId)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> service.getById(testId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Termin not found");
        verify(repository).findById(testId);
    }

    @Test
    void update_ShouldReturnUpdatedTermineResponse() {
        // Given
        when(repository.findById(testId)).thenReturn(Optional.of(testTermine));
        when(repository.save(any(Termine.class))).thenReturn(testTermine);

        // When
        TermineResponse response = service.update(testId, updateRequest);

        // Then
        assertThat(response).isNotNull();
        verify(repository).findById(testId);
        verify(repository).save(any(Termine.class));
    }

    @Test
    void updateStatus_ShouldReturnUpdatedTermineResponse() {
        // Given
        when(repository.findById(testId)).thenReturn(Optional.of(testTermine));
        when(repository.save(any(Termine.class))).thenReturn(testTermine);

        // When
        TermineResponse response = service.updateStatus(testId, Status.BESTÄTIGT);

        // Then
        assertThat(response).isNotNull();
        verify(repository).findById(testId);
        verify(repository).save(any(Termine.class));
    }

    @Test
    void delete_ShouldCallRepositoryDelete() {
        // Given
        when(repository.findById(testId)).thenReturn(Optional.of(testTermine));

        // When
        service.delete(testId);

        // Then
        verify(repository).findById(testId);
        verify(repository).delete(testTermine);
    }

    @Test
    void updateBatch_ShouldReturnListOfUpdatedResponses() {
        // Given
        when(repository.findById(testId)).thenReturn(Optional.of(testTermine));
        when(repository.save(any(Termine.class))).thenReturn(testTermine);

        // When
        List<TermineResponse> responses = service.updateBatch(List.of(updateRequest));

        // Then
        assertThat(responses).hasSize(1);
        verify(repository, times(1)).findById(testId); // Once in updateBatch -> update
        verify(repository, times(1)).save(any(Termine.class));
    }
}