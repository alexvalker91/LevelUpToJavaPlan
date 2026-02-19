package alex.valker91.service.impl;

import alex.valker91.entity.TicketDb;
import alex.valker91.model.Event;
import alex.valker91.model.Ticket;
import alex.valker91.model.User;
import alex.valker91.model.impl.TicketImpl;
import alex.valker91.repository.DbTicketRepository;
import alex.valker91.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DbTicketServiceImpl implements TicketService {

    private static final Logger log = LoggerFactory.getLogger(DbTicketServiceImpl.class);

    @Autowired
    private DbTicketRepository dbTicketRepository;

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        if (dbTicketRepository.existsByEventIdAndPlace(eventId, place)) {
            log.info("Place {} is already booked for event {}", place, eventId);
            return null;
        }

        TicketDb ticketDb = new TicketDb();
        ticketDb.setId(null);
        ticketDb.setUserId(userId);
        ticketDb.setEventId(eventId);
        ticketDb.setPlace(place);
        ticketDb.setCategory(TicketDb.Category.valueOf(category.name()));
        TicketDb savedTicket = dbTicketRepository.save(ticketDb);
        return mapTicketDbToTicket(savedTicket);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<TicketDb> ticketsPage = dbTicketRepository.findAllByUserId(user.getId(), pageable);

        return ticketsPage.getContent()
                .stream()
                .map(this::mapTicketDbToTicket)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<TicketDb> ticketsPage = dbTicketRepository.findAllByEventId(event.getId(), pageable);

        return ticketsPage.getContent()
                .stream()
                .map(this::mapTicketDbToTicket)
                .collect(Collectors.toList());
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        if (dbTicketRepository.existsById(ticketId)) {
            dbTicketRepository.deleteById(ticketId);
            log.info("Ticket {} cancelled successfully", ticketId);
            return true;
        } else {
            log.info("Ticket {} not found for cancellation", ticketId);
            return false;
        }
    }

    @Override
    public Ticket getById(long id) {
        Optional<TicketDb> ticketDbOptional = this.dbTicketRepository.findById(id);
        if (ticketDbOptional.isPresent()) {
            Ticket ticket = mapTicketDbToTicket(ticketDbOptional.get());
            log.info("Successfully find of the ticket with id: {}", id);
            return ticket;
        } else {
            log.info("Can not to find an ticket with id: {}", id);
            return null;
        }
    }

    private Ticket mapTicketDbToTicket(TicketDb ticketDb) {
        return new TicketImpl(
                ticketDb.getId(),
                ticketDb.getEventId(),
                ticketDb.getUserId(),
                ticketDb.getPlace(),
                Ticket.Category.valueOf(ticketDb.getCategory().name())
        );
    }

    private TicketDb mapTicketToTicketDb(Ticket ticket) {
        return new TicketDb(
                ticket.getId(),
                ticket.getEventId(),
                ticket.getUserId(),
                TicketDb.Category.valueOf(ticket.getCategory().name()),
                ticket.getPlace()
        );
    }
}
