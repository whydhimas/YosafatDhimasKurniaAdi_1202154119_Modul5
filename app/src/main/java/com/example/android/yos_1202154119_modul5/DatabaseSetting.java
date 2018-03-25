package com.example.android.yos_1202154119_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Yosafat Dhimas on 25/03/2018.
 */

public class DatabaseSetting extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase db;

    public static final String DATABASE_NAME = "db_todo";
    public static final String TABLE_NAME = "table_todo";

    public static final String KEY_TODO = "todo";
    public static final String KEY_DESC = "description";
    public static final String KEY_PRIORITY = "priority";

    public DatabaseSetting(Context context) {
        super(context, DATABASE_NAME, null, 1);

        this.context = context;
        db = this.getWritableDatabase();
        db.execSQL("create table if not exists "+ TABLE_NAME +" " +
                   "(todo varchar(100) primary key, description varchar(100), priority varchar(10))");
    }

    //method untuk membuat database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+ TABLE_NAME +" " +
                "(todo varchar(100) primary key, description varchar(100), priority varchar(10))");
    }

    //method untuk menghapus table jika sudah ada
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //data dimasukkan sesuai nilainya ke dalam table dengan suatu nilai
    public boolean inputdata(Todo todo) {
        ContentValues values = new ContentValues();

        values.put(KEY_TODO, todo.getTodo());
        values.put(KEY_DESC, todo.getDesc());
        values.put(KEY_PRIORITY, todo.getPrior());

        long result = db.insert(TABLE_NAME, null, values);

        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    //method untuk hapus item data dari table
    public boolean deleteData(String todo) {
        return db.delete(TABLE_NAME, KEY_TODO +"=\""+todo+"\"", null)>0;
    }

    //method untuk membaca data dari DB
    public void readData(ArrayList<Todo> daftar){
        Cursor cursor = this.getReadableDatabase().rawQuery("select todo, description, " +
                                                  "priority from "+ TABLE_NAME, null);
        while (cursor.moveToNext()){
            daftar.add(new Todo(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }
}