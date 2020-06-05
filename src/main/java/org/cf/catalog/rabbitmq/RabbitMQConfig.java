package org.cf.catalog.rabbitmq;

import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

@Configuration
public class RabbitMQConfig {

	private static final String QUEUE_NAME = "catalog-service-broker";
	private static final String TOPIC_EXCHANGE_NAME = "catalog-service-broker-exchange-send";
	private static final String ROUTING_KEY = "service.broker-send.#";
	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME, false);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(TOPIC_EXCHANGE_NAME);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}
	
	@Bean
	RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory
			, MessageConverter messageConverter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter);
		return rabbitTemplate;
	}

	@Bean
	MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
