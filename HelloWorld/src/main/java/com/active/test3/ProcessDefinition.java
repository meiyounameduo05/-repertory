package com.active.test3;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

public class ProcessDefinition {
	/**
	 * 获取默认的流程引擎实例，会自动读取activiti.cfg.xml 
	 */
	private ProcessEngine engines =ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 部署流程定义使用classpath方式
	 */
	@Test
	public void deployWhithClassPath(){
		//获取部署对象
		Deployment deploy = engines.getRepositoryService()//获取部署对象
			.createDeployment()//创建部署
			.addClasspathResource("Diagrams/helloWorld.bpmn")//加载资源
			.addClasspathResource("Diagrams/helloWorld.png") //加载资源
			.name("helloWorld")//给部署项目起名字
			.deploy();//部署成功
		
		System.out.println("部署流程的Id:"+deploy.getId());
		System.out.println("部署流程的名字:"+deploy.getName());
	}
	
	/**
	 * 部署方式流程定义使用zip的方式进行
	 */
	@Test
	public void deployWhithZip() {
		InputStream inputStream = this.getClass() //创建类
			.getClassLoader()//创建类加载器
				.getResourceAsStream("Diagrams/Desktop.zip");//指定资源的类加载路径
		
		ZipInputStream zipInputStream = new ZipInputStream(inputStream);//转换成zip形式的流
		Deployment deploy = engines.getRepositoryService()//获取service
			.createDeployment()//创建部署
			.name("helloWorld2")//给部署项目起名字
			.addZipInputStream(zipInputStream)//加载工作流的路径
			.deploy();//部署项目
		System.out.println(""+deploy.getId());
		System.out.println(""+deploy.getName());
		
	}
}
