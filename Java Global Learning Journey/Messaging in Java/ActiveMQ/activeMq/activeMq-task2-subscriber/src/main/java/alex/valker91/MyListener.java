package alex.valker91;

import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MyListener {

    private static final Logger log = LoggerFactory.getLogger(MyListener.class);
    private final JmsTemplate jmsTemplate;

    public MyListener(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = "${demo.my.request.queue}", containerFactory = "onQueueListener")
    public void onRequest(Message message) throws JMSException {
        String text = "";
        if (message instanceof TextMessage) {
            text = ((TextMessage) message).getText();
        }
        String correlationId = message.getJMSCorrelationID();

        log.info("RECEIVED Request: text='{}', correlationId='{}'", text, correlationId);
        Destination replyTo = message.getJMSReplyTo();
        String responseText = "Processed: " + text;

        if (replyTo != null) {
            jmsTemplate.send(replyTo, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage response = session.createTextMessage(responseText);
                    response.setJMSCorrelationID(correlationId);
                    return response;
                }
            });
            log.info("SENT Reply to TemporaryQueue");
        } else {
            log.warn("No ReplyTo header found");
        }
    }
}
