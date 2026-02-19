package alex.valker91.dao;

import alex.valker91.model.Event;
import alex.valker91.model.Ticket;
import alex.valker91.model.User;

import java.util.List;

public interface TicketDao {

    Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);
    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);
    List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);
    boolean cancelTicket(long ticketId);
    Ticket getById(long id);
}
