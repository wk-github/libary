package com.school.event;

import com.school.view.SideBarView;

import android.content.Context;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.Toast;

public class OnGestureListenerEvent implements OnGestureListener {

	private Context context;
	private SideBarView sideBarView;
	public OnGestureListenerEvent(Context context)
	{
		this.context = context;
	}
	
	public OnGestureListenerEvent(Context applicationContext,
			SideBarView sideBarView) {
		// TODO Auto-generated constructor stub
		this.context = applicationContext;
		this.sideBarView = sideBarView;
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		 
		return false;
	}

	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		//œÚ”“ª¨∂Ø
		 if (event1.getX() - event2.getX() < -110)
		 {
			
			 sideBarView.showListView();
		 }else if(event1.getX() - event2.getX()>110)
		 {
			 
			 sideBarView.hiddenListView();
		 }
		return false;
	}

	

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
