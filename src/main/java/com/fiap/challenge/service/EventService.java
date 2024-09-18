package com.fiap.challenge.service;

import com.fiap.challenge.model.EventEntity;
import com.fiap.challenge.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<EventEntity> getEventsByDate(LocalDate date) {
        return eventRepository.findByDate(date);
    }

    public EventEntity addEvent(EventEntity event) {
        return eventRepository.save(event);
    }

    public boolean deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
