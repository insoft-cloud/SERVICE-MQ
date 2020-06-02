package org.cf.catalog.rabbitmq;

public class RabbitMQMessage {
	private String text;
	private int priority;
	private boolean secret;
    
	public RabbitMQMessage() {}

	public RabbitMQMessage(String text, int priority, boolean secret) {
		this.text = text;
		this.priority = priority;
		this.secret = secret;
	}

	public String getText() {
		return text;
	}

	public int getPriority() {
		return priority;
	}

	public boolean isSecret() {
		return secret;
	}

	@Override
	public String toString() {
		return "RabbitMQMessage{" + 
				"text='" + text + '\'' +
                ", priority=" + priority +
                ", secret=" + secret +
                '}';
    }
}
