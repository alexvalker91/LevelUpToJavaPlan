package alex.valker91.config;

import alex.valker91.facade.BookingFacade;
import alex.valker91.facade.impl.BookingFacadeImpl;
import alex.valker91.service.EventService;
import alex.valker91.service.TicketService;
import alex.valker91.service.UserAccountService;
import alex.valker91.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {

    @Bean
    public BookingFacade bookingFacade(EventService eventService,
                                       TicketService ticketService,
                                       UserService userService,
                                       UserAccountService userAccountService) {
        return new BookingFacadeImpl(eventService, ticketService, userService, userAccountService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.POST)
                        .hasAuthority("SCOPE_edit_data")
                        .requestMatchers(HttpMethod.PATCH)
                        .hasAuthority("SCOPE_edit_data")
                        .requestMatchers(HttpMethod.DELETE)
                        .hasAuthority("SCOPE_edit_data")
                        .requestMatchers(HttpMethod.GET)
                        .hasAuthority("SCOPE_view_data")
                        .anyRequest().denyAll())
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
                        .jwt(Customizer.withDefaults()))
                .build();
    }
}
