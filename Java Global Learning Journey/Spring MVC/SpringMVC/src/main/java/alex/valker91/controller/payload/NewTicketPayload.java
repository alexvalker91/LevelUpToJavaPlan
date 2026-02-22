package alex.valker91.controller.payload;

import alex.valker91.model.Ticket;

public record NewTicketPayload(long userId, long eventId, int place, Ticket.Category category) {
}
