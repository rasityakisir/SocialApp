package com.example.independentstudy.socialapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.media.Image;
import android.provider.MediaStore;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dataDB.db";
     public static final String TABLE_DATA = "data";
     public static final String COLUMN_ID = "_id";
     public static final String COLUMN_TEXT = "text";
     public static final String COLUMN_IMAGE = null;
     public static final String COLUMN_VIDEO = null;

    //We need to pass database information along to superclass
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_DATA + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TEXT + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        onCreate(db);
    }

    //Add a new row to the database
    public void addata(Data data){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEXT, data.get_text());
        //values.put(COLUMN_IMAGE,data.get_picture()); COLUMN_IMAGE + "IMAGE" + COLUMN_VIDEO + "TEXT" +
        //values.put(COLUMN_VIDEO, data.get_video());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_DATA, null, values);
        db.close();
    }

    //Delete a product from the database
    public void deleteProduct(String text){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_DATA + " WHERE " + COLUMN_TEXT + "=\"" + text + "\";");
    }

    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_DATA + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("text")) != null) {
                dbString += c.getString(c.getColumnIndex("text"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }




}
