package com.tutionbuddy.tutionbuddy;

/**
 * Created by dipto on 11/29/17.
 */

public class Date {
    private int dateId;
    private int studentId;
    private String date;
    private String note;

    public Date() {
    }

    public Date(int dateId) {
        this.dateId = dateId;
    }

    public Date(int studentId, String date, String note) {
        this.studentId = studentId;
        this.date = date;
        this.note = note;
    }

    public Date(int dateId, int studentId, String date, String note) {
        this.dateId = dateId;
        this.studentId = studentId;
        this.date = date;
        this.note = note;
    }

    public int getDateId() {
        return dateId;
    }

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
