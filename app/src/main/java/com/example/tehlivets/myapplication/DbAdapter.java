package com.example.tehlivets.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbAdapter {

    private SQLiteDatabase db;
    private Context context;
    private DbHelper dbHelper;

    private String DB_NAME;
    private static final int DB_VERSION = 1;

    public DbAdapter(Context context, String dbName) {
        this.context = context;
        this.DB_NAME = dbName;
    }

    public DbAdapter open() {
        dbHelper = new DbHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            db = dbHelper.getReadableDatabase();
        }
        return this;
    }

    public void execSQL(String query) {
        db.execSQL(query);
    }

    public void close() {
        dbHelper.close();
    }

    public long insert(String table, ContentValues cv) {
        return db.insert(table, null, cv);
    }

    public void update(String table, ContentValues cv, String where){
        db.update(table,cv,where,null);
    }

    public Cursor getData(String table, String[] columns) {
        return db.query(table, columns, null, null, null, null, null);
    }

    public Cursor rawQuery(String query) {
        return db.rawQuery(query, null);
    }

    public void deleteDatabase(){
        context.deleteDatabase(DB_NAME);
    }
}
