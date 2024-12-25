/**
 * 
 */
package com.ems.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
@Component
public class JmsUtils {

	@Autowired
	JmsTemplate jmsTemplate;

	public void sendMessage(final String queueName, final String message) {
		//Map map = new Gson().fromJson(message, Map.class);
		
		log.info("------------> sendMessage() -- EMS_REQUEST_QUEUE");
		 
		final String textMessage = "Hello JMS Queue"  ;//map.get("name");
		log.info("Sending message " + textMessage + "to queue - " + queueName);
		jmsTemplate.send(queueName, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
				return message;
			}
		});
	}

}
