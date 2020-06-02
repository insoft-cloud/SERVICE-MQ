package org.cf.catalog.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQRunner implements CommandLineRunner {

	private static final String TOPIC_EXCHANGE = "catalog-service-broker-exchange";

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQRunner(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) {
//        System.out.println("Sending message...");
//        RabbitMQMessage message = new RabbitMQMessage("Hello 2 Message!", 1, true);
//        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, "foo.bar.baz", message);
    }
}
