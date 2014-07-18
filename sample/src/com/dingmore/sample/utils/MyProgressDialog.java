package com.dingmore.sample.utils;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.dingmore.sample.R;

public class MyProgressDialog extends ProgressDialog{

	public MyProgressDialog(Context context) {
		super(context);
	}
	
	public MyProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setCancelable(false);
		setContentView(R.layout.myprogress_dialog);
	}
	
/*	public static MyProgressDialog show(Context ctx){
		MyProgressDialog d = new MyProgressDialog(ctx);
		d.setCancelable(false);
		d.show();
		return d;
	}*/
}