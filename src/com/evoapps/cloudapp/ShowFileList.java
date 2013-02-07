/*
 * 
 */
package com.evoapps.cloudapp;

import java.io.File;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.evoapps.cloudapp.datastructure.FileInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class ShowFileList.
 */
public class ShowFileList  extends ListActivity implements OnItemLongClickListener{

		/** The list. */
		ListView list;
		ArrayList <FileInfo>listOfFiles;
		Context mContext;
		final String TAG = "ShowFileList";
		/* (non-Javadoc)
		 * @see android.app.Activity#onCreate(android.os.Bundle)
		 */
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			listOfFiles = new ArrayList<FileInfo>();
			mContext = this;
			Cursor cursor = getContentResolver().query(Uri.parse(Constants.authority), null, null, null, null);
			addToFileList(cursor);
			list = getListView();
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.available_file_list, cursor, new String[]{"name"}, new int[]{R.id.file_name});
			list.setAdapter(adapter);
			list.setOnItemLongClickListener(this);
		}

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
			//filename
			Log.e(TAG, "arg2>>"+arg2);
			FileInfo fileInfo = listOfFiles.get(arg2);
			String filename = "";
			if(fileInfo != null){
				filename = fileInfo.getName();
			}
			//path of the file
			String path = Constants.REPO_PATH +"/"+filename;
			
			
			//intent
			Intent intent = new Intent();  
			intent.setAction(android.content.Intent.ACTION_VIEW);  
			File file = new File(path);  
			Log.e(TAG, "filename>>"+filename);
			String[] filenamesplit = filename.split(".");
			Log.e(TAG, "filename>>filenamesplit.length>>"+filenamesplit.length);
			if(filename.contains(".")){
				Log.e(TAG, "contains .");
			}
				if(filenamesplit[filenamesplit.length-1].equals("jpeg")){
					intent.setDataAndType(Uri.fromFile(file), "image/*");  
				}else if(filenamesplit[filenamesplit.length-1].equals("mp4")){
					intent.setDataAndType(Uri.fromFile(file), "video/*");  
				}
				startActivity(intent);
			return true;
		}
		
		
		void launchViewActivity(int arg2){
			
		}
		
		void addToFileList(Cursor cursor){
			
			if(cursor != null){
				cursor.moveToFirst();
				do{
					FileInfo fileInfo = new FileInfo();
					String filename = cursor.getString(cursor.getColumnIndex(Constants.NAME));
					fileInfo.setName(filename);
					Log.e(TAG, "filename add to list>>"+filename);
					listOfFiles.add(fileInfo);
					cursor.moveToNext();
				}while(!cursor.isLast());
				
				//add last element
				FileInfo fileInfo = new FileInfo();
				String filename = cursor.getString(cursor.getColumnIndex(Constants.NAME));
				fileInfo.setName(filename);
				Log.e(TAG, "filename add to list>>"+filename);
				listOfFiles.add(fileInfo);
				
				
			}
		}
}