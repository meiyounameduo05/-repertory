package com.gaoxueyan.Student;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class StudentCheck {

	/**
	 * 获取默认工作流程实例，默认加载activiti.cfg.xml
	 */
	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 部署流程定义使用classpath方式
	 */
	@Test
	public void deployWhithClassPath(){
		//获取部署对象
		Deployment deploy = processEngine.getRepositoryService()//获取部署对象
			.createDeployment()//创建部署
			.addClasspathResource("Diagrams/StudentLeaveProcess.bpmn")//加载资源
			.addClasspathResource("Diagrams/StudentleaveProcess.png") //加载资源
			.name("学生请假流程")//给部署项目起名字
			.deploy();//部署成功
		
		System.out.println("部署流程的Id:"+deploy.getId());
		System.out.println("部署流程的名字:"+deploy.getName());
	}
	
	/**
	 * 启动流程实例
	 */
	@Test
	public void start(){
		ProcessInstance pec = processEngine.getRuntimeService() //运行service
			.startProcessInstanceByKey("studentLeaveProcess");//流程定义表的key字段值
		System.out.println("流程实例ID："+pec.getId());
		System.out.println("流程定义ID："+pec.getProcessDefinitionId());
	}
	
	/**
	 * 查看任务
	 */
	@Test
	public void findTask(){
		List<Task> tesk =processEngine.getTaskService()//任务相关的service
		.createTaskQuery()//创建查询
		.taskAssignee("王五")//查看指定人
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
	 * 完成任务执行流程
	 */
	@Test
	public void completeTesk(){
		processEngine.getTaskService()//任务相关的service
			.complete("72502");
	}
	
	/**
	 * 删除所有Key相同的流程定义
	 * @throws Exception
	 */
	@Test
	public void deleteByKey()throws Exception{
	    List<ProcessDefinition> pdList=processEngine.getRepositoryService()  // 获取service类
	            .createProcessDefinitionQuery() // 创建流程定义查询
	            .processDefinitionKey("studentLeaveProcess") // 根据Key查询
	            .list();
	    for(ProcessDefinition pd:pdList){  // 遍历集合 获取流程定义的每个部署Id，根据这个id来删除所有流程定义
	        processEngine.getRepositoryService()
	        .deleteDeployment(pd.getDeploymentId(), true); 
	    }
	}
	
	/**
	 * 查看流程状态（正在执行或者已经执行结束）
	 */
	@Test
	public void processStates() {
		ProcessInstance result = processEngine.getRuntimeService() //运行时service
			.createProcessInstanceQuery() //创建流程实例查询
				.processInstanceId("222222222") //用流程实例id进行查询
					.singleResult();
		if(result != null){
			System.out.println("流程正在进行");
		}else {
			System.out.println("流程已经结束");
		}
		
	}
	
	/**
	 * 历史流程状态的查询
	 */
	
	@Test
	public void historyTask() {
		List<HistoricTaskInstance> list = processEngine.getHistoryService()//历史相关的service
			.createHistoricTaskInstanceQuery()//创建历史任务实例查询
				.processInstanceId("67501")//根据历史任务的id查询
					.finished() //查询已经完成的任务
						.list();
		
		for (HistoricTaskInstance hai : list) {
			 System.out.println("任务ID:"+hai.getId());
	            System.out.println("流程实例ID:"+hai.getProcessInstanceId());
	            System.out.println("流程名称："+hai.getName());
	            System.out.println("办理人："+hai.getAssignee());
	            System.out.println("开始时间："+hai.getStartTime());
	            System.out.println("结束时间："+hai.getEndTime());
	            System.out.println("===========================");
		}
	}
	
	/**
	 * 历史活动查询
	 */
	@Test
	public void historyActInstanceList() {
		List<HistoricActivityInstance> list = processEngine.getHistoryService() //历史相关的service
			.createHistoricActivityInstanceQuery()//创建历史活动实例插寻
				.processInstanceId("67501")//执行流程id
					.finished() 
						.list();
		
		for (HistoricActivityInstance hai : list) {
			 System.out.println("任务ID:"+hai.getId());
	            System.out.println("流程实例ID:"+hai.getProcessInstanceId());
	            System.out.println("活动名称："+hai.getActivityName());
	            System.out.println("办理人："+hai.getAssignee());
	            System.out.println("开始时间："+hai.getStartTime());
	            System.out.println("结束时间："+hai.getEndTime());
	            System.out.println("===========================");
		}
	}
}
