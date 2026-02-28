package alex.valker91;

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

    @PostMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestParam(name = "message") String message) {
        myPublisher.publishMessage(message);
        return ResponseEntity.ok("published: " + message);
    }
}
