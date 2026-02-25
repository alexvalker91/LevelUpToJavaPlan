package com.epam.jmp.event.service.controller;

import com.epam.jmp.event.service.EventService;
import com.epam.jmp.event.service.dto.Event;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/l3/v3.0/event-service/events")
@Api(
        value = "event-service",
        description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Events.")
public class EventServiceControllerL3 {

    @Autowired
    private EventService eventService;

    @ModelAttribute
    LocalDateTime initLocalDate() {
        return LocalDateTime.now();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json")
    @ApiOperation(
            response = List.class,
            produces = "application/json",
            value = "Returns list of all Events.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Events Details Retrieved", response = List.class),
            @ApiResponse(code = 204, message = "Events not found"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    public @ResponseBody
    ResponseEntity<?> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        ResponseEntity responseEntity;
        if (events != null && !events.isEmpty()) {
            for (Event event : events) {
                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}").buildAndExpand(event.getEventId()).toUri();
                event.add(new Link(location.toString()));
            }
            responseEntity = ResponseEntity.ok(events);
        } else {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @RequestMapping(
            path = "/title/{title}",
            method = RequestMethod.GET,
            produces = "application/json")
    @ApiOperation(
            response = List.class,
            produces = "application/json",
            value = "Returns list of all Events by title")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Events Details Retrieved", response = List.class),
            @ApiResponse(code = 204, message = "Events not found"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    public @ResponseBody
    ResponseEntity<?> getAllEventsByTitle(@ApiParam("Title of the Event to be obtained. Cannot be empty.")
                                          @PathVariable(name = "title") String title) {
        List<Event> events = eventService.getAllEventsByTitle(title);
        ResponseEntity responseEntity;
        if (events != null && !events.isEmpty()) {
            for (Event event : events) {
                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}").buildAndExpand(event.getEventId()).toUri();
                event.add(new Link(location.toString()));
            }
            responseEntity = ResponseEntity.ok(events);
        } else {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    @ApiOperation(
            response = Event.class,
            produces = "application/json",
            value = "Returns a specific Event by their identifier. 404 if does not exist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Event Details Retrieved", response = Event.class),
            @ApiResponse(code = 404, message = "Event not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public @ResponseBody
    HttpEntity<Event> getEvent(@ApiParam("Id of the Event to be obtained. Cannot be empty.")
                               @PathVariable("id") Long id) {
        Event event = eventService.getEvent(id);
        ResponseEntity responseEntity;
        if (event != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}").buildAndExpand(event.getEventId()).toUri();
            event.add(new Link(location.toString()));
            responseEntity = ResponseEntity.ok(event);
        } else {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    @ApiOperation(
            response = Event.class,
            produces = "application/json",
            value = "Deletes a specific Event by their identifier. 404 if does not exist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Event Deleted", response = Event.class),
            @ApiResponse(code = 404, message = "Event not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public @ResponseBody
    ResponseEntity<?> deleteEvent(@ApiParam("Id of the Event to be deleted. Cannot be empty.")
                                  @PathVariable("id") Long id) {
        Event event = eventService.deleteEvent(id);
        ResponseEntity responseEntity;
        if (event != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}").buildAndExpand(event.getEventId()).toUri();
            event.add(new Link(location.toString()));
            responseEntity = ResponseEntity.ok(event);
        } else {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = "application/json")
    @ApiOperation(
            response = Event.class,
            value = "Create new Event")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Event created", response = Event.class),
            @ApiResponse(code = 404, message = "Event not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        ResponseEntity responseEntity;
        if (createdEvent != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}").buildAndExpand(createdEvent.getEventId()).toUri();
            responseEntity = ResponseEntity.created(location).build();
        } else {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.PUT,
            produces = "application/json")
    @ApiOperation(
            response = Event.class,
            value = "Update Event")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Event Updated", response = Event.class),
            @ApiResponse(code = 404, message = "Event not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> updateEvent(@PathVariable("id") Long id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        ResponseEntity responseEntity;
        if (updatedEvent != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}").buildAndExpand(updatedEvent.getEventId()).toUri();
            responseEntity = ResponseEntity.created(location).build();
        } else {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }
}