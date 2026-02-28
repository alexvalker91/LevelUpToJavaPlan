package alex.valker91;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final MyPublisher myPublisher;

    MyController(MyPublisher myPublisher) {
        this.myPublisher = myPublisher;
    }

}
