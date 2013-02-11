/*
 * 
 */
package com.evoapps.cloudapp.engine;

import com.evoapps.cloudapp.Constants;

import android.app.IntentService;
import android.content.Intent;
import android.os.FileObserver;
import android.os.IBinder;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving fileChange events.
 * The class that is interested in processing a fileChange
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addFileChangeListener<code> method. When
 * the fileChange event occurs, that object's appropriate
 * method is invoked.
 *
 * @see FileChangeEvent
 */
public class FileChangeListener extends IntentService{

	/**
	 * Instantiates a new file change listener.
	 *
	 * @param name the name
	 */
	public FileChangeListener(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/** The tag. */
	private final String TAG = "FileChangeListener";
	
	/** The Constant selfInstance. */
	private final static FileChangeListener selfInstance = null;
	
	/* (non-Javadoc)
	 * @see android.app.IntentService#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see android.app.IntentService#onStart(android.content.Intent, int)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		
		FileObserver fileObserver = new MobileRepoFileObserver(Constants.REPO_PATH);
		fileObserver.startWatching();
		Log.e(TAG, "service started");
	}

	/* (non-Javadoc)
	 * @see android.app.IntentService#onDestroy()
	 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.e(TAG, "service destroy called");
	}
	
	/* (non-Javadoc)
	 * @see android.app.Service#onUnbind(android.content.Intent)
	 */
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.e(TAG, "service unbinded");
		return super.onUnbind(intent);
		
	}

	/* (non-Javadoc)
	 * @see android.app.IntentService#onHandleIntent(android.content.Intent)
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Log.e(TAG, "handle intent");
	}
}
