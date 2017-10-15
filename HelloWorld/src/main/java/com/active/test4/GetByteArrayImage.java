package com.active.test4;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class GetByteArrayImage {

	/**
	 * 获取默认引擎的实例。自动获取activiti.cfg.xml
	 */
	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 通过部署id来获取部署图片
	 * @throws IOException 
	 */
	@Test
	public void getByIdImage() throws IOException{
		InputStream inputStream = processEngine.getRepositoryService() //获取服务
			.getResourceAsStream("15001", "Diagrams/helloWorld.png");
		FileUtils.copyInputStreamToFile(inputStream, new File("D:/helloWorld.png"));
	} 
}
