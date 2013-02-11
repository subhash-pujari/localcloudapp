/*
 * 
 */
package com.evoapps.cloudapp;

import java.util.Iterator;
import java.util.List;

import com.evoapps.cloudapp.engine.CheckForChangeInRepo;
import com.evoapps.cloudapp.engine.FileChangeListener;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {

	/** The Constant TAG. */
	private static final String TAG = "MainActivity";
	
	/** The m context. */
	Context mContext;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		
		setContentView(R.layout.activity_main);
		
		Intent intent = new Intent(this, FileChangeListener.class);
		
	//	if (!isServiceRunning("FileChangeListener")){
	//		startService(intent);
		//}

			
		
		Button listButton = (Button)findViewById(R.id.click_me);
		listButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContext, ShowFileList.class);
				startActivity(intent);
			}
		});
		
		Button syncButton = (Button)findViewById(R.id.sync);
		syncButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
					new CheckForChangeInRepo(mContext.getContentResolver()).execute();
			}
		});
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	
	
	
	/*private boolean isServiceRunning(String serviceName){
        boolean serviceRunning = false;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> l = am.getRunningServices(50);
        Iterator<ActivityManager.RunningServiceInfo> i = l.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningServiceInfo runningServiceInfo = (ActivityManager.RunningServiceInfo) i
                    .next();
            
            if(runningServiceInfo.service.getClassName().equals(serviceName)){
                serviceRunning = true;
            }
            Log.e(TAG, "service name>> "+ runningServiceInfo.service.getClassName());
        }
        return serviceRunning;
    }*/
}
