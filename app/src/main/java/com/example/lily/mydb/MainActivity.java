package com.example.lily.mydb;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private SQLiteDatabase sqLiteDatabase;
    static final String TABLE_NAME="lilytable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonAdd= (Button) findViewById(R.id.btn_add);
        Button buttonDelete= (Button) findViewById(R.id.btn_delete);
        Button buttonChange= (Button) findViewById(R.id.btn_change);
        Button buttonQuery= (Button) findViewById(R.id.btn_query);
        buttonAdd.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonChange.setOnClickListener(this);
        buttonQuery.setOnClickListener(this);

        MyDBHelper myDBHelper=new MyDBHelper(MainActivity.this);
        sqLiteDatabase = myDBHelper.getWritableDatabase();



    }

    private void add(){
        ContentValues contentValues=new ContentValues();
        contentValues.put("name","lily");
        contentValues.put("age",2);
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    private void delete(){
        sqLiteDatabase.delete("lilytable", "name like ?", new String[]{"lily"});
    }

    private void update(){
        ContentValues contentValues=new ContentValues();
        contentValues.put("age", 26);
        sqLiteDatabase.update(TABLE_NAME,contentValues,"name like ?",new String[]{"lily"});

    }

    private void query(){
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME, new String[]{"name", "age"}, " name like ?", new String[]{"%lily%"}, null, null, null);
        int cursorCount=cursor.getCount();
        Log.v("PLU", "cursor count is " + cursorCount);
       // cursor.moveToFirst();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            long age = cursor.getLong(cursor.getColumnIndexOrThrow("age"));
            Log.v("PLU", "--query result name : " + name + " , age : " + age);
        }
        cursor.close();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                add();
                break;
            case R.id.btn_delete:
                delete();
                break;
            case R.id.btn_change:
                update();
                break;
            case R.id.btn_query:
                query();
                break;
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqLiteDatabase.close();
    }
}
