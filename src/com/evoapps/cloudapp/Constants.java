/*
 * 
 */
package com.evoapps.cloudapp;

// TODO: Auto-generated Javadoc
/**
 * The Class Constants.
 */
public class Constants {

		/** The Constant REPO_PATH. */
		public static final String REPO_PATH = "/sdcard/repo";
		
		//database name
				/** The Constant DATABASE_NAME. */
		public static final String DATABASE_NAME="clouddataentry";
				//version of the database
				/** The Version. */
				public static int Version = 1;
				//the path to the repository in the android sdcard
				/** The Constant repoPath. */
				public static final String repoPath = "/mnt/sdcard/repo";
				// the size of the data in the memory that can be allowed
				/** The Constant MAXIMUM_DATA. */
				public static final int MAXIMUM_DATA = 2000;
				
				
				//information about the tables in the database
				
				//tables
				/** The Constant TABLE_NAME. */
				public static final String TABLE_NAME = "file_info";
				
				/** The Constant authority. */
				public static final String authority = "content://"+"com.cloudapps.filedatabase"+"/"+TABLE_NAME;
				//fields in table
				/** The Constant ID. */
				public static final String ID = "_id";
				
				/** The Constant NAME. */
				public static final String NAME = "name";
				
				/** The Constant SIZE. */
				public static final String SIZE = "size";
				
				/** The Constant CREATE_TIME. */
				public static final String CREATE_TIME = "createtime";
				
				/** The Constant LAST_MODIFIED. */
				public static final String LAST_MODIFIED = "lastmodified";
				
				/** The Constant isAvailable. */
				public static final String isAvailable = "isavailable";
				
				public static final String LAST_ACCESSED = "lastaccessed";
				
				public static final String ACCESS_FREQUENCY = "access_frequency";
				
				public static final String TYPE = "type";
				/** The Constant CreateFileInfoTable. */
				public static final String CreateFileInfoTable = "create table "+ TABLE_NAME + "( "+ ID 
						+" auto increment primary key, "+ NAME +" varchar, " +SIZE +" long, "
						+ CREATE_TIME+" long, "+ LAST_MODIFIED+" long, "+ LAST_ACCESSED+" long, "+
						ACCESS_FREQUENCY+ " integer, "+isAvailable +" integer, "+TYPE+" integer);"; 
}
