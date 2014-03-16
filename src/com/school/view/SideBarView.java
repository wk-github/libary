package com.school.view;

import java.util.ArrayList;
import java.util.List;

import com.school.libary.R;
import com.school.libary.UserLoginActivity;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SideBarView extends LinearLayout {
	private ListView listView = null;
	private List<String> list=null; 
	private MyBaseAdapter myBaseAdapter = null;
	private int list_icon[];
	private View rbmOutsideView;
	private ImageView head;
	public SideBarView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		inflateLayout();
		initUi();
	}
	public SideBarView(Context context, AttributeSet attrs) {
		super(context, attrs);

		inflateLayout();
		initUi();
	}
	//隐藏侧栏
	public  void hiddenListView() {
		// TODO Auto-generated method stub
		
		
		rbmOutsideView.setVisibility(View.GONE);
		rbmOutsideView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.rbm_out_to_left));
	}

	//显示侧栏
	public void showListView() {
		// TODO Auto-generated method stub
		
		rbmOutsideView.setVisibility(View.VISIBLE);
		rbmOutsideView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.rbm_in_from_left));
	}
	private void inflateLayout() {

		LayoutInflater.from(getContext()).inflate(R.layout.sidebar, this, true);

	}
	private void initUi()
	{
		head = (ImageView) this.findViewById(R.id.head_portrait);
		head.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				hiddenListView();
				Intent intent = new Intent(getContext(),UserLoginActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//如果activity加载过就不会在重新加载
				getContext().startActivity(intent);
			}
		});
		rbmOutsideView = findViewById(R.id.left_item);
		int i[] = {
				R.drawable.search,
				R.drawable.dynamic,
				R.drawable.location,
				R.drawable.user_item,
				R.drawable.setting
		};
		list_icon = i;
		list = new ArrayList<String>();
		list.add("搜索");
		list.add("动态");
		list.add("地图");
		list.add("用户");
		list.add("设置");
		listView = (ListView) this.findViewById(R.id.sidebar_view);
		myBaseAdapter = new MyBaseAdapter(getContext());
		listView.setAdapter(myBaseAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Log.i("message",arg2+"-"+arg3);
			}
		});
		
	}
	private class MyBaseAdapter extends BaseAdapter{
		
		private Context context;
		public MyBaseAdapter(Context context) {
			// TODO Auto-generated constructor stub
			this.context = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int position, View view, ViewGroup viewGroup) {
			// TODO Auto-generated method stub
			
			TextView mTextView = null;
			ImageView imageView = null;
			
			if(view == null)
			{
				view = LayoutInflater.from(context).inflate(R.layout.sidebar_list,null);
				mTextView = (TextView) view.findViewById(R.id.sidebar_function);
				SpannableString msp = new SpannableString(list.get(position));
				msp.setSpan(new TypefaceSpan("sans-serif"), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   
				mTextView.setText(msp);
				imageView = (ImageView) view.findViewById(R.id.sidebar_img);
				imageView.setBackgroundResource(list_icon[position]);
				
			}else
			{
				mTextView = (TextView) view.findViewById(R.id.sidebar_function);
				mTextView.setText(list.get(position));
				imageView = (ImageView) view.findViewById(R.id.sidebar_img);
				imageView.setBackgroundResource(list_icon[position]);
			}
			return view;
		}
		
	}

}
