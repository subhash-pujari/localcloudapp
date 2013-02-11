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
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.evoapps.cloudapp.datastructure.FileInfo;
import com.evoapps.cloudapp.engine.FileUtility;
import com.evoapps.cloudapp.ui.FileListItemAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class ShowFileList.
 */
public class ShowFileList  extends ListActivity implements OnItemLongClickListener, OnItemClickListener{

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
			setContentView(R.layout.showfilelist);
			listOfFiles = new ArrayList<FileInfo>();
			mContext = this;
			Cursor cursor = getContentResolver().query(Uri.parse(Constants.authority), null, null, null, null);
			if(cursor.getCount()>0){
				addToFileList(cursor);
			}
			
			list = getListView();
			//SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.available_file_list, cursor, new String[]{"name"}, new int[]{R.id.file_name});
			FileListItemAdapter adapterList = new FileListItemAdapter(this, listOfFiles);
			
			list.setAdapter(adapterList);
			list.setOnItemLongClickListener(this);
			list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
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
			String file_extension = (String)filename.subSequence(filename.indexOf(".")+1, filename.length());
			int type = FileUtility.getType(file_extension);
			
			
			Log.e(TAG, "type>>file_extension"+type+">>"+file_extension);
			if(type == 0){
				intent.setDataAndType(Uri.fromFile(file), "image/*"); 
			}else if(type == 1){
				intent.setDataAndType(Uri.fromFile(file), "audio/*"); 
			}else if(type == 2){
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
					long modifiedTime = cursor.getLong(cursor.getColumnIndex(Constants.LAST_MODIFIED));
					long size = cursor.getLong(cursor.getColumnIndex(Constants.SIZE));
					int available = cursor.getInt(cursor.getColumnIndex(Constants.isAvailable));
					
					fileInfo.setName(filename);
					fileInfo.setModificationTime(modifiedTime);
					fileInfo.setSize(size);
					fileInfo.setAvailable(available);
					
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

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			
			SparseBooleanArray idSelectedItem = list.getCheckedItemPositions();	
			
			
		}
}