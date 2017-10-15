package com.itcast.customer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Service;

@Service
public class ActiveMQTopicCustomer1 implements MessageListener {

	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			System.out.println("ActiveMQTopicCustomer1"+textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
