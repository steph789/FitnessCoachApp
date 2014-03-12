package de.gerlich.fitnesscoachapp.database;

import java.util.Arrays;
import java.util.HashSet;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class UserProvider extends ContentProvider{
	
	private TableHelper database;
	
	private static final int USERS = 10;
	private static final int USER_ID = 20;

	private static final String AUTHORITY = "de.gerlich.fitnesscoachapp.database";
	
	private static final String BASE_PATH = "users";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
	      + "/" + BASE_PATH);
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
		      + "/users";
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
		      + "/user";
	
	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	  static {
	    sURIMatcher.addURI(AUTHORITY, BASE_PATH, USERS);
	    sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", USER_ID);
	  }
	  
	@Override
	public boolean onCreate() {
		database = new TableHelper(getContext());
		return false;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int uriType = sURIMatcher.match(uri);
	    SQLiteDatabase sqlDB = database.getWritableDatabase();
	    int rowsDeleted = 0;
	    switch (uriType) {
	    case USERS:
	      rowsDeleted = sqlDB.delete(TableUser.TABLE_USER, selection,
	          selectionArgs);
	      break;
	    case USER_ID:
	      String id = uri.getLastPathSegment();
	      if (TextUtils.isEmpty(selection)) {
	        rowsDeleted = sqlDB.delete(TableUser.TABLE_USER,
	            TableUser.COLUMN_ID + "=" + id, 
	            null);
	      } else {
	        rowsDeleted = sqlDB.delete(TableUser.TABLE_USER,
	            TableUser.COLUMN_ID + "=" + id 
	            + " and " + selection,
	            selectionArgs);
	      }
	      break;
	    default:
	      throw new IllegalArgumentException("Unknown URI: " + uri);
	    }
	    getContext().getContentResolver().notifyChange(uri, null);
	    return rowsDeleted;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sURIMatcher.match(uri);
	    SQLiteDatabase sqlDB = database.getWritableDatabase();
	    int rowsDeleted = 0;
	    long id = 0;
	    switch (uriType) {
	    case USERS:
	      id = sqlDB.insert(TableUser.TABLE_USER, null, values);
	      break;
	    default:
	      throw new IllegalArgumentException("Unknown URI: " + uri);
	    }
	    getContext().getContentResolver().notifyChange(uri, null);
	    return Uri.parse(BASE_PATH + "/" + id);
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

	    checkColumns(projection);

	    queryBuilder.setTables(TableUser.TABLE_USER);

	    int uriType = sURIMatcher.match(uri);
	    switch (uriType) {
	    case USERS:
	      break;
	    case USER_ID:
	      queryBuilder.appendWhere(TableUser.COLUMN_ID + "="
	          + uri.getLastPathSegment());
	      break;
	    default:
	      throw new IllegalArgumentException("Unknown URI: " + uri);
	    }

	    SQLiteDatabase db = database.getWritableDatabase();
	    Cursor cursor = queryBuilder.query(db, projection, selection,
	        selectionArgs, null, null, sortOrder);

	    cursor.setNotificationUri(getContext().getContentResolver(), uri);

	    return cursor;
		
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int uriType = sURIMatcher.match(uri);
	    SQLiteDatabase sqlDB = database.getWritableDatabase();
	    int rowsUpdated = 0;
	    switch (uriType) {
	    case USERS:
	      rowsUpdated = sqlDB.update(TableUser.TABLE_USER, 
	          values, 
	          selection,
	          selectionArgs);
	      break;
	    case USER_ID:
	      String id = uri.getLastPathSegment();
	      if (TextUtils.isEmpty(selection)) {
	        rowsUpdated = sqlDB.update(TableUser.TABLE_USER, 
	            values,
	            TableUser.COLUMN_ID + "=" + id, 
	            null);
	      } else {
	        rowsUpdated = sqlDB.update(TableUser.TABLE_USER, 
	            values,
	            TableUser.COLUMN_ID + "=" + id 
	            + " and " 
	            + selection,
	            selectionArgs);
	      }
	      break;
	    default:
	      throw new IllegalArgumentException("Unknown URI: " + uri);
	    }
	    getContext().getContentResolver().notifyChange(uri, null);
	    return rowsUpdated;
	}
	
	private void checkColumns(String[] projection) {
	    String[] available = { TableUser.COLUMN_ID, TableUser.COLUMN_USERNAME, TableUser.COLUMN_PASSWORD, TableUser.COLUMN_FIRSTNAME,
	    		TableUser.COLUMN_LASTNAME, TableUser.COLUMN_EMAIL, TableUser.COLUMN_YEARBIRTH,TableUser.COLUMN_SEX, 
	    		TableUser.COLUMN_HEIGHT, TableUser.COLUMN_RUN, TableUser.COLUMN_BIKE, TableUser.COLUMN_SWIM,
	    		TableUser.COLUMN_PUMP, TableUser.COLUMN_STATUS, TableUser.COLUMN_FITNESSLEVEL};
	    if (projection != null) {
	      HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
	      HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));

	      if (!availableColumns.containsAll(requestedColumns)) {
	        throw new IllegalArgumentException("Unknown columns in projection");
	      }
	    }
	  }

}
