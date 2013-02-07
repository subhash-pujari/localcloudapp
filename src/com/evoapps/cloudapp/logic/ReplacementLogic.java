package com.evoapps.cloudapp.logic;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.evoapps.cloudapp.Constants;
import com.evoapps.cloudapp.datastructure.FileInfo;
import com.evoapps.cloudapp.datastructure.FileInfoList;

public class ReplacementLogic {
	
		ContentResolver cr;
		FileInfoList list = new FileInfoList();
	
		public ReplacementLogic(ContentResolver cr) {
			// TODO Auto-generated constructor stub
		
			this.cr = cr;
		}
		
		void getFilesInfo(){
			Cursor cursor = cr.query(Uri.parse(Constants.authority), null, null, null, null);
		
			cursor.moveToFirst();
			while(cursor.isLast()){
				cursor.getInt(cursor.getColumnIndex(Constants.ID));
				cursor.getInt(cursor.getColumnIndex(Constants.NAME));
				cursor.getInt(cursor.getColumnIndex(Constants.CREATE_TIME));
				cursor.getInt(cursor.getColumnIndex(Constants.LAST_MODIFIED));
				cursor.getInt(cursor.getColumnIndex(Constants.SIZE));
			}
		}
		
		ArrayList<FileInfo> sortByLRU(){
			
			return null;
		}
		
		ArrayList<FileInfo> sortByMU(){
			
			return null;
		}
		
		ArrayList<FileInfo> sortBySIZE(){
			
			return null;
		}

}
