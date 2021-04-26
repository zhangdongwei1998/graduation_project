package com.example.myapp.bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Author zhangdongwei
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(Context context) {
        //创建数据库
        super(context, "medicine.db", null, 1);
        // TODO Auto-generated constructor stub
        System.out.println("MyOpenHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //创建表
        db.execSQL("create table person(_id integer primary key autoincrement, name char(40), category char(40), description varchar(400) ,picId char(40))");
        db.execSQL("create table person1(_id integer primary key autoincrement, name char(40), category char(40), description varchar(400) ,picId char(40))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}