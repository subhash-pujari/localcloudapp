/*
 * 
 */
package com.evoapps.cloudapp;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.evoapps.cloudapp.database.FileContentProvider;

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
	CheckForChangeInRepo(ContentResolver cr){
		this.cr = cr;
	}
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		Log.e(TAG, "do in backgoud called...");
		
		repo_directory = new File(Constants.REPO_PATH);
		files = repo_directory.listFiles();
		listFileRepo = new ArrayList<String>();
		
		for(File file: files){
			listFileRepo.add(file.getName());
			Log.e(TAG, "FilesInRepo>>"+file.getName());
		}
		
		listFileDatabase = new ArrayList<String>();
		
		//FileContentProvider fileProvider = new FileContentProvider();
		Cursor cursor= cr.query(Uri.parse(Constants.REPO_PATH), null, null, null, null);
		if(cursor != null){
			while(cursor.isLast()){
				String filename = cursor.getString(cursor.getColumnIndex(Constants.NAME));
				listFileRepo.add(filename);
				cursor.moveToNext();
				Log.e(TAG, "FileInDatabase>>"+filename);
			}
		}
		
		//sort the lists
		Collections.sort(listFileRepo);
		Collections.sort(listFileDatabase);
		
		Iterator<String> repoIterator = listFileRepo.iterator();
		//Iterator<String> databaseIterator = listFileDatabase.iterator();
		filesToAdd = new ArrayList<String>();
		
		while(repoIterator.hasNext()){
			String checkFilename = repoIterator.next();
			if(!listFileDatabase.contains(checkFilename)){
				Log.e(TAG, "new file add it to repo>>"+checkFilename);
				filesToAdd.add(checkFilename);
			}
		}
		
		if(filesToAdd.size()>0){
			addToRepo(filesToAdd);
		}

		
		return null;
	}
	
	/**
	 * Adds the to repo.
	 *
	 * @param filesToAdd the files to add
	 */
	void addToRepo(ArrayList<String> filesToAdd){
		
		Iterator <String>iterator = filesToAdd.iterator();
		while(iterator.hasNext()){
			String filename = (String)iterator.next();
			Log.e(TAG, "filesNewInRepo>>"+ filename);
		
			ContentValues value = new ContentValues();
			value.put(Constants.NAME, filename);
			Uri insertUri = cr.insert(Uri.parse(Constants.authority), value);
			if(insertUri != null){
				Log.e(TAG, "insertUri>>"+insertUri.toString());
			}
			
		}
	}
}
