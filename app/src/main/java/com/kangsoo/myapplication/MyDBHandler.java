package com.kangsoo.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bsnc on 2015-04-01.
 */
public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "products.db";
    public static final String TABLE_NAME = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCT_NAME = "productname";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCT_NAME + " TEXT " +
                ");";
        db.execSQL(query);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addProduct(Products p){

        ContentValues values = new ContentValues();

        values.put(COLUMN_PRODUCT_NAME, p.get_productName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values );

        db.close();
    }

    public void deleteProduct(String productname){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_PRODUCT_NAME + " = \"" + productname + "\";");

    }

    public String databaseToString(){

        String dbString= "";

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1;";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()){

            if(c.getString(c.getColumnIndex("productname")) != null){
                dbString +=c.getString(c.getColumnIndex("productname"));
                dbString += "\n";
            }
            //cursor move next
            c.moveToNext();
        }

        db.close();
        return dbString;
    }
}
