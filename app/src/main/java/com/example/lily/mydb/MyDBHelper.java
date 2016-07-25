package com.example.lily.mydb;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2016/7/25.
 */
public class MyDBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION=1;
    public static final String DB_NAME="lily.db";
    public static final String SQL_CREATE_TABLE="create table lilytable(id integer primary key autoincrement,name text,age integer,grade integer)";
    public static final String SQL_DELETE_TABLE="drop table lilytable";



    public MyDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.delete("lilytable","where name like ?",new String[]{"lily"});
        onCreate(db);

    }
}
