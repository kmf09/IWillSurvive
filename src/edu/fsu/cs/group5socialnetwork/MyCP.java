package edu.fsu.cs.group5socialnetwork;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyCP extends ContentProvider {

    public final static String DBNAME = "SurviveDB";
    public final static String TABLE_USERS = "users";
    public final static String COLUMN_FIRSTNAME = "firstname";
    public final static String COLUMN_LASTNAME = "lastname";
    public final static String COLUMN_USERNAME = "username";
    public final static String COLUMN_PASSWORD = "password";
    public final static String COLUMN_PHONENUM = "phonenum";
    public final static String COLUMN_EMAILADDR = "emailaddr";

    public static final String AUTHORITY = "co.NoCoffee.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://co.NoCoffee.provider/" + TABLE_USERS);

    private static UriMatcher sUriMatcher;

    @SuppressWarnings("unused")
    private MainDatabaseHelper mOpenHelper;

    private static final String SQL_CREATE_MAIN = "CREATE TABLE " +
            TABLE_USERS +                       // Table's name
            "(" +                           // The columns in the table
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

    @Override
    public boolean onCreate() {

        mOpenHelper = new MainDatabaseHelper(getContext());

        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = mOpenHelper.getWritableDatabase().insert(TABLE_USERS, null, values);

        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        return mOpenHelper.getWritableDatabase().update(TABLE_USERS, values, selection, selectionArgs);

    }

    @Override
    public int delete(Uri uri, String whereClause, String[] whereArgs) {

        return mOpenHelper.getWritableDatabase().delete(TABLE_USERS, whereClause, whereArgs);

    }

    @Override
    public Cursor query(Uri table, String[] columns, String selection, String[] args, String orderBy) {

        return mOpenHelper.getReadableDatabase().query(TABLE_USERS, columns, selection, args, null, null, orderBy);
    }

    @Override
    public String getType(Uri arg0) {
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
