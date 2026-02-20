1. Transform project from Spring Introduction module into web application, configure dispatcher servlet. (10 points) 

2. Implement annotation-based controllers that will delegate to BookingFacade methods. For methods, that accept Entity, just send the list of parameters from the client and assemble the entity in controller, no need in automatic conversion of request payload to java object. (10 points) 

3. For methods, that should return single entity or entity list result (e.g. getUsersByName), implement simple thymeleaf templates for displaying results. No sophisticated markup required, just the fact that you know how to implement the chain: 

ModelAndView à Resolver à ThymeleafTemplate à Html page in the browser. (20 points) 

  

4. For the following facade method: 

List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

Implement alternative controller, which will be mapped on header value "accept=application/pdf" and return PDF version of booked tickets list. (10 points) 

5. Implement batch creation of ticket bookings from XML file. Source file example: 
<code>
&lt;tickets&gt; 

  &lt;ticket user="..." event="..." category="..." place="..."/&gt; 

  &lt;ticket user="..." event="..." category="..." place="..."/&gt;

  &lt;ticket user="..." event="..." category="..." place="..."/&gt; 

&lt;/tickets&gt; 
</code>

  

Add a method public void preloadTickets() to facade that will load this file from some predefined place (or from a location specified in parameter), unmarshal ticket objects using Spring OXM capabilities and update the storage. The whole batch should be performed in a single transaction, using programmatic transaction management. (20 points) 

    

6. Implement custom HandlerExceptionResolver, which in case of controller exception just send simple text response to the client with brief description of the error. (10 points) 

7. Unit tests, logging, javadocs. (10 points) 

8. Implement integration tests for Booking service controllers using  MockMVC framework. (10 points) 


Extra mile(optional, should be done when previous items are complete):  

    Update your application using spring boot instead of the current implementation.  

    Modify the method implemented in the point 5. Make facade method accept some input stream, MVC controller for this method should handle multipart file upload and pass the file byte stream to the facade method. 


