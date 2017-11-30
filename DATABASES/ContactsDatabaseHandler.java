package com.tutionbuddy.tutionbuddy;

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

public class ContactsDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tutionBuddyDatabase";

    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_CONTACT_ID = "contact_id";
    private static final String KEY_STUDENT_ID = "student_id";
    private static final String KEY_CONTACT_NUMBER = "contact_number";



    public ContactsDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_CONTACT_ID + " INTEGER PRIMARY KEY,"
                + KEY_STUDENT_ID + " INTEGER,"
                + KEY_CONTACT_NUMBER + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }





    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }





    void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_ID, contact.getStudentId());
        values.put(KEY_CONTACT_NUMBER, contact.getContactNumber());

        db.insert(TABLE_CONTACTS, null, values);

        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }






    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_CONTACT_ID, KEY_STUDENT_ID,
                        KEY_CONTACT_NUMBER}, KEY_CONTACT_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), cursor.getString(2));
        // return contact
        return contact;
    }








    // getting all the contacts in the contacts table
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setContactId(Integer.parseInt(cursor.getString(0)));
                contact.setStudentId(Integer.parseInt(cursor.getString(1)));
                contact.setContactNumber(cursor.getString(2));

                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }







    // update a single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_ID, contact.getStudentId());
        values.put(KEY_CONTACT_NUMBER, contact.getContactNumber());

        return db.update(TABLE_CONTACTS, values, KEY_CONTACT_ID + " = ?",
                new String[] { String.valueOf(contact.getContactId()) });
    }








    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_CONTACT_ID+ " = ?",
                new String[] { String.valueOf(contact.getContactId()) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
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
        db.execSQL("DROP TABLE " + TABLE_CONTACTS);
        db.close();
    }

    public void createTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_CONTACT_ID + " INTEGER PRIMARY KEY,"
                + KEY_STUDENT_ID + " INTEGER,"
                + KEY_CONTACT_NUMBER + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
}
