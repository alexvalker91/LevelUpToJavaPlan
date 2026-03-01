package alex.valker91;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class RabbitMQListener {

    private final static Random RANDOM = new Random();

    private final Set<String> withAutoAckMessageHash;

    private final Set<String> withoutAutoAckMessageHash;

    public RabbitMQListener() {
        withAutoAckMessageHash = new HashSet<>();
        withoutAutoAckMessageHash = new HashSet<>();
    }

    @RabbitListener(queues = "#{@environment.getProperty('rabbitmq.queue.name')}")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }

    @RabbitListener(queues = "#{@environment.getProperty('rabbitmq.queue_with_delay.name')}")
    public void receiveMessageWithDelay(String message) throws InterruptedException {
        var delay = RANDOM.nextInt(10000);
        Thread.sleep(delay);
        System.out.println("Received message: " + message + " with delay: " + delay);
    }

    @RabbitListener(queues = "#{@environment.getProperty('rabbitmq.queue_with_auto_ack.name')}")
    public void receiveMessageWithAutoAckMessageHash(String message) {
        System.out.println("Queue with auto ack got: " + message);
        if (withAutoAckMessageHash.contains(message)) {
            withAutoAckMessageHash.remove(message);
        } else {
            withAutoAckMessageHash.add(message);
            System.out.println("Simulate Exception");
        }
    }

    @RabbitListener(
            queues = "#{@environment.getProperty('rabbitmq.queue_without_auto_ack.name')}",
            ackMode = "MANUAL"
    )
    public void receiveMessageWithoutAutoAckMessageHashAndAckMode(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println("Queue without auto ack got: " + message);
        if (withoutAutoAckMessageHash.contains(message)) {
            withoutAutoAckMessageHash.remove(message);
            channel.basicAck(tag, false);
        } else {
            withoutAutoAckMessageHash.add(message);
            System.out.println("Simulate exception");
            channel.basicNack(tag, false, true);
        }
    }

    @RabbitListener(queues = "#{@environment.getProperty('rabbitmq.durable_queue.name')}")
    public void receiveDurableMsg(String message) {
        System.out.println("Durable message: " + message);
    }

}
