package com.active.test5;

import static org.junit.Assert.*;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class RecieveTask {

	/**
	 * 获取默认流程引擎实例，会自动读取activiti.cfg.xml
	 */
	private ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

	/**
	 * 部署定义流程
	 */
	@Test
	public void deploy() {
		Deployment deploy = engine.getRepositoryService()// 获取部署相关的service
				.createDeployment() // 创建部署
				.name("接受任务活动").addClasspathResource("Diagrams/ReceiveTaskProcess.bpmn")// 加载资源文件
				.addClasspathResource("Diagrams/ReceiveTaskProcess.png") // 加载资源文件
				.deploy();// 部署

		System.out.println("流程部署Id:" + deploy.getId());
		System.out.println("流程部署名字:" + deploy.getName());
	}

	/**
	 * 启动流程实例+设置流程变量+获取流程变量+向后执行一步
	 */
	@Test
	public void start() {
		//定义流程id的key
		String processInstanceKey = "receiveTask";
		ProcessInstance pec = engine.getRuntimeService() // 运行service
				.startProcessInstanceByKey(processInstanceKey );// 流程定义表的key字段值
		
		System.out.println("流程实例ID：" + pec.getId());
		System.out.println("流程定义ID：" + pec.getProcessDefinitionId());
		
		/*查询执行id的查询*/
		Execution execution = engine.getRuntimeService()
			.createExecutionQuery()//创建执行流程的查询
				.processInstanceId(pec.getId())//使用流程实例的Id来查询
					.activityId("receivetask1") //当前活动的Id对应，ReceiveTask.bpmn中活动id的属性值
						.singleResult();
		
		/*使用流程id来设置当日销售额，用来传递业务参数*/
		engine.getRuntimeService() //运行是的service
			.setVariable(execution.getId(), "汇总当日销售额", 21000);
		
		/*向后执行一部，如果流程处于等待状态，使得流程继续执行*/
		engine.getRuntimeService()
			.signal(execution.getId());//向后执行一部
		
		/*查询执行id的查询*/
		Execution execution2 = engine.getRuntimeService()
			.createExecutionQuery()//创建执行流程的查询
				.processInstanceId(pec.getId())//使用流程实例的Id来查询
					.activityId("receivetask2") //当前活动的Id对应，ReceiveTask.bpmn中活动id的属性值
						.singleResult();
		
		/*从流程id中获取汇总当日的销售额的值*/
		Integer pay = (Integer)engine.getRuntimeService()
			.getVariable(execution2.getId(), "汇总当日销售额");
		
		System.out.println("给老板发送短信"+pay);
		
		/*向后执行一部，如果流程处于等待状态，使得流程继续执行*/
		engine.getRuntimeService()
			.signal(execution2.getId());
	}

	/**
	 * 查看任务
	 */
	@Test
	public void findTask() {
		List<Task> tesk = engine.getTaskService()// 任务相关的service
				.createTaskQuery()// 创建查询
				.taskAssignee("java_gao")// 查看指定人
				.list();

		for (Task task : tesk) {
			System.out.println("任务ID:" + task.getId());
			System.out.println("任务名称：" + task.getName());
			System.out.println("任务创建时间：" + task.getCreateTime());
			System.out.println("任务委派人：" + task.getAssignee());
			System.out.println("流程实例ID:" + task.getProcessInstanceId());
		}
	}

	/**
	 * 完成任务
	 */
	@Test
	public void completeTask() {
		engine.getTaskService().complete("22504");
	}

	/*将任务从一个人指定给另一个人*/
	@Test
	public void setAssignee(){
		//指定任务的Id
		String taskId = "";
		//指定任务的完成的人
		String userId ="周芷若";
		engine.getTaskService()
			.setAssignee(taskId, userId);
	}
}
