package alex.valker91;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener {

    private static final Logger log = LoggerFactory.getLogger(MyListener.class);

    @JmsListener(
            destination = "${my.destination.name}",
            containerFactory = "onNonDurableListener"
    )
    public void onNonDurableListener(String message) {
        log.info("onNonDurableListener received: " + message);
    }

    @JmsListener(
            destination = "${my.destination.name}",
            containerFactory = "onDurableListener"
    )
    public void onDurableListener(String message) {
        log.info("onDurableListener received: " + message);
    }
}
