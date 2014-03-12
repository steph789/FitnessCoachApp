package de.gerlich.fitnesscoachapp.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TableUser {
	
	public static final String TABLE_USER = "user";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_FIRSTNAME = "firstname";
	public static final String COLUMN_LASTNAME = "lastname";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_YEARBIRTH = "yearbirth";
	public static final String COLUMN_SEX = "sex";
	public static final String COLUMN_HEIGHT = "height";
	public static final String COLUMN_RUN = "run";
	public static final String COLUMN_BIKE = "bike";
	public static final String COLUMN_SWIM = "swim";
	public static final String COLUMN_PUMP = "pump";
	public static final String COLUMN_STATUS = "status";
	public static final String COLUMN_FITNESSLEVEL = "fitnesslevel";
	
	private static final String DATABASE_CREATE = "create table " 
		      + TABLE_USER
		      + "(" 
		      + COLUMN_ID + " integer primary key autoincrement, " 
		      + COLUMN_USERNAME + " text not null, " 
		      + COLUMN_PASSWORD + " text not null," 
		      + COLUMN_FIRSTNAME + " text not null, "
		      + COLUMN_LASTNAME + " text not null, "
		      + COLUMN_EMAIL + " text not null, "
		      + COLUMN_YEARBIRTH + " integer, "
		      + COLUMN_SEX + " integer, "
		      + COLUMN_HEIGHT + " real, "
		      + COLUMN_RUN + " integer, "
		      + COLUMN_BIKE + " integer, "
		      + COLUMN_SWIM + " integer, "
		      + COLUMN_PUMP + " integer, "
		      + COLUMN_STATUS + " text not null, "
		      + COLUMN_FITNESSLEVEL + " integer"
		      + ");";
	
	
	
	
	public static void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	      int newVersion) {
	    Log.w(TableUser.class.getName(), "Upgrading database from version "
	        + oldVersion + " to " + newVersion
	        + ", which will destroy all old data");
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
	    onCreate(database);
	  }

}
