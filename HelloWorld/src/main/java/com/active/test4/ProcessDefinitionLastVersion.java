package com.active.test4;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;

public class ProcessDefinitionLastVersion {
	/**
	 * 获取默认工作流程实例，默认加载activiti.cfg.xml
	 */
	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 根据版本来获取最新版本的工作流程定义
	 */
	@Test
	public void getByLastVersion() {
		//获取流程定义的集合，进行升序排列
		List<ProcessDefinition> list = processEngine.getRepositoryService()//获取服务的信息
			.createProcessDefinitionQuery()//创建查询条件
			.orderByProcessDefinitionVersion().asc()//根据版本进行升序排列
			.list();
		//定义有序map相同的key,假如有相同的key,map会进行覆盖
		Map<String, ProcessDefinition> allList = new LinkedHashMap<String, ProcessDefinition>();
		for (ProcessDefinition processDefinition : list) {
			allList.put(processDefinition.getKey(),processDefinition);
		}
		//将他转换成list集合
		List<ProcessDefinition>processDefinitions = new ArrayList<ProcessDefinition>(allList.values());
		
		for (ProcessDefinition processDefinition : processDefinitions) {
			System.out.println("ID_："+processDefinition.getId());
	        System.out.println("NAME_："+processDefinition.getName());
	        System.out.println("KEY_："+processDefinition.getKey());
	        System.out.println("VERSION_："+processDefinition.getVersion());
	        System.out.println("===================");
		}
		
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
}
