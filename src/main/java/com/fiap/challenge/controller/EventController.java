package com.fiap.challenge.controller;

import com.fiap.challenge.model.EventEntity;
import com.fiap.challenge.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventEntity>> getEventsByDate(@RequestParam("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<EventEntity> events = eventService.getEventsByDate(localDate);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventEntity> addEvent(@RequestBody EventEntity event) {
        EventEntity savedEvent = eventService.addEvent(event);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (eventService.deleteEvent(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
