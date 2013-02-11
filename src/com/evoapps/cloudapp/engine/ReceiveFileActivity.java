package com.evoapps.cloudapp.engine;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.evoapps.cloudapp.Constants;
import com.evoapps.cloudapp.R;

public class ReceiveFileActivity extends Activity implements OnClickListener{

	
		static final String TAG = "ReceiveFileActivity";
		private Button save;
		private EditText edit;
		private String filepath;
		private String filename;
		private String fileextension;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.receivefileactivity);
			
			edit = (EditText)findViewById(R.id.filename);
			save = (Button)findViewById(R.id.save);
			save.setOnClickListener(this);
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
	                filepath = cursor.getString(cursor.getColumnIndexOrThrow(Images.Media.DATA));
	                Log.e(TAG, "filePath>>"+filepath);
				}
			}
			
			String[] filePathToken = filepath.split("/");
			
			if(filePathToken.length > 0){
				filename = filePathToken[filePathToken.length-1];
			}
			
			fileextension = (String)filename.subSequence(filename.indexOf(".")+1, filename.length());
			filename = (String)filename.subSequence(0, filename.indexOf(fileextension)-1);
			edit.setText(filename);		
			Log.e(TAG, "filename>>"+filename+"fileextension>>"+fileextension);
		}
		
	
		
		void copyImage(String filePath){
			File file = new File(filePath);
			
			filename = edit.getText().toString();
			Log.e(TAG, "before storing filename>>"+filename);
			try {
				
				String modifiedFileName = edit.getText().toString();
				FileInputStream fis = new FileInputStream(file);
				FileOutputStream fos = new FileOutputStream(Constants.REPO_PATH+"/"+filename+"."+fileextension);
				
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

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			copyImage(filepath);
		}
		
		
}
