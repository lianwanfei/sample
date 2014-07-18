package com.dingmore.sample;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dingmore.fragment.BitmapHelp;
import com.dingmore.sample.loader.Task;
import com.dingmore.sample.loader.TaskListener;
import com.dingmore.sample.loader.TaskType;
import com.dingmore.sample.model.Child;
import com.dingmore.sample.utils.MyProgressDialog;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;

/**
 * Author: lian
 * 显示一个类别下不同的菜品
 */
public class DingmoreChildActivity extends BaseActivity implements TaskListener{

	private String tag = "DingmoreChildActivity";
	private ArrayList<Child> child;
    private ListAdapter listAdapter;
    public static BitmapUtils bitmapUtils;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.main);
    	initView();
		child = new ArrayList<Child>();
		myProgressDialog = new MyProgressDialog(this);
		myProgressDialog.show();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", getIntent().getStringExtra("parentId"));
		Task getChildTask = new Task(TaskType.Task_getChildList, this,
				params);
		getChildTask.execute();
    }

    private void initView() {
		// TODO Auto-generated method stub
    	listView=(ListView)findViewById(R.id.img_list);
    	listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(DingmoreChildActivity.this, child.get(arg2).getName(), Toast.LENGTH_SHORT).show();
			}});
	}



    private class ListAdapter extends BaseAdapter {

        private final LayoutInflater mInflater;

        public ListAdapter(Context context) {
            super();
            mInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return child.size();
        }

        @Override
        public Object getItem(int position) {
            return child.get(position);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            ImageItemHolder holder = null;
            if (view == null) {
                view = mInflater.inflate(R.layout.child_item, null);
                holder = new ImageItemHolder();
                holder.imgItem=(ImageView) view.findViewById(R.id.img_item);
                holder.childName=(TextView) view.findViewById(R.id.child_name);
                holder.imgPb=(ProgressBar) view.findViewById(R.id.img_pb);
                view.setTag(holder);
            } else {
                holder = (ImageItemHolder) view.getTag();
            }
            holder.imgPb.setProgress(0);
            holder.childName.setText(child.get(position).getName());
            bitmapUtils.display(holder.imgItem, child.get(position).getUrl(), new CustomBitmapLoadCallBack(holder));
            return view;
        }
    }

    private class ImageItemHolder {
        private ImageView imgItem;
        private TextView childName;
        private ProgressBar imgPb;
    }

    public class CustomBitmapLoadCallBack extends DefaultBitmapLoadCallBack<ImageView> {
        private final ImageItemHolder holder;

        public CustomBitmapLoadCallBack(ImageItemHolder holder) {
            this.holder = holder;
        }

        @Override
        public void onLoading(ImageView container, String uri, BitmapDisplayConfig config, long total, long current) {
            this.holder.imgPb.setProgress((int) (current * 100 / total));
        }

        @Override
        public void onLoadCompleted(ImageView container, String uri, Bitmap bitmap, BitmapDisplayConfig config, BitmapLoadFrom from) {
            //super.onLoadCompleted(container, uri, bitmap, config, from);
            fadeInDisplay(container, bitmap);
            this.holder.imgPb.setProgress(100);
        }
    }

    private static final ColorDrawable TRANSPARENT_DRAWABLE = new ColorDrawable(android.R.color.transparent);

    private void fadeInDisplay(ImageView imageView, Bitmap bitmap) {
        final TransitionDrawable transitionDrawable =
                new TransitionDrawable(new Drawable[]{
                        TRANSPARENT_DRAWABLE,
                        new BitmapDrawable(imageView.getResources(), bitmap)
                });
        imageView.setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(500);
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
				child = jsonToChild(repObject.getJSONArray("child"));
			} else {
				System.out.println(tag + repObject.getString("data"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(tag + "result===" + result);
        bitmapUtils = BitmapHelp.getBitmapUtils(this.getApplicationContext());
        bitmapUtils.configDefaultLoadingImage(R.drawable.ic_launcher);
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.bitmap);
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
        bitmapUtils.configDiskCacheEnabled(true);
        bitmapUtils.configMemoryCacheEnabled(true);
        bitmapUtils.configDefaultBitmapMaxSize(BitmapCommonUtils.getScreenSize(this).scaleDown(3));
        listAdapter = new ListAdapter(this);
        listView.setAdapter(listAdapter);

	}

	private ArrayList<Child> jsonToChild(JSONArray jsonArray) {
		ArrayList<Child> tempChild = new ArrayList<Child>();
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				tempChild.add(new Child(
						jsonArray.getJSONObject(i).getInt("id"), jsonArray
								.getJSONObject(i).getString("name"), jsonArray
								.getJSONObject(i).getInt("parentId"), jsonArray
								.getJSONObject(i).getString("parentName"), jsonArray
								.getJSONObject(i).getString("url")));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tempChild;
	
	}

}