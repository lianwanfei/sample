package com.dingmore.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.dingmore.sample.utils.MyProgressDialog;
/**
 * 基础Activity类
 */
public class BaseActivity extends Activity{
	protected MyProgressDialog myProgressDialog;  
	protected ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
}
