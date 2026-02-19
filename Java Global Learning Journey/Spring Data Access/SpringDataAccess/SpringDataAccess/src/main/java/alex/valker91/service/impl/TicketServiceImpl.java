package alex.valker91.service.impl;

import alex.valker91.dao.TicketDao;
import alex.valker91.model.Event;
import alex.valker91.model.Ticket;
import alex.valker91.model.User;
import alex.valker91.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    private TicketDao ticketDao;
    private static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

    public TicketServiceImpl() {}

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        Ticket booked = ticketDao.bookTicket(userId, eventId, place, category);
        log.info("bookTicket: id={}, userId={}, eventId={}, place={}, category={}", booked.getId(), userId, eventId, place, category);
        return booked;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketDao.getBookedTickets(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketDao.getBookedTickets(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketDao.cancelTicket(ticketId);
    }
}
