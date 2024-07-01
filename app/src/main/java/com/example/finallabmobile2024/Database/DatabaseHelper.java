package com.example.finallabmobile2024.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Images.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Images";

    private static final String SQL_CREATE_TABLE_IMAGES=String.format(
            "CREATE TABLE %s ("+
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT NOT NULL, "+
                    "%s TEXT NOT NULL, "+
                    "%s TEXT NOT NULL)",
            DatabaseContract.TABLE_NAME,
            DatabaseContract.ImagesColumns._ID,
            DatabaseContract.ImagesColumns.TITLE,
            DatabaseContract.ImagesColumns.DESCRIPTION,
            DatabaseContract.ImagesColumns.IMAGE
    );

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_IMAGES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public Cursor getData(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor= DB.rawQuery("Select * from Images",null);
        return cursor;
    }
}
