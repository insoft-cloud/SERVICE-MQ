package org.cf.catalog.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RabbitMQConsumer {
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);
	
	@RabbitListener(queues = "catalog-service-broker")
	public void receiveMessage(String message) {
		System.out.println("/// RESPONSE MESSAGE > " + message);
    }
}
