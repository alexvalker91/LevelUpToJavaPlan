package alex.valker91;

import alex.valker91.facade.BookingFacade;
import alex.valker91.model.impl.UserImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookingFacade bookingFacade = context.getBean("bookingFacade", BookingFacade.class);
        System.out.println("Hello World");
        System.out.println(bookingFacade.createUser(new UserImpl("alex", "alexvalker91@gmail.com")));
        System.out.println(bookingFacade.getUserById(1).getEmail());
    }
}
