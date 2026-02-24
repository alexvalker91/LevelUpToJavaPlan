package com.epam.jmp.event.service.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Event extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long eventId;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String place;

    @Getter
    @Setter
    private String speaker;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private EventType eventType;

    @Getter
    @Setter
    private LocalDateTime dateTime;

    @JsonCreator
    public Event() {
    }

    @JsonCreator
    public Event(@JsonProperty("title") String title,
                 @JsonProperty("place") String place,
                 @JsonProperty("speaker") String speaker,
                 @JsonProperty("eventType") EventType eventType,
                 @JsonProperty("dateTime") LocalDateTime dateTime) {
        this.title = title;
        this.place = place;
        this.speaker = speaker;
        this.eventType = eventType;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(title, event.title) &&
                Objects.equals(place, event.place) &&
                Objects.equals(speaker, event.speaker) &&
                eventType == event.eventType &&
                Objects.equals(dateTime, event.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, place, speaker, eventType, dateTime);
    }
}
