package com.itcast.testProduct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itcast.product.ActiveMQunieProduct;
import com.itcast.product.ActiveTopicProduct;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-mq.xml")
public class TestProduct {

	@Autowired
	private ActiveMQunieProduct activeMQunieProduct;
	
	@Autowired
	private ActiveTopicProduct activeTopicProduct;
	
	@Test
	public void test(){
		activeMQunieProduct.send("spring-quenue", "你好高雪岩");
		activeTopicProduct.send("spring-topic", "你好高雪崖");
	}
}
