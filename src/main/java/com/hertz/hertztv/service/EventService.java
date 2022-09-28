package com.hertz.hertztv.service;

import com.hertz.hertztv.exception.ResourceNotFoundException;
import com.hertz.hertztv.model.Event;
import com.hertz.hertztv.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(Event Event) {
        return eventRepository.save(Event);
    }

    public ResponseEntity<Event> findEventById(Long id) {
        Event Event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event with id " + id + " not found!"));
        return ResponseEntity.ok(Event);
    }

    public Event findEventForUpdate(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event with id " + id + " not found!"));
    }

    public void deleteEvent(Long id) {
        try {
            eventRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Event with id " + id + " not existing!");
        }
    }


}
