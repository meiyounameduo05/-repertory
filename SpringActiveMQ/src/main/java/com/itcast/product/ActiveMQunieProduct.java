package com.itcast.product;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class ActiveMQunieProduct {
	
	//×¢ÈëqunineµÄÒÀÀµ×¢Èë
	@Resource(name="jmsQueueTemplate")
	private JmsTemplate jmsTemplate;
	
	public void send(String queneName,final String message){
		jmsTemplate.send(queneName, new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}
	
}
