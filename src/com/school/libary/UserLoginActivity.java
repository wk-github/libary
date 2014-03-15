package com.school.libary;

import com.school.event.OnGestureListenerEvent;
import com.school.view.SideBarView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class UserLoginActivity extends Activity {
	private ImageButton head_left_img;
	private ImageButton head_right_img;
	private MyOnclick onclick;
	private TextView textView;
	private Button lose_password;//忘记密码
	private Button login_button;//登录
	//定义手势检测器实例  
    private GestureDetector detector; 
    private SideBarView sideBarView;
    private void initUi() {
		// TODO Auto-generated method stub
    	lose_password = (Button) findViewById(R.id.lose_password_button);
    	login_button = (Button) findViewById(R.id.login_button);
		textView = (TextView) findViewById(R.id.head_text);
		textView.setText("登录");
		head_left_img = (ImageButton) findViewById(R.id.head_left_img);
		head_right_img = (ImageButton) findViewById(R.id.head_right_img);
		onclick = new MyOnclick();
		head_left_img.setOnClickListener(onclick);
		head_right_img.setOnClickListener(onclick);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_login);
		initUi();
		sideBarView = (SideBarView) this.findViewById(R.id.ribbonMenuView1);
        OnGestureListenerEvent onGestureListenerEvent = new OnGestureListenerEvent(getApplicationContext(),sideBarView);
        detector = new GestureDetector(onGestureListenerEvent);
        actionMethod();
	}

	private void actionMethod() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
	}

	
	 @Override
	    public boolean onTouchEvent(MotionEvent event) {
	    	// TODO Auto-generated method stub
	    	return detector.onTouchEvent(event);
	    }
	private class MyTouchClick  implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if(MotionEvent.ACTION_DOWN == event.getAction())
			{
				
			}
			else if(MotionEvent.ACTION_UP == event.getAction())
			{
				v.setBackgroundResource(R.color.button_no_press);
			}
			return false;
		}
		
	}
	private class MyOnclick implements OnClickListener 
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//菜单
			if(v==head_left_img)
			{
				sideBarView.hiddenListView();
				finish();
			}//返回
			else if(v==head_right_img)
			{
				sideBarView.showListView();
				
			}
		}
	}
}
