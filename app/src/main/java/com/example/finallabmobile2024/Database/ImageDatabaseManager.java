package com.example.finallabmobile2024.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageDatabaseManager {
    private static final  String DATABASE_TABLE=DatabaseContract.TABLE_NAME;
    private  DatabaseHelper dbHelper;
    private static SQLiteDatabase database;
    private static volatile ImageDatabaseManager INSTANCE;
    private Context context;

    public ImageDatabaseManager(Context context){
        this.context=context;
        dbHelper=new DatabaseHelper(context);
    }

    public static ImageDatabaseManager getInstance(Context context){
        if (INSTANCE==null){
            synchronized (SQLiteOpenHelper.class){
                if(INSTANCE==null){
                    INSTANCE=new ImageDatabaseManager(context);
                }
            }
        }return INSTANCE;
    }

    public void open() throws SQLException{
        database=dbHelper.getWritableDatabase();
    }
    public long insert(ContentValues values){
        return database.insert(DATABASE_TABLE,null,values);
    }


    public void insertImage(String title, String description, Uri imageUri){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DatabaseContract.ImagesColumns.TITLE,title);
        values.put(DatabaseContract.ImagesColumns.DESCRIPTION,description);
        try{
            InputStream inputStream=context.getContentResolver().openInputStream(imageUri);
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            byte[] imageBytes=outputStream.toByteArray();
            values.put(DatabaseContract.ImagesColumns.IMAGE,imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.insert(DatabaseHelper.TABLE_NAME,null,values);
        db.close();
    }
}
