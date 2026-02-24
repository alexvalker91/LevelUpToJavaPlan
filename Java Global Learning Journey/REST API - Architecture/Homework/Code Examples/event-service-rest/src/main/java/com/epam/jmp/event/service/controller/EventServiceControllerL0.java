package com.epam.jmp.event.service.controller;

import com.epam.jmp.event.service.EventService;
import com.epam.jmp.event.service.dto.Event;
import com.epam.jmp.event.service.requests.CreateEventRequest;
import com.epam.jmp.event.service.requests.EventRequestFailure;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/l0/v1.0/event-service")
@Api(
        value = "event-service",
        description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Events.")
public class EventServiceControllerL0 {

    @Autowired
    private EventService eventService;

    @RequestMapping(
            method = RequestMethod.POST,
            produces = "application/json")
    @ApiOperation(
            response = Event.class,
            value = "Create new Event")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Created Event Details", response = Event.class),
            @ApiResponse(code = 200, message = "Internal Server Error", response = EventRequestFailure.class)
    })
    public ResponseEntity<?> createEvent(@RequestBody CreateEventRequest createEventRequest) {
        Event createdEvent = eventService.createEvent(createEventRequest.getEvent());
        ResponseEntity responseEntity;
        if (createdEvent != null) {
            responseEntity = ResponseEntity.ok(createdEvent);
        } else {
            responseEntity = ResponseEntity.ok(new EventRequestFailure("Internal Server Error"));
        }
        return responseEntity;
    }
}