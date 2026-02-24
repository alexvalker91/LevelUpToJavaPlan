package com.epam.jmp.event.service;

import com.epam.jmp.event.service.dto.Event;

import java.util.List;

public interface EventService {

    /**
     * Find all registered Events
     *
     * @return {@link List<Event>}
     */
    List<Event> getAllEvents();

    /**
     * Find all registered Events by title
     *
     * @param title {@link Event} title
     * @return {@link List<Event>}
     */
    List<Event> getAllEventsByTitle(String title);

    /**
     * Get Event
     *
     * @param id {@link Event} id
     * @return existing {@link Event} object or <i>null</i> if {@link Event} with specified id was not found
     */
    Event getEvent(Long id);

    /**
     * Delete Event
     *
     * @param id {@link Event} id
     * @return existing {@link Event} object or <i>null</i> if {@link Event} with specified id was not found
     */
    Event deleteEvent(Long id);

    /**
     * Delete all Events
     */
    void deleteAllEvents();

    /**
     * Create Event
     *
     * @param event {@link Event}
     * @return {@link Event} object
     */
    Event createEvent(Event event);

    /**
     * Update Event
     *
     * @param id    {@link Event} id
     * @param event {@link Event}
     * @return existing {@link Event} object or <i>null</i> if {@link Event} with specified id was not found
     */
    Event updateEvent(Long id, Event event);
}
