package com.evoapps.cloudapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.util.Log;

public class ReceiveFileActivity extends Activity{

	
		static final String TAG = "ReceiveFileActivity";
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			
			
			Intent intent = getIntent();
			Bundle bundle = intent.getExtras();
			if(bundle.containsKey(Intent.EXTRA_STREAM)){
				Uri uri = (Uri) bundle.getParcelable(Intent.EXTRA_STREAM);
				String scheme = uri.getScheme();
				if(scheme.equals("content")){
					String mimeType = intent.getType();
	                ContentResolver contentResolver = getContentResolver();
	                Cursor cursor = contentResolver.query(uri, null, null, null, null);
	                cursor.moveToFirst();
	                String filePath = cursor.getString(cursor.getColumnIndexOrThrow(Images.Media.DATA));
	                Log.e(TAG, "filePath>>"+filePath);
	                
	                copyImage(filePath);
				}
			}
		//	Uri uri = intent.getData();
			//Log.e(TAG, "uri>>"+uri.toString());
		}
		
		void copyImage(String filePath){
			
			String[] filePathToken = filePath.split("/");
			String fileName = "";
			if(filePathToken.length > 0){
				fileName = filePathToken[filePathToken.length-1];
				/*String[] fileNameSplit = fileName.split(".");
				if(fileNameSplit.length == 2){
					String fileNameSplit[0];
				}*/
			}
			
			File file = new File(filePath);
			try {
				FileInputStream fis = new FileInputStream(file);
				FileOutputStream fos = new FileOutputStream(Constants.REPO_PATH+"/"+fileName);
				
				byte[] buffer = new byte[1024];
				
				while(fis.read(buffer)!=-1){
					fos.write(buffer);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
