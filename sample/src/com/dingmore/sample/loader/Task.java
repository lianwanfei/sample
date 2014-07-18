package com.dingmore.sample.loader;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.AsyncTask;

import com.dingmore.sample.DingmoreApplication;
/*
 * 接口相关
 */
public class Task extends AsyncTask<String, Void, String> {
    public static  String SERVICE_IP=DingmoreApplication.getService();
	private String mTaskResult = null;//接口返回值
	private TaskListener mTaskListener = null;//接口监听器
	public TaskType taskType = null;//接口类型
	private HashMap<String, Object> mTaskParams = null;//接口参数
	
	public ArrayList<Task> taskContainer;
	
	public Task(TaskType type, TaskListener listener, HashMap<String, Object> params)
	{	SERVICE_IP=DingmoreApplication.getService();
		taskType = type;
		mTaskListener = listener;
		mTaskParams = params;
	}
	
	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
		
		mTaskListener = null;
		
		if (taskContainer != null) {
			taskContainer.remove(this);
		}
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		if (mTaskListener != null) {
			mTaskListener.taskFinished(taskType, mTaskResult);
			System.out.println("jumptestmtasklistener="+mTaskListener);
		}
		
		if (taskContainer != null) {
			taskContainer.remove(this);
		}

	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		if (mTaskListener != null) {
			mTaskListener.taskStarted(taskType);
		}
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		String url=null;
		switch (taskType) {
			case Task_GetParentsList:
				url=SERVICE_IP+"/Task_GetParentsList?";
//				mTaskResult=NetHelper.getDataByHttpClient(url);
				//以下使用测试数据
				mTaskResult="{'result':1,'parents':[{'name':'西餐面点','id':0},{'name':'四川风味','id':1},{'name':'广东粤菜','id':2},{'name':'西餐面点','id':3}]}";
				break;
			case Task_getChildList:
				url=SERVICE_IP+"/Task_getChildList?";
//				mTaskResult=NetHelper.getDataByHttpClient(url);
				//以下使用测试数据
				switch (Integer.valueOf(mTaskParams.get("parentId").toString())) {
				case 0:
					mTaskResult = "{'result':1,'child':[{'id':0,'name':'奶酪火锅','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-04/e3382371091f8f2b3f7e5cd5bd1a1f9e.jpg'},{'id':1,'name':'巧克力小火锅','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-04/4b28beef820413a32b7aa66a26fb02ff.jpg'},{'id':2,'name':'丝滑的诱惑','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-04/b98c0db56463b4e14a811d3544f6229e.jpg'},{'id':3,'name':'硬朗的甜蜜','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-03/4db8844f6dfd0f22b2d7891c94827e92.jpg'},{'id':4,'name':'圣诞布丁','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-03/876b72d0b9b8c447fd33eefe22807bef.jpg'},{'id':5,'name':'英式司康','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-03/98dc1416dbea6d49a58911a559d04bb2.jpg'},{'id':6,'name':'奶酪火锅','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-04/e3382371091f8f2b3f7e5cd5bd1a1f9e.jpg'},{'id':7,'name':'巧克力小火锅','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-04/4b28beef820413a32b7aa66a26fb02ff.jpg'},{'id':8,'name':'丝滑的诱惑','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-04/b98c0db56463b4e14a811d3544f6229e.jpg'},{'id':9,'name':'硬朗的甜蜜','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-03/4db8844f6dfd0f22b2d7891c94827e92.jpg'},{'id':10,'name':'圣诞布丁','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-03/876b72d0b9b8c447fd33eefe22807bef.jpg'},{'id':11,'name':'英式司康','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-03/98dc1416dbea6d49a58911a559d04bb2.jpg'}]}";
					break;
				case 1:
					mTaskResult = "{'result':1,'child':[{'id':0,'name':'麻婆豆腐','parentId':'1','parentName':'四川风味','url':'http://www.chinacaipu.com/d/file/menu/sichuanfengwei/2014-07-10/2672cb7792bd3e5625ab21884535b1c5.jpg'},{'id':1,'name':'椒麻肚丝','parentId':'1','parentName':'四川风味','url':'http://www.chinacaipu.com/d/file/menu/sichuanfengwei/2014-07-11/abe1b15d34f0dc1cfbab4a3a2c56bd66.jpg'},{'id':2,'name':'手撕鸡','parentId':'1','parentName':'四川风味','url':'http://www.chinacaipu.com/d/file/menu/sichuanfengwei/2014-07-11/a616650c30697ff73abf6d083a0b1967.jpg'},{'id':3,'name':'四川甜水面','parentId':'1','parentName':'四川风味','url':'http://www.chinacaipu.com/d/file/menu/sichuanfengwei/2014-07-02/e0604ea970463006c9e0b6a8ae7f413b.jpg'},{'id':4,'name':'红烧牛肉面','parentId':'1','parentName':'四川风味','url':'http://www.chinacaipu.com/d/file/menu/sichuanfengwei/2014-07-02/51c99208aa7dd327e1cb5dd07fe6e0ba.jpg'},{'id':5,'name':'麻婆豆腐','parentId':'1','parentName':'四川风味','url':'http://www.chinacaipu.com/d/file/menu/sichuanfengwei/2014-07-10/2672cb7792bd3e5625ab21884535b1c5.jpg'},{'id':0,'name':'麻婆豆腐','parentId':'1','parentName':'四川风味','url':'http://www.chinacaipu.com/d/file/menu/sichuanfengwei/2014-07-10/2672cb7792bd3e5625ab21884535b1c5.jpg'},{'id':1,'name':'椒麻肚丝','parentId':'1','parentName':'四川风味','url':'http://www.chinacaipu.com/d/file/menu/sichuanfengwei/2014-07-11/abe1b15d34f0dc1cfbab4a3a2c56bd66.jpg'},{'id':2,'name':'手撕鸡','parentId':'1','parentName':'四川风味','url':'http://www.chinacaipu.com/d/file/menu/sichuanfengwei/2014-07-11/a616650c30697ff73abf6d083a0b1967.jpg'},{'id':3,'name':'四川甜水面','parentId':'1','parentName':'四川风味','url':'http://www.chinacaipu.com/d/file/menu/sichuanfengwei/2014-07-02/e0604ea970463006c9e0b6a8ae7f413b.jpg'},{'id':4,'name':'红烧牛肉面','parentId':'1','parentName':'四川风味','url':'http://www.chinacaipu.com/d/file/menu/sichuanfengwei/2014-07-02/51c99208aa7dd327e1cb5dd07fe6e0ba.jpg'},{'id':5,'name':'麻婆豆腐','parentId':'1','parentName':'四川风味','url':'http://www.chinacaipu.com/d/file/menu/sichuanfengwei/2014-07-10/2672cb7792bd3e5625ab21884535b1c5.jpg'}]}";
					break;
				case 2:
					mTaskResult = "{'result':1,'child':[{'id':0,'name':'腊肠煲仔饭','parentId':'1','parentName':'广东粤菜','url':'http://www.chinacaipu.com/d/file/menu/guangdongecai/2014-07-11/905796675f826e13e4048f1c382a36ac.jpg'},{'id':1,'name':'香煎银鳕鱼','parentId':'1','parentName':'广东粤菜','url':'http://www.chinacaipu.com/d/file/menu/guangdongecai/2014-07-11/9dcf34779a86118fc4298d3cc63aa1a3.jpg'},{'id':2,'name':'叉烧肉','parentId':'1','parentName':'广东粤菜','url':'http://www.chinacaipu.com/d/file/menu/guangdongecai/2014-07-11/b55872f30afcd92da7f9f2cdc71014dd.jpg'},{'id':3,'name':'荷香蒸滑鸡','parentId':'1','parentName':'广东粤菜','url':'http://www.chinacaipu.com/d/file/menu/guangdongecai/2014-07-11/9532900370a5c692e49b45b04e5ca908.jpg'},{'id':4,'name':'酱油黑椒蘑菇','parentId':'1','parentName':'广东粤菜','url':'http://www.chinacaipu.com/d/file/menu/guangdongecai/2014-07-11/a41e2c3d6f41cf1ec1b83b144381ab27.jpg'},{'id':0,'name':'腊肠煲仔饭','parentId':'1','parentName':'广东粤菜','url':'http://www.chinacaipu.com/d/file/menu/guangdongecai/2014-07-11/905796675f826e13e4048f1c382a36ac.jpg'},{'id':1,'name':'香煎银鳕鱼','parentId':'1','parentName':'广东粤菜','url':'http://www.chinacaipu.com/d/file/menu/guangdongecai/2014-07-11/9dcf34779a86118fc4298d3cc63aa1a3.jpg'},{'id':2,'name':'叉烧肉','parentId':'1','parentName':'广东粤菜','url':'http://www.chinacaipu.com/d/file/menu/guangdongecai/2014-07-11/b55872f30afcd92da7f9f2cdc71014dd.jpg'},{'id':3,'name':'荷香蒸滑鸡','parentId':'1','parentName':'广东粤菜','url':'http://www.chinacaipu.com/d/file/menu/guangdongecai/2014-07-11/9532900370a5c692e49b45b04e5ca908.jpg'},{'id':4,'name':'酱油黑椒蘑菇','parentId':'1','parentName':'广东粤菜','url':'http://www.chinacaipu.com/d/file/menu/guangdongecai/2014-07-11/a41e2c3d6f41cf1ec1b83b144381ab27.jpg'},{'id':5,'name':'腊肠煲仔饭','parentId':'1','parentName':'广东粤菜','url':'http://www.chinacaipu.com/d/file/menu/guangdongecai/2014-07-11/905796675f826e13e4048f1c382a36ac.jpg'},{'id':6,'name':'香煎银鳕鱼','parentId':'1','parentName':'广东粤菜','url':'http://www.chinacaipu.com/d/file/menu/guangdongecai/2014-07-11/9dcf34779a86118fc4298d3cc63aa1a3.jpg'},{'id':7,'name':'叉烧肉','parentId':'1','parentName':'广东粤菜','url':'http://www.chinacaipu.com/d/file/menu/guangdongecai/2014-07-11/b55872f30afcd92da7f9f2cdc71014dd.jpg'},{'id':8,'name':'荷香蒸滑鸡','parentId':'1','parentName':'广东粤菜','url':'http://www.chinacaipu.com/d/file/menu/guangdongecai/2014-07-11/9532900370a5c692e49b45b04e5ca908.jpg'},{'id':9,'name':'酱油黑椒蘑菇','parentId':'1','parentName':'广东粤菜','url':'http://www.chinacaipu.com/d/file/menu/guangdongecai/2014-07-11/a41e2c3d6f41cf1ec1b83b144381ab27.jpg'}]}";
					break;
				case 3:
					mTaskResult = "{'result':1,'child':[{'id':0,'name':'奶酪火锅333','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-04/e3382371091f8f2b3f7e5cd5bd1a1f9e.jpg'},{'id':1,'name':'巧克力小火锅','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-04/4b28beef820413a32b7aa66a26fb02ff.jpg'},{'id':2,'name':'丝滑的诱惑','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-04/b98c0db56463b4e14a811d3544f6229e.jpg'},{'id':3,'name':'硬朗的甜蜜','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-03/4db8844f6dfd0f22b2d7891c94827e92.jpg'},{'id':4,'name':'圣诞布丁','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-03/876b72d0b9b8c447fd33eefe22807bef.jpg'},{'id':5,'name':'英式司康','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-03/98dc1416dbea6d49a58911a559d04bb2.jpg'},{'id':6,'name':'奶酪火锅','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-04/e3382371091f8f2b3f7e5cd5bd1a1f9e.jpg'},{'id':7,'name':'巧克力小火锅','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-04/4b28beef820413a32b7aa66a26fb02ff.jpg'},{'id':8,'name':'丝滑的诱惑','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-04/b98c0db56463b4e14a811d3544f6229e.jpg'},{'id':9,'name':'硬朗的甜蜜','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-03/4db8844f6dfd0f22b2d7891c94827e92.jpg'},{'id':10,'name':'圣诞布丁','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-03/876b72d0b9b8c447fd33eefe22807bef.jpg'},{'id':11,'name':'英式司康','parentId':'0','parentName':'西餐面点','url':'http://www.chinacaipu.com/d/file/menu/xicanmiandian/2014-07-03/98dc1416dbea6d49a58911a559d04bb2.jpg'}]}";
					break;
				default:
					break;
				}
				break;
			default:
				break;
		}
		System.out.println("url===="+url);
		System.out.println("mTaskResult===="+mTaskResult);
		return null;
	}
	
	
}
