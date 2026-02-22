package alex.valker91.dao;

import alex.valker91.exception.DbException;
import alex.valker91.model.Event;

import java.util.List;

public interface EventDAO {

    Event getById(long id);

    List<Event> getAll();

    Event insert(Event event);

    Event update(Event event);

    boolean delete(long eventId);
}
