package com.dingmore.sample.loader;

public interface TaskListener {
	//接口启动及完成时的监听器
	public void taskStarted(TaskType type);

	public void taskFinished(TaskType type, Object result);
}
