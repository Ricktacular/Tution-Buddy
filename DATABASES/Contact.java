package com.tutionbuddy.tutionbuddy;

/**
 * Created by dipto on 11/29/17.
 */

public class Contact {
    private int ContactId;
    private int studentId;
    private String contactNumber;

    public Contact() {
    }

    public Contact(int contactId) {
        ContactId = contactId;
    }

    public Contact(int studentId, String contactNumber) {
        this.studentId = studentId;
        this.contactNumber = contactNumber;
    }

    public Contact(int contactId, int studentId, String contactNumber) {
        ContactId = contactId;
        this.studentId = studentId;
        this.contactNumber = contactNumber;
    }

    public int getContactId() {
        return ContactId;
    }

    public void setContactId(int contactId) {
        ContactId = contactId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
