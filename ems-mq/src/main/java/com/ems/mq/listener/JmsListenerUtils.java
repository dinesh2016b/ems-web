/**
 * 
 */
package com.ems.mq.listener;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;

@Configuration
@EnableJms
public class JmsListenerUtils  {

	@JmsListener(destination = "EMS_REQUEST_QUEUE")	
	@SendTo("EMS_RESPONSE_QUEUE")
	public String receiveMessage(final Message jsonMessage) throws JMSException {
		String messageData = null;
		System.out.println("Received message " + jsonMessage);
		String response = null;
		if(jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)jsonMessage;
			messageData = textMessage.getText();
			//Map map = new Gson().fromJson(messageData, Map.class);
			response  = "Hello " + messageData;
		}
		return response;
	}

}