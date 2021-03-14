package com.example.mynotes.db;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.mynotes.model.Note;

import java.util.List;

public class NotesManager {
    // Singleton
    private static NotesManager instance;

    private static final String INSERT_STMT =
            "INSERT INTO " + DbSchema.NotesTable.NAME + "(text) VALUES (?)";

    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public NotesManager(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static NotesManager getInstance(Context context) {
        if (instance == null) {
            instance = new NotesManager(context);
        }
        return instance;
    }

    public List<Note> all(){
        String sql = "SELECT * FROM notes";
        Cursor cursor = db.rawQuery(sql, null);
        NoteCursorWrapper cursorWrapper = new NoteCursorWrapper(cursor);

        return cursorWrapper.getNotes();
    }

    public boolean add(Note note){
        SQLiteStatement statement = db.compileStatement(INSERT_STMT);

        statement.bindString(1, note.getText());

        long id = statement.executeInsert();
        statement.executeUpdateDelete();

        if(id > 0){
            note.setId(id);
            return true;
        }

        return false;
    }

    public boolean delete(long id){
        int result = db.delete(DbSchema.NotesTable.NAME, "id = ?", new String[]{ id+"" });

        return result > 0;
    }

}
