package com.evoapps.cloudapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class FileGetReceiver extends BroadcastReceiver{

	final String TAG = "FileGetReceiver";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.e(TAG, "received");
	}
	
		
}
