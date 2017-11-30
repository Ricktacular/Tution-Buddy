package com.tutionbuddy.tutionbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String sst ="" ;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContactsDatabaseHandler db = new ContactsDatabaseHandler(this);

        textView = (TextView) findViewById(R.id.textView);

//        db.createTable();
//        db.dropTable();
//        sst = "TABLE CONTACTS DROPPED SUCESSFULLY";
//        db.deleteContact(new Contact(5));
//        db.updateContact(new Contact(1, 9396, "01775016325"));


//        Log.d("Reading: ", "Reading all contacts..");
//        List<Contact> contacts = db.getAllContacts();
//
//
//
//        for (Contact cn : contacts) {
//            sst += cn.getContactId() + " ----------> " + cn.getStudentId() + " ---------> " + cn.getContactNumber()+ "\n";
//        }

//        sst = db.getContact(3).getContactNumber();
//        textView.setText(sst);

    }
}