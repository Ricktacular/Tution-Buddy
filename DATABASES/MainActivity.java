package com.tutionbuddy.tutionbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String sst ="" ;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventsDatabaseHandler db = new EventsDatabaseHandler(this);

        textView = (TextView) findViewById(R.id.textView);



//        db.createTable();
//        db.dropTable();


        db.addEvent(new Event("12", "12/12/12", "first note", "chal"));
        db.addEvent(new Event("17", "4/545/45", "second note", "daal"));
        db.addEvent(new Event("19", "232/34/34", "third note", "chos"));
//        db.addEvent(new Event("16", "1752/412/102", "fourth note", "a girl me in"));
//        db.addEvent(new Event("14", "912/142/712", "fifth note", "chai"));
//        db.addEvent(new Event("10", "23/123/675", "sixth note", "nachos"));

//        sst = "TABLE CONTACTS DROPPED SUCESSFULLY";
//
//        db.deleteDate(new Date(4));

//
//        Log.d("Reading: ", "Reading all contacts..");
        List<Event> events = new ArrayList<>();



        for (Event event: events) {
            sst += event.getEventId()+ "  --->  " +
                    event.getDate()+ "  --->  " +
                    event.getTime()+ "  --->  " +
                    event.getNote()+ "  --->  " +
                    event.getReminder()+ "\n" ;
            System.out.println(sst);
        }

////        sst = String.valueOf(db.getEventsCount());


    }
}