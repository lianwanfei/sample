package com.dingmore.sample;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dingmore.sample.loader.Task;
import com.dingmore.sample.loader.TaskListener;
import com.dingmore.sample.loader.TaskType;
import com.dingmore.sample.model.parents;
import com.dingmore.sample.utils.MyProgressDialog;

/**
 * Author: lian 
 * 用于显示菜的分类列表
 */
public class DingmoreParentsActivity extends BaseActivity implements TaskListener {
	private String tag = "DingmoreParentsActivity";
	private ArrayList<parents> parents;
	private ListAdapter listAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
		parents = new ArrayList<parents>();
		myProgressDialog = new MyProgressDialog(this);
		myProgressDialog.show();
		HashMap<String, Object> params = new HashMap<String, Object>();
		Task getParentsTask = new Task(TaskType.Task_GetParentsList, this,
				params);
		getParentsTask.execute();
	}

	private void initView() {
		// TODO Auto-generated method stub
		listView = (ListView) findViewById(R.id.img_list);
		listAdapter = new ListAdapter(this);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DingmoreParentsActivity.this,
						DingmoreChildActivity.class);
				intent.putExtra("parentId", String.valueOf(parents.get(arg2).getId()));
				DingmoreParentsActivity.this.startActivity(intent);
			}
		});
	}

	private class ListAdapter extends BaseAdapter {

		private final LayoutInflater mInflater;

		public ListAdapter(Context context) {
			super();
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			System.out.println(tag+parents.size()+parents);
			return parents.size();
		}

		@Override
		public Object getItem(int position) {
			return parents.get(position);
		}

		@Override
		public long getItemId(int i) {
			return i;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
				ItemHolder holder = null;
				if (convertView == null) {
					convertView = mInflater.inflate(R.layout.parent_item, null);
					holder = new ItemHolder();
					holder.parentName = (TextView)convertView.findViewById(R.id.parent_name);
					convertView.setTag(holder);
				} else {
					holder = (ItemHolder) convertView.getTag();
				}
				holder.parentName.setText(parents.get(position).getName());
				return convertView;
		}


	}

	private class ItemHolder {
		private TextView parentName;
	}

	@Override
	public void taskStarted(TaskType type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void taskFinished(TaskType type, Object result) {
		JSONObject repObject = null;
		if (myProgressDialog.isShowing()) {
			myProgressDialog.dismiss();
		}
		try {
			repObject = new JSONObject(result.toString());
			if (1 == repObject.getInt("result"))// 若账号存在即获取成功，则判断账号是否已激活
			{
				parents = jsonToParents(repObject.getJSONArray("parents"));
				listView.setAdapter(listAdapter);
				listAdapter.notifyDataSetChanged();
			} else {
				System.out.println(tag + repObject.getString("data"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(tag + "result===" + result);

	}

	private ArrayList<com.dingmore.sample.model.parents> jsonToParents(
			JSONArray jsonArray) {
		// TODO Auto-generated method stub
		ArrayList<parents> tempParents = new ArrayList<parents>();
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				tempParents.add(new parents(jsonArray.getJSONObject(i).getInt(
						"id"), jsonArray.getJSONObject(i).getString("name")));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tempParents;
	}

}