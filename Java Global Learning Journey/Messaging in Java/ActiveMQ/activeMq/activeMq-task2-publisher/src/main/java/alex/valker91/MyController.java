package alex.valker91;

import jakarta.jms.JMSException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final MyPublisher myPublisher;

    MyController(MyPublisher myPublisher) {
        this.myPublisher = myPublisher;
    }

    @PostMapping("/request-reply")
    public ResponseEntity<String> requestReply(@RequestParam(name = "message") String message,
                                               @RequestParam(name = "timeoutMilliSec", required = false, defaultValue = "5000") long timeoutMilliSec) {
        String reply = myPublisher.requestReply(message, timeoutMilliSec);
        return ResponseEntity.ok(reply);
    }
}
