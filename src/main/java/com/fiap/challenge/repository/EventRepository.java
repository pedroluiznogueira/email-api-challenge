package com.fiap.challenge.repository;

import com.fiap.challenge.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    List<EventEntity> findByDate(LocalDate date); // Find events by date
}
