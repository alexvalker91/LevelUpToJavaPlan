package com.epam.jmp.event.service.controller;

import com.epam.jmp.event.service.EventService;
import com.epam.jmp.event.service.dto.Event;
import com.epam.jmp.event.service.requests.CreateEventRequest;
import com.epam.jmp.event.service.requests.EventRequestFailure;
import com.epam.jmp.event.service.requests.GetAllEventsRequest;
import com.epam.jmp.event.service.requests.GetEventByTitleRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/l1/v1.0/event-service")
@Api(
        value = "event-service",
        description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Events.")
public class EventServiceControllerL1 {

    @Autowired
    private EventService eventService;

    @RequestMapping(
            path = "/events",
            method = RequestMethod.POST,
            produces = "application/json")
    @ApiOperation(
            response = List.class,
            produces = "application/json",
            value = "Returns list of all Events.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Event Details Retrieved", response = List.class),
            @ApiResponse(code = 200, message = "No content", response = EventRequestFailure.class)
    })
    public @ResponseBody
    ResponseEntity<?> getAllEvents(@RequestBody GetAllEventsRequest getAllEventsRequest) {
        List<Event> events = eventService.getAllEvents();
        ResponseEntity responseEntity;
        if (events != null && !events.isEmpty()) {
            responseEntity = ResponseEntity.ok(events);
        } else {
            responseEntity = ResponseEntity.ok(new EventRequestFailure("No content"));
        }
        return responseEntity;
    }

    @RequestMapping(
            path = "/events/title/{title}",
            method = RequestMethod.POST,
            produces = "application/json")
    @ApiOperation(
            response = List.class,
            produces = "application/json",
            value = "Returns list of all Events by title")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Event Details Retrieved", response = List.class),
            @ApiResponse(code = 200, message = "No content", response = EventRequestFailure.class)
    })

    public @ResponseBody
    ResponseEntity<?> getAllEventsByTitle(@RequestBody GetEventByTitleRequest getEventByTitleRequest) {
        List<Event> events = eventService.getAllEventsByTitle(getEventByTitleRequest.getTitle());
        ResponseEntity responseEntity;
        if (events != null && !events.isEmpty()) {
            responseEntity = ResponseEntity.ok(events);
        } else {
            responseEntity = ResponseEntity.ok(new EventRequestFailure("No content"));
        }
        return responseEntity;
    }

    @RequestMapping(
            path = "/events/{id}",
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