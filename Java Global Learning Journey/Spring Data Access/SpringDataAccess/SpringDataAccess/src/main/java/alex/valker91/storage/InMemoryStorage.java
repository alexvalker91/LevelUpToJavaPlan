package alex.valker91.storage;

import alex.valker91.model.Event;
import alex.valker91.model.Ticket;
import alex.valker91.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryStorage {

    public InMemoryStorage() {}

    private final Map<String, User> userStorage = new HashMap<>();
    private final Map<String, Event> eventStorage = new HashMap<>();
    private final Map<String, Ticket> ticketStorage = new HashMap<>();

    private static final String NAME_SPACE_EVENT = "event";
    private static final String NAME_SPACE_TICKET = "ticket";
    private static final String NAME_SPACE_USER = "user";

    public void setUsers(List<User> users) {
        users.forEach(user -> userStorage.put(NAME_SPACE_USER + ":" + user.getId(), user));
    }

    public void setEvents(List<Event> events) {
        events.forEach(event -> eventStorage.put(NAME_SPACE_EVENT + ":" + event.getId(), event));
    }

    public void setTickets(List<Ticket> tickets) {
        tickets.forEach(ticket -> ticketStorage.put(NAME_SPACE_TICKET + ":" + ticket.getId(), ticket));
    }

    public Map<String, User> getUserStorage() {
        return userStorage;
    }

    public Map<String, Event> getEventStorage() {
        return eventStorage;
    }

    public Map<String, Ticket> getTicketStorage() {
        return ticketStorage;
    }
}
