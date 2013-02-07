/*
 * 
 */
package com.evoapps.cloudapp.database;

import com.evoapps.cloudapp.Constants;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class FileContentProvider.
 */
public class FileContentProvider extends ContentProvider{

	/** The Constant TAG. */
	private static final String TAG = "FileContentProvider";
	
	/** The filedb. */
	FileSqliteDatabase filedb;
	
	/** The sqldb. */
	SQLiteDatabase sqldb;
	
	/** The file table matcher. */
	UriMatcher fileTableMatcher;
	
	/* (non-Javadoc)
	 * @see android.content.ContentProvider#delete(android.net.Uri, java.lang.String, java.lang.String[])
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#getType(android.net.Uri)
	 */
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#insert(android.net.Uri, android.content.ContentValues)
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		sqldb = filedb.getWritableDatabase();
		sqldb.insert(Constants.TABLE_NAME, null, values);
		return null;
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#onCreate()
	 */
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		filedb = new FileSqliteDatabase(getContext());
		fileTableMatcher = new UriMatcher(TRIM_MEMORY_MODERATE);
		Log.e(TAG, "onCreateCalled");
		//fileTableMatcher.
		return true;
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		sqldb = filedb.getReadableDatabase();
		
		if(sqldb != null){
			return sqldb.query(Constants.TABLE_NAME, null, null, null,null, null, null);
		}else{
			Log.e(TAG, "sqldb null");
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[])
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * The Class FileSqliteDatabase.
	 */
	class FileSqliteDatabase extends SQLiteOpenHelper{

		/**
		 * Instantiates a new file sqlite database.
		 *
		 * @param context the context
		 */
		public FileSqliteDatabase(Context context) {
			super(context, Constants.DATABASE_NAME, null, Constants.Version);
			Log.e(TAG, "database constructor");
			// TODO Auto-generated constructor stub
		}

		/* (non-Javadoc)
		 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			Log.e(TAG, "database create");
			db.execSQL(Constants.CreateFileInfoTable);
		
		}

		/* (non-Javadoc)
		 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
