package com.example.mynotes.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.mynotes.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteCursorWrapper extends CursorWrapper {
    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Note getNote(){
        String noteText = getString(getColumnIndex(DbSchema.NotesTable.Cols.NOTE_TEXT));

        Note note = new Note(noteText);
        return note;
    }

    public Note getNoteById(){
        Note note = null;

        moveToFirst();
        if (!isAfterLast()) {
            note = getNote();
        }
        return note;
    }

    public List<Note> getNotes(){
        List<Note> notes = new ArrayList<>();

        moveToFirst();
        while (!isAfterLast()){
            Note note = getNote();
            notes.add(note);

            moveToNext();
        }
        return notes;
    }
}
