##################################
#部署流程定义涉及到底的表

#流程部署表
SELECT * FROM act_re_deployment

#流程定义表
SELECT * FROM act_re_procdef

#资源文件表
SELECT * FROM act_ge_bytearray

#系统配置表
SELECT * FROM act_ge_property

################################################
#启动流程涉及到的标

#流程实例运行时执行对象
SELECT * FROM act_ru_execution

#流程实例运行时 身份联系表
SELECT * FROM act_ru_identitylink

#流程实例运行时 用户表
SELECT * FROM act_ru_task

#活动节点历史表
SELECT * FROM act_hi_actinst

#身份联系表历史表
SELECT * FROM act_hi_identitylink

#流程实例表 历史
SELECT * FROM act_hi_procinst

#历史任务表
SELECT * FROM act_hi_taskinst

###########################################
#结束流程实例涉及到的表
#运行时  表数据全部清空
#历史表  表数据修改 或者增加了数据

#运行是流程变量
SELECT * FROM act_ru_variable

#历史流程变量
SELECT * FROM act_hi_varinst


