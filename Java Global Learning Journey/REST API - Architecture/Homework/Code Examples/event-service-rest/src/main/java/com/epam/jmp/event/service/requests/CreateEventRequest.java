package com.epam.jmp.event.service.requests;

import com.epam.jmp.event.service.dto.Event;

public class CreateEventRequest {

    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}