package alex.valker91;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final RabbitTemplate template;

    @Value("${rabbitmq.queue.name}")
    private String messageQueue;

    @Value("${rabbitmq.queue_with_delay.name}")
    private String messageWithDelayQueue;

    @Value("${rabbitmq.queue_with_auto_ack.name}")
    private String queueWithAutoAck;

    @Value("${rabbitmq.queue_without_auto_ack.name}")
    private String queueWithoutAutoAck;

    @Value("${rabbitmq.durable_queue.name}")
    private String durableQueue;

    @Autowired
    public MessageService(RabbitTemplate template) {
        this.template = template;
    }

    /**
     * Отправляет сообщение в очередь
     * @param message Сообщение
     */
    public void sendMessage(String message) {
        template.convertAndSend(messageQueue, message);
    }

    /**
     * Посылает сообщение в очередь, которая обрабатывается слушателем с некоторой задержкой
     * @param message Сообщение
     */
    public void sendMessageToQueueWithDelay(String message) {
        template.convertAndSend(messageWithDelayQueue, message);
    }

    /**
     * Посылает сообщение в две очереди: одна оповещается автоматически, вторая - нет
     * @param message Сообщение
     */
    public void sendMessageToQueueWithAndWithoutAutoAck(String message) {
        template.convertAndSend(queueWithAutoAck, message);
        template.convertAndSend(queueWithoutAutoAck, message);
    }

    /**
     * Посылает сообщение в очередь с установленным параметром durable
     * @param message Сообщение
     */
    public void sendMessageToDurableQueue(String message) {
        template.convertAndSend(durableQueue, message);
    }

}
