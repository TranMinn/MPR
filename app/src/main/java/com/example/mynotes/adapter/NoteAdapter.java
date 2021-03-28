package com.example.mynotes.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mynotes.EditNote;
import com.example.mynotes.R;
import com.example.mynotes.db.NotesManager;
import com.example.mynotes.model.Note;

import java.util.List;

//Create the basic adapter extending from RecyclerView.Adapter
//Note that we specify the custom ViewHolder which give us access to our views
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private final List<Note> notes;
    private static final int EDITED_CODE = 2;
    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Activity to display
        Context context = parent.getContext();

        //xml to java Object
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_note, parent, false);

        return new NoteHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
            Note note = notes.get(position);
            holder.bind(note);
    }

    @Override
    public int getItemCount() {

        return notes.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        private TextView tvNote;
        private Context context;

        public NoteHolder(@NonNull View itemView, Context context) {
            super(itemView);
            tvNote = itemView.findViewById(R.id.tvNote);
            this.context = context;
        }

        public void bind(Note note){
            tvNote.setText(note.getText());

            tvNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent data = new Intent(context, EditNote.class);

                    data.putExtra("id", note.getId());
                    data.putExtra("text", note.getText());
                    ((Activity) context).startActivityForResult(data, EDITED_CODE);
                }
            });

            tvNote.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new AlertDialog.Builder(context)
                            .setTitle("Delete")
                            .setMessage("Do you want to delete this note?")
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    notes.remove(note);
                                    NotesManager.getInstance(context).delete(note.getId());

                                    notifyDataSetChanged();
                                }
                            }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();


                    return true;
                }
            });
        }
    }

}
