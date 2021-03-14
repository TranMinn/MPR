package com.example.mynotes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private Context mcontext;
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table
        db.execSQL("CREATE TABLE " + DbSchema.NotesTable.NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbSchema.NotesTable.Cols.NOTE_TEXT + " TEXT " + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // for debug
        Log.w("My Notes", "My Notes: upgrading DB; dropping/recreating tables.");

        // Drop exixting table
        db.execSQL("DROP TABLE IF EXISTS " + DbSchema.NotesTable.NAME);

        // onCreate
        onCreate(db);
    }
}
