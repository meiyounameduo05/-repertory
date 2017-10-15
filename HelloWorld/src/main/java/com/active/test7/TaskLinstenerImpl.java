package com.active.test7;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class TaskLinstenerImpl implements TaskListener {
	
	/*用来指定任务的办理人*/
	public void notify(DelegateTask delegateTask) {
		/*可以指定个人任务的办理人，也可以指定组任务的办理人*/
		/**
		 * 可以通了过类来查询数据库，将下一个任务的办理人查询获取，然后通过setAggignee()的方法指定任务的办理人
		 */
		delegateTask.setAssignee("灭绝师太");
	}

}
