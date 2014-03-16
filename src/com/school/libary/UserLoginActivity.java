package com.school.libary;

import com.school.event.OnGestureListenerEvent;
import com.school.view.SideBarView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
	private Button lose_password;// 忘记密码
	private Button login_button;// 登录
	private MyTouchClick myTouchClick;
	// 定义手势检测器实例
	private GestureDetector detector;
	private SideBarView sideBarView;
	private Handler myHandler;
	private void initUi() {
		// TODO Auto-generated method stub
		lose_password = (Button) findViewById(R.id.lose_password_button);
		login_button = (Button) findViewById(R.id.login_button);
		textView = (TextView) findViewById(R.id.head_text);
		textView.setText("登录");
		head_left_img = (ImageButton) findViewById(R.id.head_left_img);
		head_right_img = (ImageButton) findViewById(R.id.head_right_img);
		onclick = new MyOnclick();
		myTouchClick = new MyTouchClick();
		head_left_img.setOnClickListener(onclick);
		head_right_img.setOnClickListener(onclick);
		login_button.setOnTouchListener(myTouchClick);
		lose_password.setOnTouchListener(myTouchClick);
		myHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				String method = msg.getData().getString("method");
				if("success_back".equals(method))//跳转到登录之前的页面
				{
					finish();
				}else if("success_user".equals(method))//跳转到个人中心
				{
					
				}
				else if("error".equals(method))//输出错误信息
				{
					
				}
			}
		};
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_login);
		initUi();
		sideBarView = (SideBarView) this.findViewById(R.id.ribbonMenuView1);
		OnGestureListenerEvent onGestureListenerEvent = new OnGestureListenerEvent(
				getApplicationContext(), sideBarView);
		detector = new GestureDetector(onGestureListenerEvent);
		actionMethod();
	}

	private void actionMethod() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
	}

	// 用户登录
	private void login() {
		
	}

	// 用户找回密码
	private void findPassword() {

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
	}

	private class MyTouchClick implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (MotionEvent.ACTION_DOWN == event.getAction()) {
				v.setBackgroundResource(R.drawable.button_style);
				if (v == login_button) {
					login();
				} else if (v == lose_password) {
					findPassword();
				}
			} else if (MotionEvent.ACTION_UP == event.getAction()) {
				v.setBackgroundResource(R.drawable.button_shape);
			}
			return false;
		}

	}

	private class MyOnclick implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// 菜单
			if (v == head_left_img) {
				sideBarView.hiddenListView();
				finish();
			}// 返回
			else if (v == head_right_img) {
				sideBarView.showListView();
			}
		}
	}
	
}
