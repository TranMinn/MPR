package com.example.mynotes.model;

public class Note {
    private long id;
    private String text;

    // Data from form
    public Note(String text){
        this.text = text;
    }

    // Data from database
    public Note(long id, String text) {
        this.id = id;
        this.text = text;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
