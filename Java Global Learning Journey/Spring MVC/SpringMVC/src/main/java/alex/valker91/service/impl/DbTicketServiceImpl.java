package alex.valker91.service.impl;

import alex.valker91.model.Event;
import alex.valker91.model.Ticket;
import alex.valker91.model.User;
import alex.valker91.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbTicketServiceImpl implements TicketService {

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return List.of();
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return List.of();
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return false;
    }
}
