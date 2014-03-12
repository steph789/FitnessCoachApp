package de.gerlich.fitnesscoachapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TableHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "fitness.db";
	private static final int DATABASE_VERSION = 1;

	public TableHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		TableUser.onCreate(database);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		TableUser.onUpgrade(database, oldVersion, newVersion);
	}

}
