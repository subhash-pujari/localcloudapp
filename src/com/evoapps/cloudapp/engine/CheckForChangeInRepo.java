/*
 * 
 */
package com.evoapps.cloudapp.engine;

import java.io.File;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.Iterator;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.evoapps.cloudapp.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class CheckForChangeInRepo.
 */
public class CheckForChangeInRepo extends AsyncTask<Void, Void, Void>{

	/** The tag. */
	private String TAG = "CheckForChangeInRepo";
	
	/** The cr. */
	private ContentResolver cr;
	
	/** The repo_directory. */
	File repo_directory;
	
	/** The files. */
	File[] files;
	
	/** The list file repo. */
	ArrayList <String> listFileRepo;
	
	/** The list file database. */
	ArrayList<String>listFileDatabase;
	
	/** The files to add. */
	ArrayList<String>filesToAdd;
	
	
	/**
	 * Instantiates a new check for change in repo.
	 *
	 * @param cr the cr
	 */
	public CheckForChangeInRepo(ContentResolver cr){
		this.cr = cr;
	}
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		Log.e(TAG, "do in backgroud called...");
		
		repo_directory = new File(Constants.REPO_PATH);
		files = repo_directory.listFiles();
		
		listFileRepo = new ArrayList<String>();
		
		for(File file: files){
			listFileRepo.add(file.getName());
			Log.e(TAG, "FilesInRepo>>"+file.getName());
		}
		
		listFileDatabase = new ArrayList<String>();
		
		//FileContentProvider fileProvider = new FileContentProvider();
		Cursor cursor= cr.query(Uri.parse(Constants.authority), null, null, null, null);
		if(cursor != null && cursor.getCount()>0){
			Log.e(TAG, "cursor.getCount>>"+cursor.getCount());
			cursor.moveToFirst();
			while(!cursor.isLast()){
				String filename = cursor.getString(cursor.getColumnIndex(Constants.NAME));
				listFileDatabase.add(filename);
				cursor.moveToNext();
				Log.e(TAG, "FileInDatabase>>"+filename);
			}
		}else{
			Log.e(TAG, "cursor is null");
		}
		
		//sort the lists
	//	Collections.sort(listFileRepo);
		//Collections.sort(listFileDatabase);
		
		//checkForNewFiles();
		/*String[] listFileRepoArray = listFileRepo.toArray(new String[]);
		String[] listFileDatabaseArray = listFileRepo.toArray(new String[1024]);
		
		Log.e(TAG, "listFileRepoArray.length>>"+listFileRepoArray.length);
		Log.e(TAG, "listFileDatabaseArray.length>>"+listFileDatabaseArray.length);*/
		//check for the new files
		/*for(String filename : listFileRepoArray){
			if(filename == null){
				continue;
			}
			boolean isNew = true;
			for(String filenameDatabase : listFileDatabaseArray){
				if(filenameDatabase == null){
					continue;
				}
				if(filenameDatabase.equals(filename)){
					isNew = false;
				}
				
			}
			
			if(isNew){
				filesToAdd.add(filename);
				Log.e(TAG, ">>addfiletorepo>>"+filename);
			}
		}*/
		
		
		filesToAdd = checkNewFiles(listFileRepo, listFileDatabase);
		
		
		//add new files to repo
		if(filesToAdd.size()>0){
			addToDatabase(filesToAdd);
		}

		
		return null;
	}
	
	/**
	 * Adds the to repo.
	 *
	 * @param filesToAdd the files to add
	 */
	void addToDatabase(ArrayList<String> filesToAdd){
		
		Iterator <String>iterator = filesToAdd.iterator();
		while(iterator.hasNext()){
			String filename = iterator.next();
			File file = new File(Constants.REPO_PATH +"/"+filename);
			long timeLastModified = file.lastModified();
			long size = file.length();
			
			String file_extension = (String)filename.subSequence(filename.indexOf(".")+1, filename.length());
			//String extension = file_extension[file_extension.length-1];
			Log.e(TAG, file_extension);
			int type = FileUtility.getType(file_extension);
			
			Log.e(TAG, " file>>"+filename+" time>>"+timeLastModified+" size>>"+size+" type>>"+type);
			
			ContentValues cv = new ContentValues();
			cv.put(Constants.NAME, filename);
			cv.put(Constants.LAST_MODIFIED, timeLastModified);
			cv.put(Constants.SIZE, size);
			cv.put(Constants.TYPE, type);
			cv.put(Constants.ACCESS_FREQUENCY, 0);
			cv.put(Constants.isAvailable, 1);
			cr.insert(Uri.parse(Constants.authority), cv);
			Log.e(TAG, "insert done");
		}
	}
	
	
	
	ArrayList<String> checkNewFiles(ArrayList<String> repo, ArrayList<String> database){
		
		Iterator iterator = repo.iterator();
		ArrayList <String> temp = new ArrayList<String>();
		
		while(iterator.hasNext()){
			String filename = (String)iterator.next();
			Log.e(TAG, "check for filename>>"+filename);
			Iterator<String> iterator2 = database.iterator();
			boolean isNew = true;
			String filenameDatabase = new String();
			Log.e(TAG, "database length>>"+database.size());
			while(iterator2.hasNext()){
				filenameDatabase = (String)iterator2.next();
				Log.e(TAG, "filename>>"+filename+"filenameDatabase>>"+filenameDatabase);
				if(filename.equals(filenameDatabase)){
					Log.e(TAG, "fileexist");
					isNew = false;
				}
			}
			if(database.size()>0){
				if(database.get(database.size()-1).equals(filename)){
					isNew = false;
				}
			}
			if(isNew){
				Log.e(TAG,"filename add>>"+filename);
				temp.add(filename);
			}
		}
		
		return temp;
		
	}
}
