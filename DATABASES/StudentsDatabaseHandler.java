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

public class StudentsDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tutionBuddyDatabase";

    private static final String TABLE_STUDENTS = "students";
    
    private static final String KEY_STUDENT_ID = "student_id";
    private static final String KEY_STUDENT_NAME = "student_name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_ROUTINES = "routines";
    private static final String KEY_SALARY = "salary";
    private static final String KEY_NOTE = "note";
    private static final String KEY_DAYS_TO_GET_SALARY = "days_to_get_salary";
    



    public StudentsDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + TABLE_STUDENTS + "("
                + KEY_STUDENT_ID + " INTEGER PRIMARY KEY,"
                + KEY_STUDENT_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_ROUTINES + " TEXT,"
                + KEY_SALARY + " TEXT,"
                + KEY_NOTE + " TEXT,"
                + KEY_DAYS_TO_GET_SALARY + " TEXT" +
        ")";
        
        db.execSQL(CREATE_STUDENTS_TABLE);
    }





    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);

        // Create tables again
        onCreate(db);
    }





    void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        
        values.put(KEY_STUDENT_NAME, student.getStudentName());
        values.put(KEY_ADDRESS, student.getAddress());
        values.put(KEY_ROUTINES, student.getRoutine());
        values.put(KEY_SALARY, student.getSalary());
        values.put(KEY_NOTE, student.getNote());
        values.put(KEY_DAYS_TO_GET_SALARY, student.getDaysToGetSalary());
        

        db.insert(TABLE_STUDENTS, null, values);

        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }






    Student getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENTS, new String[] { KEY_STUDENT_ID, 
                        KEY_STUDENT_NAME,
                        KEY_ADDRESS,
                        KEY_ROUTINES,
                        KEY_SALARY,
                        KEY_NOTE,
                        KEY_DAYS_TO_GET_SALARY}, KEY_STUDENT_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Student student = new Student(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6)
        );
        
        return  student;
    }








    
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setStudentId(Integer.parseInt(cursor.getString(0)));
                student.setStudentName(cursor.getString(1));
                student.setAddress(cursor.getString(2));
                student.setRoutine(cursor.getString(3));
                student.setSalary(cursor.getString(4));
                student.setNote(cursor.getString(5));
                student.setDaysToGetSalary(cursor.getString(6));

                studentList.add(student);
            } while (cursor.moveToNext());
        }

       
        return studentList;
    }







  
    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_ID, student.getStudentId());
        values.put(KEY_STUDENT_NAME, student.getStudentName());
        values.put(KEY_ADDRESS, student.getAddress());
        values.put(KEY_ROUTINES, student.getRoutine());
        values.put(KEY_SALARY, student.getSalary());
        values.put(KEY_NOTE, student.getNote());
        values.put(KEY_DAYS_TO_GET_SALARY, student.getDaysToGetSalary());

        return db.update(TABLE_STUDENTS, values, KEY_STUDENT_ID + " = ?",
                new String[] { String.valueOf(student.getStudentId()) });
    }









    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, KEY_STUDENT_ID + " = ?",
                new String[] { String.valueOf(student.getStudentId()) });
        db.close();
    }


    
    
    public int getStudentsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }




    /* THESE TWO METHODS BELOW ARE FOR TESTING ONLY
       IF TABLE_STUDENTS NEEDS TO BE DROPPED THEN dropTable() NEEDS TO BE CALLED
       IF TABLE_STUDENTS NEEDS TO BE CREATED THEN createTable() NEEDS TO BE CALLED
     */

    
    public void dropTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + TABLE_STUDENTS);
        db.close();
    }

    
    
    public void createTable(){
        
        SQLiteDatabase db = this.getWritableDatabase();
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + TABLE_STUDENTS + "("
                + KEY_STUDENT_ID + " INTEGER PRIMARY KEY,"
                + KEY_STUDENT_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_ROUTINES + " TEXT,"
                + KEY_SALARY + " TEXT,"
                + KEY_NOTE + " TEXT,"
                + KEY_DAYS_TO_GET_SALARY + " TEXT" +
                ")";

        db.execSQL(CREATE_STUDENTS_TABLE);
        
    }
}

