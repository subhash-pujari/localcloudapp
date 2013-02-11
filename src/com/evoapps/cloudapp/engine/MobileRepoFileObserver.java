/*
 * 
 */
package com.evoapps.cloudapp.engine;

import android.os.FileObserver;

// TODO: Auto-generated Javadoc
/**
 * An asynchronous update interface for receiving notifications
 * about MobileRepoFile information as the MobileRepoFile is constructed.
 */
public class MobileRepoFileObserver extends FileObserver{

	/**
	 * This method is called when information about an MobileRepoFile
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param path the path
	 */
	public MobileRepoFileObserver(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.os.FileObserver#onEvent(int, java.lang.String)
	 */
	@Override
	public void onEvent(int event, String path) {
		// TODO Auto-generated method stub
		switch(event){
			
			case FileObserver.CREATE:
						break;
			case FileObserver.MODIFY:
						break;
			case FileObserver.DELETE:
						break;
			case FileObserver.ACCESS:
						break;
		}
	}

}
