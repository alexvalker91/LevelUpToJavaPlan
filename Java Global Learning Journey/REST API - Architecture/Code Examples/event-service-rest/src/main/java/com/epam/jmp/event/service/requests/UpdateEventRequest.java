package com.epam.jmp.event.service.requests;

import com.epam.jmp.event.service.dto.Event;

public class UpdateEventRequest {

    private Long id;

    private Event event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
