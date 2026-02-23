package alex.valker91.dao;

import alex.valker91.model.Event;
import alex.valker91.model.Ticket;
import alex.valker91.model.User;

import java.util.List;

public interface TicketDAO {

    Ticket getById(long id);

    List<Ticket> getAll();

    Ticket insert(Ticket ticket);

    Ticket update(Ticket ticket);

    boolean delete(long ticketId);

    List<Ticket> getAllByUser(User user, int pageSize, int pageNum);

    List<Ticket> getAllByEvent(Event event, int pageSize, int pageNum);
}
