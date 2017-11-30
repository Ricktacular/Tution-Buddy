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

public class EventsDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tutionBuddyDatabase";

    private static final String TABLE_EVENTS = "events";
    private static final String KEY_EVENT_ID = "event_id";
    private static final String KEY_TIME = "time";
    private static final String KEY_NOTE = "note";
    private static final String KEY_DATE = "date";
    private static final String KEY_REMINDER = "reminder";



    public EventsDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + KEY_EVENT_ID + " INTEGER PRIMARY KEY, "
                + KEY_DATE + " TEXT, "
                + KEY_TIME + " TEXT, "
                + KEY_NOTE + " TEXT, "
                + KEY_REMINDER + " TEXT " +
        ")";

        db.execSQL(CREATE_EVENTS_TABLE);
    }





    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);

        // Create tables again
        onCreate(db);
    }





    void addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, event.getDate());
        values.put(KEY_TIME, event.getTime());
        values.put(KEY_NOTE, event.getNote());
        values.put(KEY_REMINDER, event.getReminder());

        db.insert(TABLE_EVENTS, null, values);

        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }






    Event getEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EVENTS, new String[] { KEY_EVENT_ID, KEY_DATE,
                        KEY_TIME, KEY_NOTE, KEY_REMINDER}, KEY_EVENT_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Event event = new Event(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
        return event;
    }









    public List<Event> getAllEvents() {
        List<Event> eventList = new ArrayList<Event>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();

                event.setEventId(Integer.parseInt(cursor.getString(0)));
                event.setDate(cursor.getString(1));
                event.setTime(cursor.getString(2));
                event.setNote(cursor.getString(3));
                event.setReminder(cursor.getString(4));

                eventList.add(event);
            } while (cursor.moveToNext());
        }

        // return contact list
        return eventList;
    }








    public int updateEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, event.getDate());
        values.put(KEY_TIME, event.getTime());
        values.put(KEY_NOTE, event.getNote());
        values.put(KEY_REMINDER, event.getReminder());

        return db.update(TABLE_EVENTS, values, KEY_EVENT_ID + " = ?",
                new String[] { String.valueOf(event.getEventId()) });
    }









    public void deleteEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENTS, KEY_EVENT_ID+ " = ?",
                new String[] { String.valueOf(event.getEventId()) });
        db.close();
    }



    public int getEventsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_EVENTS;
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
        db.execSQL("DROP TABLE " + TABLE_EVENTS);
        db.close();
    }

    public void createTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + KEY_EVENT_ID + " INTEGER PRIMARY KEY, "
                + KEY_DATE + " TEXT, "
                + KEY_TIME + " TEXT, "
                + KEY_NOTE + " TEXT, "
                + KEY_REMINDER + " TEXT " +
                ")";

        db.execSQL(CREATE_EVENTS_TABLE);
    }
}
