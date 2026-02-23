package alex.valker91.config;

import alex.valker91.facade.BookingFacade;
import alex.valker91.facade.impl.BookingFacadeImpl;
import alex.valker91.service.EventService;
import alex.valker91.service.TicketService;
import alex.valker91.service.UserAccountService;
import alex.valker91.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public BookingFacade bookingFacade(EventService eventService,
                                       TicketService ticketService,
                                       UserService userService,
                                       UserAccountService userAccountService) {
        return new BookingFacadeImpl(eventService, ticketService, userService, userAccountService);
    }
}
