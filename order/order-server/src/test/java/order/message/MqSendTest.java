package order.message;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqSendTest extends MqReceiverTest {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void sendMq() {
        amqpTemplate.convertAndSend("myQueue", "hello , MQ");
    }
}
