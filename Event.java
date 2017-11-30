package com.tutionbuddy.tutionbuddy;

/**
 * Created by dipto on 11/29/17.
 */

public class Event {
    private int eventId;
    private String date;
    private String time;
    private String note;
    private String reminder;

    public Event() {
    }

    public Event(int eventId) {
        this.eventId = eventId;
    }

    public Event(String date, String time, String note, String reminder) {
        this.date = date;
        this.time = time;
        this.note = note;
        this.reminder = reminder;
    }

    public Event(int eventId, String date, String time, String note, String reminder) {
        this.eventId = eventId;
        this.date = date;
        this.time = time;
        this.note = note;
        this.reminder = reminder;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }
}
