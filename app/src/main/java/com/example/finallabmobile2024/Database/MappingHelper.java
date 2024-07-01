package com.example.finallabmobile2024.Database;

import android.database.Cursor;

import com.example.finallabmobile2024.Models.Images;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<Images> mapCursorToArrayList(Cursor cursor){
        ArrayList<Images> images=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ImagesColumns._ID));
            String title=
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ImagesColumns.TITLE));
            String descriprion=
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ImagesColumns.DESCRIPTION));
            String image=
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ImagesColumns.IMAGE));
            images.add(new Images(id,title,descriprion,image));
        }
        return images;
    }
}
