package alex.valker91.service.impl;

import alex.valker91.model.Event;
import alex.valker91.service.EventService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DbEventServiceImpl implements EventService {

    @Override
    public Event getEventById(long eventId) {
        return null;
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return List.of();
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return List.of();
    }

    @Override
    public Event createEvent(Event event) {
        return null;
    }

    @Override
    public Event updateEvent(Event event) {
        return null;
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return false;
    }
}
