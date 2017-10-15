package com.itcast.customer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Service;

@Service
public class ActiveMQTopicCustomer2 implements MessageListener {

	public void onMessage(Message message) {
			TextMessage textMessage = (TextMessage) message;
			try {
				System.out.println("ActiveMQTopicCustomer2"+textMessage.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
	}
	
}
