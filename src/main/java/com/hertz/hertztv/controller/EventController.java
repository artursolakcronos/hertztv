package com.hertz.hertztv.controller;

import com.hertz.hertztv.model.Event;
import com.hertz.hertztv.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    /**
     * CREATE A NEW EVENT
     */
    @PostMapping("/events")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    /**
     * GET ARTIST EVENT
     */
    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return eventService.findEventById(id);
    }

    /**
     * UPDATE ARTIST EVENT
     */
    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        Event event = eventService.findEventForUpdate(id);

        event.setName(updatedEvent.getName());
        event.setCapacity(updatedEvent.getCapacity());
        event.setLocation(updatedEvent.getLocation());
        event.setDescription(updatedEvent.getDescription());
        event.setArtists(updatedEvent.getArtists());

        //Method calling repository.save, will update existing
        Event newEventDetails = eventService.createEvent(event);

        return ResponseEntity.ok(newEventDetails);
    }

    /**
     * DELETE EVENT
     */
    @DeleteMapping("/events/{id}")
    public ResponseEntity<Boolean> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);

        return ResponseEntity.ok(true);
    }


}
