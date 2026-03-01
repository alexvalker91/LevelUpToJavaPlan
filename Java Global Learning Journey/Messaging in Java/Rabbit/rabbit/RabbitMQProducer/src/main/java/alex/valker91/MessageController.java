package alex.valker91;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Message Controller", description = "Controller to send message to RabbitMQ")
@RequestMapping("/message")
public class MessageController {

    private final MessageService service;

    @Autowired
    public MessageController(MessageService service) {
        this.service = service;
    }

    @Operation(description = "Send message to RabbitMQ")
    @ApiResponse(responseCode = "204")
    @PostMapping
    public void sendMessage(String message) {
        service.sendMessage(message);
    }

    @Operation(description = "Send message to RabbitMQ in queue with delay")
    @ApiResponse(responseCode = "204")
    @PostMapping("/with_delay")
    public void sendMessageWithDelay(String message) {
        service.sendMessageToQueueWithDelay(message);
    }

    @Operation(description = "Send message to RabbitMQ in queue with autoAck and without autoAck")
    @ApiResponse(responseCode = "204")
    @PostMapping("/with_and_without_auto_ack")
    public void sendMessageToQueueWithAndWithoutAutoAck(String message) {
        service.sendMessageToQueueWithAndWithoutAutoAck(message);
    }

    @Operation(description = "Send message to RabbitMQ to durable Queue")
    @ApiResponse(responseCode = "204")
    @PostMapping("/send_message_to_durable_queue")
    public void sendMessageToDurableQueue(String message) {
        service.sendMessageToDurableQueue(message);
    }

}
