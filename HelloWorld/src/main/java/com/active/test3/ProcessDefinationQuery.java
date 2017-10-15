package com.active.test3;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;

public class ProcessDefinationQuery {
	/**
	 * 获取默认的引擎实例，会自动获取activiti.cfg.xml
	 */
	private ProcessEngine processEngine =ProcessEngines.getDefaultProcessEngine();
	
	@Test
	public void list() {
		List<ProcessDefinition> processDefinitions = processEngine.getRepositoryService()//获取service
			.createProcessDefinitionQuery()//创建流程定义查询
				.processDefinitionKey("myFirstProcess")//通过key来进行查询
					.list();
		
		for (ProcessDefinition pdf : processDefinitions) {
			System.out.println("_Id"+pdf.getId());
			System.out.println("_CATEGORY"+pdf.getCategory());
			System.out.println("_NAME"+pdf.getName());
			System.out.println("KEY"+pdf.getKey());
			System.out.println("VERSION"+pdf.getVersion());
			System.out.println("DiagramResourceName"+pdf.getDiagramResourceName());
			System.out.println("+++++++++++++++++++++++++++++++++");
		}
	}
	
	@Test
	public void getById() {
		String processDefinitionId = "myFirstProcess:2:7504";
		ProcessDefinition pdf = processEngine.getRepositoryService()//获取service
				.createProcessDefinitionQuery()//创建流程定义查询
					.processDefinitionId(processDefinitionId)//通过id来进行查询
						.singleResult();//获取单个的结果
		
		System.out.println("_Id"+pdf.getId());
		System.out.println("_CATEGORY"+pdf.getCategory());
		System.out.println("_NAME"+pdf.getName());
		System.out.println("KEY"+pdf.getKey());
		System.out.println("VERSION"+pdf.getVersion());
		System.out.println("DiagramResourceName"+pdf.getDiagramResourceName());
	}
	
	
}
