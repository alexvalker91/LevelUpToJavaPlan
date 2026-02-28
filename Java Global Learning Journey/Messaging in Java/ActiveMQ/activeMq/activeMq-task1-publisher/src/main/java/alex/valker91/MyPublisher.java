package alex.valker91;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyPublisher {

    @Autowired
    @Qualifier("myJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Value("${my.destination.name}")
    private String myDestinationName;

    public void publishMessage(String message) {
        jmsTemplate.convertAndSend(myDestinationName, message);
    }
}
