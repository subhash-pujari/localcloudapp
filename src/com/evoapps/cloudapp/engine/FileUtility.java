package com.evoapps.cloudapp.engine;

import java.util.ArrayList;

import android.text.format.Time;
import android.util.Log;

public class FileUtility {

	
		private static final String TAG = "FileUtility";
		ArrayList<String> getListOfFilesToRemove(){
			return null;
		}
		
		public static int getType(String extension){
			
			if(extension.equals("jpeg")){
				return 0;
			}else if(extension.equals("jpg")){
				return 0;
			}else if(extension.equals("mp3")){
				return 1;
			}else if(extension.equals("mp4")){
				return 2;
			}
			
			return 0;
		}
		
		public static String getSize(long size){
			
			double sizeMB = size/(10.0*10.0*10.0*10.0*10.0*10.0);
			String sizeMBString = String.valueOf(sizeMB);
			sizeMBString = sizeMBString + " MB";
			
			return sizeMBString;
		}
		
		public static String getDate(long timeInMillis){
			Time time =new Time();
			time.set(timeInMillis);
			return time.format3339(true);
			
		}
	
}
