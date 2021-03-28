package com.example.mynotes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynotes.db.NotesManager;
import com.example.mynotes.model.Note;
public class EditNote extends AppCompatActivity {

    private EditText edtNote;
    private Note note;
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__note);

        Intent intent = getIntent();
        id = intent.getLongExtra("id", -1);
        Toast.makeText(this, "" + id, Toast.LENGTH_SHORT).show();
        NotesManager noteManager = NotesManager.getInstance(this);
        note = noteManager.findById(id);

        edtNote = findViewById(R.id.edt_text_note);
        edtNote.setText(note.getText());
    }

    @Override
    public void onBackPressed() {
        note.setText(edtNote.getText().toString());
        note.setId(id);
        NotesManager.getInstance(this).update(note);
        setResult(RESULT_OK);
        super.onBackPressed();
    }

}
