package com.epam.jmp.event.service.impl;

import com.epam.jmp.event.service.EventService;
import com.epam.jmp.event.service.dto.Event;
import com.epam.jmp.event.service.impl.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    /**
     * Get all registered Events
     *
     * @return {@link List<Event>}
     */
    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    /**
     * Get all registered Events by title
     *
     * @return {@link List<Event>}
     */
    public List<Event> getAllEventsByTitle(String title) {
        return eventRepository.findByTitle(title);
    }

    /**
     * Get Event
     *
     * @param id {@link Long} id
     * @return existing {@link Event} object or <i>null</i> if {@link Event} with specified id was not found
     */
    public Event getEvent(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElse(null);
    }

    /**
     * Delete Event
     *
     * @param id {@link Long} id
     * @return existing {@link Event} object or <i>null</i> if {@link Event} with specified id was not found
     */
    public Event deleteEvent(Long id) {
        Optional<Event> existingEvent = eventRepository.findById(id);
        Event result = null;
        if (existingEvent.isPresent()) {
            result = existingEvent.get();
            eventRepository.deleteById(id);
        }
        return result;
    }

    /**
     * Delete all Events
     */
    public void deleteAllEvents() {
        eventRepository.deleteAll();
    }

    /**
     * Create Event
     *
     * @param event {@link Event}
     * @return created {@link Event} object
     */
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    /**
     * Update Event
     *
     * @param id    {@link Event} id
     * @param event {@link Event}
     * @return updated {@link Event} object or <i>null</i> if {@link Event} with specified id was not found
     */
    public Event updateEvent(Long id, Event event) {
        Optional<Event> existingEvent = eventRepository.findById(id);
        Event result = null;
        if (existingEvent.isPresent()) {
            event.setEventId(existingEvent.get().getEventId());
            result = eventRepository.save(event);
        }
        return result;
    }
}