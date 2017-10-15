package com.active.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class HelloWorld {
	
	/**
	 * 生成active得自动生成25张表
	 * 
	 */
	
	@Test
	public void testCreatTable(){
		ProcessEngineConfiguration pec=ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration(); // 获取流程引擎配置
		pec.setJdbcDriver("com.mysql.jdbc.Driver"); // 配置驱动
		pec.setJdbcUrl("jdbc:mysql://localhost:3306/bd_active"); // 配置连接地址
		pec.setJdbcUsername("root"); // 用户名
		pec.setJdbcPassword("root"); // 密码
		
		/**
		 * 配置模式  true 自动创建和更新表
		 */
		pec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		
		// 获取流程引擎对象
		ProcessEngine pe=pec.buildProcessEngine(); 
	}
	
	@Test
	public void testCreatTable2(){
		/**
		 * 加载流程引擎
		 */
		ProcessEngineConfiguration pec = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
		
		ProcessEngine engine = pec.buildProcessEngine();
		
	}
	
}
