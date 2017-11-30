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

        StudentsDatabaseHandler db = new StudentsDatabaseHandler(this);

        textView = (TextView) findViewById(R.id.textView);

//        db.createTable();
//        db.dropTable();
//        sst = "TABLE CONTACTS DROPPED SUCESSFULLY";
//        db.addStudent(new Student("dipto", "tolarbag", "dipto's routine", "1000", "dipto's note", "12"));
//        db.addStudent(new Student("dipto", "tolarbag", "dipto's routine", "1000", "dipto's note", "12"));
//        db.addStudent(new Student("dipto", "tolarbag", "dipto's routine", "1000", "dipto's note", "12"));
//        db.addStudent(new Student("dipto", "tolarbag", "dipto's routine", "1000", "dipto's note", "12"));
//        db.addStudent(new Student("dipto", "tolarbag", "dipto's routine", "1000", "dipto's note", "12"));

//        db.addStudent(new Student("mara", "tolarbag", "dipto's routine", "1000", "dipto's note", "12"));
//
//        db.addStudent(new Student("mara", "tolarbag", "dipto's routine", "345345", "dipto's note", "12"));
//        db.addStudent(new Student("mara", "tolarbag", "dipto's routine", "768654", "dipto's note", "17"));
//        db.addStudent(new Student("mara", "tolarbag", "dipto's routine", "89632", "dipto's note", "77"));
//        db.deleteContact(new Contact(5));

//        db.deleteStudent(new Student(6));
//
//        Log.d("Reading: ", "Reading all contacts..");
//        List<Student> students = db.getAllStudents();
//
//
//
//        for (Student student : students) {
//            sst += student.getStudentId()+ "--->" +
//                    student.getStudentName()+ "--->" +
//                    student.getAddress()+ "--->" +
//                    student.getRoutine()+ "--->" +
//                    student.getSalary()+ "--->" +
//                    student.getNote()+ "--->" +
//                    student.getDaysToGetSalary()+ "\n" ;
//        }
//
////        sst = db.getStudent(8).getSalary();
//        textView.setText(sst);

    }
}