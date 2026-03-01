package alex.valker91;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.queue_with_delay.name}")
    private String queueWithDelayName;

    @Value("${rabbitmq.queue_with_auto_ack.name}")
    private String queueWithAutoAckName;

    @Value("${rabbitmq.queue_without_auto_ack.name}")
    private String queueWithoutAutoAckName;

    @Value("${rabbitmq.durable_queue.name}")
    private String durableQueueName;

    /**
     * Конфигурируем очередь
     * @return Очередь RabbitMQ
     */
    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public Queue queueWithDelay() {
        return new Queue(queueWithDelayName, false);
    }

    @Bean
    public Queue autoAckWithException() {
        return new Queue(queueWithAutoAckName, false);
    }

    @Bean
    public Queue autoAckWithoutException() {
        return new Queue(queueWithoutAutoAckName, false);
    }

    @Bean
    public Queue durableQueue() {
        return new Queue(durableQueueName, true);
    }

}
