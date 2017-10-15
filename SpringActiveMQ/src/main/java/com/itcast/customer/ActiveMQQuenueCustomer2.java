package com.itcast.customer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Service;

@Service
public class ActiveMQQuenueCustomer2 implements MessageListener{

	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			System.out.println("ActiveMQQuenueCustomer2"+textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
