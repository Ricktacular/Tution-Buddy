package com.tutionbuddy.tutionbuddy;

/**
 * Created by dipto on 11/30/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dipto on 11/29/17.
 */

public class DatesDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tutionBuddyDatabase";

    private static final String TABLE_DATES = "dates";
    private static final String KEY_DATE_ID = "date_id";
    private static final String KEY_STUDENT_ID = "student_id";
    private static final String KEY_DATE = "date";
    private static final String KEY_NOTE = "note";



    public DatesDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_DATES + "("
                + KEY_DATE_ID + " INTEGER PRIMARY KEY, "
                + KEY_STUDENT_ID + " INTEGER, "
                + KEY_DATE + " TEXT, " 
                + KEY_NOTE + " TEXT " +
        ")";
        
        db.execSQL(CREATE_CONTACTS_TABLE);
    }





    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATES);

        // Create tables again
        onCreate(db);
    }




    
    void addDate(Date date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_ID, date.getStudentId());
        values.put(KEY_DATE, date.getDate());
        values.put(KEY_NOTE, date.getNote());

        db.insert(TABLE_DATES, null, values);

        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }






    Date getDate(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DATES, new String[] { KEY_DATE_ID, KEY_STUDENT_ID,
                        KEY_DATE, KEY_NOTE}, KEY_DATE_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Date date = new Date(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)),
                cursor.getString(2),
                cursor.getString(3));
        return date;
    }








    
    public List<Date> getAllDates() {
        List<Date> dateList = new ArrayList<Date>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Date date = new Date();
                
                date.setDateId(Integer.parseInt(cursor.getString(0)));
                date.setStudentId(Integer.parseInt(cursor.getString(1)));
                date.setDate(cursor.getString(2));
                date.setNote(cursor.getString(3));

                dateList.add(date);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dateList;
    }








    public int updateDate(Date date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_ID, date.getStudentId());
        values.put(KEY_DATE, date.getDate());
        values.put(KEY_NOTE, date.getNote());

        return db.update(TABLE_DATES, values, KEY_DATE_ID + " = ?",
                new String[] { String.valueOf(date.getDateId()) });
    }









    public void deleteDate(Date date) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DATES, KEY_DATE_ID+ " = ?",
                new String[] { String.valueOf(date.getDateId()) });
        db.close();
    }



    public int getDatesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DATES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }




    /* THESE TWO METHODS BELOW ARE FOR TESTING ONLY
       IF TABLE_CONTACTS NEEDS TO BE DROPPED THEN dropTable() NEEDS TO BE CALLED
       IF TABLE_CONTACTS NEEDS TO BE CREATED THEN createTable() NEEDS TO BE CALLED
     */

    public void dropTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + TABLE_DATES);
        db.close();
    }

    public void createTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_DATES + "("
                + KEY_DATE_ID + " INTEGER PRIMARY KEY, "
                + KEY_STUDENT_ID + " INTEGER, "
                + KEY_DATE + " TEXT, "
                + KEY_NOTE + " TEXT " +
                ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }
}
