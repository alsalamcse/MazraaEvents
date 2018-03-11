package com.hsarme.teya.mazraaevents.data;

/**
 * Created by user on 11/03/2018.
 */

public class Note

{
    private String notes;

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

    @Override
    public String toString() {
        return "Note{" +
                "notes='" + notes + '\'' +
                ", keyId='" + keyId + '\'' +
                '}';
    }
}
