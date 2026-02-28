package alex.valker91;

import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageProducer;
import jakarta.jms.Message;
import jakarta.jms.Session;
import jakarta.jms.TemporaryQueue;
import jakarta.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;import org.springframework.jms.core.SessionCallback;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MyPublisher {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${demo.my.request.queue}")
    private String requestQueueName;

    public String requestReply(String messageContent, long timeoutMs) {
        return jmsTemplate.execute(new SessionCallback<String>() {
            @Override
            public String doInJms(Session session) throws JMSException {

                TemporaryQueue tempReplyQueue = session.createTemporaryQueue();
                MessageProducer producer = null;
                MessageConsumer consumer = null;

                try {
                    TextMessage requestMessage = session.createTextMessage(messageContent);
                    String correlationId = UUID.randomUUID().toString();
                    requestMessage.setJMSCorrelationID(correlationId);
                    requestMessage.setJMSReplyTo(tempReplyQueue);
                    Destination requestQueue = session.createQueue(requestQueueName);
                    producer = session.createProducer(requestQueue);
                    producer.send(requestMessage);
                    consumer = session.createConsumer(tempReplyQueue);
                    Message replyMessage = consumer.receive(timeoutMs);

                    if (replyMessage instanceof TextMessage) {
                        return ((TextMessage) replyMessage).getText();
                    } else if (replyMessage == null) {
                        return "TIMEOUT: Сервер не ответил за " + timeoutMs + " мс";
                    } else {
                        return "Error: Unknown message type";
                    }

                } finally {
                    if (consumer != null) consumer.close();
                    if (producer != null) producer.close();
                    if (tempReplyQueue != null) tempReplyQueue.delete();
                }
            }
        }, true);
    }
}
