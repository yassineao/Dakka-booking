package com.Yassine.dev.api.termine.repository;

import com.Yassine.dev.api.termine.entity.Termine;
import com.Yassine.dev.api.termine.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



public interface TermineRepository extends JpaRepository<Termine, UUID> {
    List<Termine> findByName(Status status);

}
