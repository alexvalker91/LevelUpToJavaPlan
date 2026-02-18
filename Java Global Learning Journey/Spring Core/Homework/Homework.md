Create Spring based module, which handles event tickets booking.    

Based on attached source code model: 

1. Implement three service classes: 

    UserService  

    EventService  

    TicketService  

which should contain user, event and booking related functionality accordingly. Create implementation of BookingFacade interface which should delegate method calls to services mentioned above. 

(10 points) 

2. Configure spring application context based on xml config file. (10 points) 

3. Implement DAO objects for each of the domain model entities (User, Event, Ticket). They should store in and retrieve data from a common in-memory storage - java map. Each entity should be stored under separate namespace, so you could list particular entity types. (10 points) 

Example for ticket - map entry {"ticket:<ticketId>" à {<Ticket object>}}.   

E.g. {"ticket:12345" à {"id" : 12345, "place" : 23, ......}}  


4. Storage should be implemented as a separate spring bean. Implement ability to initialize storage with some prepared data from file during the application start (use spring bean post-processing features). Path to the concrete file should be set using property placeholder and external property file. (20 points) 


5. DAO with storage bean should be inserted into services beans using autowiring. Services beans should be injected into facade using constructor-based injections. Rest of the injections should be done in a setter-based way. (20 points) 

6. Cover code with unit tests. (10 points) 

7. Code should contain proper logging. (10 points) 

8. Create several integration tests that instantiate the Application Context directly, lookup facade bean and perform some real-life scenario (e.g. create user, then create event, then book ticket for this event for the user, then cancel it). (10 points) 
 
Extra mile (optional, should be done when previous items are complete):   

    Use "p" namespace to define properties   

    Use SLF4J API for logging, configure Spring to use SLF4J and add some Spring logging output to common application log. 

Page BreakTotal mark is 100. 
