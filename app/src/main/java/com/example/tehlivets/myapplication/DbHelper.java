package com.example.tehlivets.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tehlivets on 29/06/16.
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists recipe (" +
                "id integer primary key autoincrement," +
                "recipe_name text unique," +
                "photo_path text" +
                ");");
        db.execSQL("create table if not exists product (" +
                "id integer primary key autoincrement," +
                "product_name text unique" +
                ");");
        db.execSQL("create table if not exists recipe_products (" +
                "rec_id integer," +
                "product_id integer," +
                "count integer," +
                "foreign key (product_id) references products(id)," +
                "unique(rec_id,product_id)" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
