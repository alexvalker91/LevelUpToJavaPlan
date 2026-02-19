Based on the codebase created during Spring Web module

1. Using the Hibernate ORM framework, update existing models. (10 points) 


2. Add new model to the application – UserAccount, it should store the amount of prepaid money user has in the system, which should be used during booking procedure. Add methods for refilling the account to the BookingFacade class. Add DAO and service objects for new entity. Add ticketPrice field to Event entity. (10 points) 

  

3. Create database schema for storing application entities. Provide SQL script with schema creation DDL for DBMS of your choice. (10 points)  

   

4. Update DAO objects so that they inherit from one of the Spring Data interfaces, for example – CrudRepository. They would store and retrieve application entities from database. Use transaction management to perform actions in a transaction where it necessary (define the approach to implementing transactions with a mentor). Configure Hibernate for work with DBMS that you choose. (40 points)  

   

5. Update ticket booking methods to check and withdraw money from user account according to the ticketPrice for a particular event. (10 points)  

     

6. Cover code with unit tests. Code should contain proper logging (10 points)  


7. Add integration tests for newly implemented scenarios. (10 points)  


Extra mile(optional, should be done when previous items are complete): 

Add Hibernate caching to all getById() methods. Add query caching. Show that you caches are really working in tests (cache hits count, cache miss count) Hint: org.hibernate.stat.Statistic.  
