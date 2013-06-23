package com.example.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 
 * Defines constants for accessing the content provider defined in DataProvider.
 * A content provider contract assists in accessing the provider's available
 * content URIs, column names, MIME types, and so forth, without having to know
 * the actual values.
 */
public final class DataProviderContract implements BaseColumns {

	private DataProviderContract() {
	}

	// The content provider database name
	public static final String DATABASE_NAME = "demo.db"; //need modify

	// The starting version of the database
	public static final int DATABASE_VERSION = 1;

	// Constants for building SQLite tables during initialization
	private static final String TEXT_TYPE = "TEXT";
	private static final String PRIMARY_KEY_TYPE = "INTEGER PRIMARY KEY ";
	private static final String INTEGER_TYPE = "INTEGER";

	private static final String AUTOINCREMENT = "AUTOINCREMENT";

	// The URI scheme used for content URIs
	public static final String SCHEME = "content";

	// The provider's authority
	public static final String AUTHORITY = "com.schedule";  //need modify

	/**
	 * The DataProvider content URI
	 */
	public static final Uri CONTENT_URI = Uri.parse(SCHEME + "://" + AUTHORITY);

	/**
	 * The MIME type for a content URI that would return multiple rows
	 * <P>
	 * Type: TEXT
	 * </P>
	 */
	public static final String MIME_TYPE_ROWS = "vnd.android.cursor.dir/com.schedule"; //need modify

	/**
	 * The MIME type for a content URI that would return a single row
	 * <P>
	 * Type: TEXT
	 * </P>
	 * 
	 */
	public static final String MIME_TYPE_SINGLE_ROW = "vnd.android.cursor.item/com.schedule"; //need modify

	/**
	 * Picture table primary key column name
	 */
	public static final String ROW_ID = BaseColumns._ID;

	public static abstract class TargetEntry implements BaseColumns {

		/**
		 * Target table name
		 */
		public static final String TARGET_TABLE_NAME = "TargetData"; //need modify
		/**
		 * Target table content URI
		 */
		public static final Uri TARGET_TABLE_CONTENTURI = Uri.withAppendedPath(CONTENT_URI, TARGET_TABLE_NAME);

		/**
		 * Target table name column name
		 */
		public static final String COLUMN_NAME = "Name";

		/**
		 * Target table age column name
		 */
		public static final String COLUMN_AGE = "Age";

		/**
		 * Target table phone column name
		 */
		public static final String COLUMN_PHONE = "Phone";
		
/**
 * demo of createTableSql
 */
//	           db.execSQL("CREATE TABLE " + NotePad.Notes.TABLE_NAME + " ("
//	                   + NotePad.Notes._ID + " INTEGER PRIMARY KEY,"
//	                   + NotePad.Notes.COLUMN_NAME_TITLE + " TEXT,"
//	                   + NotePad.Notes.COLUMN_NAME_NOTE + " TEXT,"
//	                   + NotePad.Notes.COLUMN_NAME_CREATE_DATE + " INTEGER,"
//	                   + NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE + " INTEGER"
//	                   + ");");
//	       }

		// Defines an SQLite statement that builds the Target table
		public static final String CREATE_TARGET_TABLE_SQL = "CREATE TABLE "  + TARGET_TABLE_NAME +"(" 
				+ ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ COLUMN_NAME + " TEXT,"
				+ COLUMN_AGE  + " INTEGER,"
				+ COLUMN_PHONE + " TEXT"
				+ ")";

		// target(_id,name,age,phone)
	}

	public static abstract class MessageEntry implements BaseColumns {

		/**
		 * Message table name
		 * (_id,targetId,content,created,modified,executed,is_executed)
		 */
		public static final String MESSAGE_TABLE_NAME = "MessageData";

		/**
		 * Content URI for Message table
		 */
		public static final Uri MESSAGE_TABLE_CONTENTURI = Uri.withAppendedPath(CONTENT_URI, MESSAGE_TABLE_NAME);

		/**
		 * Message table TargetId column name
		 */
		public static final String COLUMN_TARGET_ID = "target_id";

		/**
		 * Message table Content column name
		 */
		public static final String COLUMN_CONTENT = "content";

		/**
		 * Column name for the creation timestamp
		 * <P>
		 * Type: INTEGER (long from System.curentTimeMillis())
		 * </P>
		 */
		public static final String COLUMN_CREATE_DATE = "created";

		/**
		 * Column name for the modification timestamp
		 * <P>
		 * Type: INTEGER (long from System.curentTimeMillis())
		 * </P>
		 */
		public static final String COLUMN_MODIFICATION_DATE = "modified";

		/**
		 * Message table executed column name
		 */
		public static final String COLUMN_EXECUTION_DATE = "executed";

		/**
		 * Message table is_executed column name
		 */
		public static final String COLUMN_IS_EXECUTED = "is_executed";
		



		// Defines an SQLite statement that builds the URL modification date
		// table
		public static final String CREATE_MESSAGE_TABLE_SQL = "CREATE TABLE "  + MESSAGE_TABLE_NAME + " (" 
				+ _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ COLUMN_TARGET_ID + " INTEGER REFERENCES " + TargetEntry.TARGET_TABLE_NAME + "(" + _ID + ")," 
				+ COLUMN_CONTENT + " TEXT,"
				+ COLUMN_CREATE_DATE + " INTEGER,"
				+ COLUMN_MODIFICATION_DATE+" INTEGER,"
				+ COLUMN_EXECUTION_DATE +" INTEGER,"
				+ COLUMN_IS_EXECUTED+ " INTEGER" 
				+ ")";

	}
}
