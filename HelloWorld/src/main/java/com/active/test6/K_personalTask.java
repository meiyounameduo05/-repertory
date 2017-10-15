package com.active.test6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

/*
 * 运用流程变量
 */
public class K_personalTask {

	/**
	 * 获取默认流程引擎实例，会自动读取activiti.cfg.xml
	 */
	private ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 部署定义流程
	 */
	@Test
	public void deploy(){
		Deployment deploy = engine.getRepositoryService()//获取部署相关的service
			.createDeployment() //创建部署
			.name("helloWorld")
			.addClasspathResource("Diagrams/task.bpmn")//加载资源文件
			.addClasspathResource("Diagrams/task.png") //加载资源文件
			.deploy();//部署
		
		System.out.println("流程部署Id:"+deploy.getId());
		System.out.println("流程部署名字:"+deploy.getName());
	}
	
	/**
	 * 启动流程实例,设置流程变量
	 */
	@Test
	public void start(){
		/*启动流程实例,设置流程变量,来设置流程的办理人*/
		Map<String, Object> variables  = new HashMap<String, Object>();
		variables.put("userID", "张三丰");
		ProcessInstance pec = engine.getRuntimeService() //运行service
			.startProcessInstanceByKey("task",variables);//流程定义表的key字段值
		System.out.println("流程实例ID："+pec.getId());
		System.out.println("流程定义ID："+pec.getProcessDefinitionId());
	}
	
	/**
	 * 查看任务
	 */
	@Test
	public void findTask(){
		List<Task> tesk = engine.getTaskService()//任务相关的service
		.createTaskQuery()//创建查询
		.taskAssignee("java_gao")//查看指定人
		.list();
		
		for (Task task : tesk) {
			System.out.println("任务ID:"+task.getId());
			System.out.println("任务名称："+task.getName());
			System.out.println("任务创建时间："+task.getCreateTime());
			System.out.println("任务委派人："+task.getAssignee());
			System.out.println("流程实例ID:"+task.getProcessInstanceId());
		}
	}
	/**
	 * 完成任务
	 */
	@Test
	public void completeTask(){
		engine.getTaskService()
		.complete("140005");
	}
	
}
