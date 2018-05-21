package com.hsarme.teya.mazraaevents.data;

import java.util.Date;

/**
 * Created by user on 11/03/2018.
 */

public class Note

{
    private String notes;
    private Date date;

    private String keyId;

    public Note() {
        this.notes = notes;
        this.keyId = keyId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Note{" +
                "notes='" + notes + '\'' +
                ", keyId='" + keyId + '\'' +
                '}';
    }
}
