package alex.valker91.config;

import alex.valker91.controller.exception.CustomExceptionResolver;
import alex.valker91.facade.BookingFacade;
import alex.valker91.facade.impl.BookingFacadeImpl;
import alex.valker91.oxm.TicketXml;
import alex.valker91.oxm.TicketsXml;
import alex.valker91.service.EventService;
import alex.valker91.service.TicketService;
import alex.valker91.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class AppConfig {

    @Bean
    public HandlerExceptionResolver customExceptionHandler() {
        return new CustomExceptionResolver();
    }

    @Bean
    public BookingFacade bookingFacade(EventService eventService,
                                       TicketService ticketService,
                                       UserService userService,
                                       Jaxb2Marshaller ticketsMarshaller,
                                       TransactionTemplate transactionTemplate
    ) {
        return new BookingFacadeImpl(eventService, ticketService, userService, ticketsMarshaller, transactionTemplate
        );
    }

    @Bean
    public Jaxb2Marshaller ticketsMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(TicketsXml.class, TicketXml.class);
        return marshaller;
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }
}
