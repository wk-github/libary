package com.school.libary;



import com.school.event.OnGestureListenerEvent;
import com.school.view.SideBarView;

import android.os.Bundle;
import android.app.Activity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;

public class MainActivity extends Activity {
	//定义手势检测器实例  
    GestureDetector detector;  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_main);
        SideBarView sideBarView = (SideBarView) this.findViewById(R.id.ribbonMenuView1);
        OnGestureListenerEvent onGestureListenerEvent = new OnGestureListenerEvent(getApplicationContext(),sideBarView);
        detector = new GestureDetector(onGestureListenerEvent);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	// TODO Auto-generated method stub
    	return detector.onTouchEvent(event);
    }

}
