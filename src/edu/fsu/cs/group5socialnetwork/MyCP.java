package edu.fsu.cs.group5socialnetwork;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyCP extends ContentProvider {
    public final static String DBNAME = "SurviveDB";           /* Name of database                                         */
    public final static String TABLE_USERS = "users";          /* Name of database table                                   */
    public final static String COLUMN_FIRSTNAME = "firstname"; /* Column in database to represent the user's first name    */
    public final static String COLUMN_LASTNAME = "lastname";   /* Column in database to represent the user's last name     */
    public final static String COLUMN_USERNAME = "username";   /* Column in database to represent the user's user name     */
    public final static String COLUMN_PASSWORD = "password";   /* Column in database to represent the user's password      */
    public final static String COLUMN_PHONENUM = "phonenum";   /* Column in database to represent the user's phone number  */
    public final static String COLUMN_EMAILADDR = "emailaddr"; /* Column in database to represent the user's email address */
    public final static Uri CONTENT_URI = Uri.parse("content://co.NoCoffee.provider/" + TABLE_USERS);
    private MainDatabaseHelper mOpenHelper; 				   /* Handler on the database helper object                    */

    /* SQL statement to create the user table */ 
    private static final String SQL_CREATE_MAIN = "CREATE TABLE " +
            TABLE_USERS +                       
            "(" +                           
            /* Primary key for a distinctive table & have a unique number for all rows */
            " _ID INTEGER PRIMARY KEY, " +		
            COLUMN_FIRSTNAME +
            " TEXT," +
            COLUMN_LASTNAME +
            " TEXT," +
            COLUMN_USERNAME +
            " TEXT," +
            COLUMN_PASSWORD +
            " TEXT," +
            COLUMN_PHONENUM +
            " TEXT," +
            COLUMN_EMAILADDR +
            " TEXT)";

    /* This method is called when the activity first runs */
    /* It is quick to return */
    @Override public boolean onCreate() {
    	/* Object reference to MainDatabaseHelper */
    	/* MainDatabaseHelper creates and manages the data */ 
        mOpenHelper = new MainDatabaseHelper(getContext());
        /* getContext() gets the context that the content provider runs in */
        return true;
    }

    /* The provider's insert method must be implemented */
    @Override public Uri insert(Uri uri, ContentValues values) {
    	/* The writable database is retrieved */
    	/* If it doesn't already exist then it will be created */
    	/* Use .insert to insert the table and the table values */
        long id = mOpenHelper.getWritableDatabase().insert(TABLE_USERS, null, values);

        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().update(TABLE_USERS, values, selection, selectionArgs);
    }

    @Override public int delete(Uri uri, String whereClause, String[] whereArgs) {
        return mOpenHelper.getWritableDatabase().delete(TABLE_USERS, whereClause, whereArgs);
    }

    @Override public Cursor query(Uri table, String[] columns, String selection, String[] args, String orderBy) {
        return mOpenHelper.getReadableDatabase().query(TABLE_USERS, columns, selection, args, null, null, orderBy);
    }

    @Override public String getType(Uri arg0) {
        return null;
    }

    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {

        MainDatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(SQL_CREATE_MAIN);
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    }
}
