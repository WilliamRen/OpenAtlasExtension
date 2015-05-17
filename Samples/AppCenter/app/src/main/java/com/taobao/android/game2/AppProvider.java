package com.taobao.android.game2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.net.Uri.Builder;

public class AppProvider extends ContentProvider {
    private SQLiteDatabase     sqlDB;
    private DatabaseHelper    dbHelper;
    private static final String  DATABASE_NAME = "Users.db";
    private static final int  DATABASE_VERSION= 1;
    private static final String TABLE_NAME= "User";
    private static final String TAG = "MyContentProvider";
    public  static  Uri AppProviderURI=Uri.parse("content://appcenter");
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //创建用于存储数据的表
        db.execSQL("Create table " + TABLE_NAME + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, USER_NAME TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    @Override
    public int delete(Uri uri, String s, String[] as) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentvalues) {
        sqlDB = dbHelper.getWritableDatabase();
        long rowId = sqlDB.insert(TABLE_NAME, "", contentvalues);
        if (rowId > 0) {
        	Builder builder=new Builder();
        	
            Uri rowUri = ContentUris.appendId(builder, rowId).build();
            getContext().getContentResolver().notifyChange(rowUri, null);
            return rowUri;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext());
        return (dbHelper == null) ? false : true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        qb.setTables(TABLE_NAME);
        Cursor c = qb.query(db, projection, selection, null, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int update(Uri uri, ContentValues contentvalues, String s, String[] as) {
        return 0;
    }
}