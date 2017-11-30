package com.tutionbuddy.tutionbuddy;

/**
 * Created by dipto on 11/29/17.
 */

public class Student {
    private int studentId;
    private String studentName;
    private String address;
    private String routine;
    private String salary;
    private String daysToGetSalary;
    private String note;

    public Student(){}

    public Student(int studentId) {
        this.studentId = studentId;
    }



    public Student(int studentId, String studentName, String address,
                   String routine, String salary, String daysToGetSalary,
                   String note) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.address = address;
        this.routine = routine;
        this.salary = salary;
        this.daysToGetSalary = daysToGetSalary;
        this.note = note;
    }



    public Student(String studentName, String address, String routine,
                   String salary, String daysToGetSalary, String note) {
        this.studentName = studentName;
        this.address = address;
        this.routine = routine;
        this.salary = salary;
        this.daysToGetSalary = daysToGetSalary;
        this.note = note;
    }




    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoutine() {
        return routine;
    }

    public void setRoutine(String routine) {
        this.routine = routine;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDaysToGetSalary() {
        return daysToGetSalary;
    }

    public void setDaysToGetSalary(String daysToGetSalary) {
        this.daysToGetSalary = daysToGetSalary;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
