package com.example.finallabmobile2024.Database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_NAME="Images";
    public static final class ImagesColumns implements BaseColumns{
        public static String TITLE="Title";
        public static String DESCRIPTION="Description";
        public static String IMAGE="Image";

    }
}
