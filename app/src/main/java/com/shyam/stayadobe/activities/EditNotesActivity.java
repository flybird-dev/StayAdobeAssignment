package com.shyam.stayadobe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.shyam.stayadobe.R;
import com.shyam.stayadobe.db.DatabaseClient;
import com.shyam.stayadobe.db.entities.NotesE;
import com.shyam.stayadobe.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        final EditText edtNote = findViewById(R.id.edt_note);
        LinearLayout btnLayout = findViewById(R.id.btn_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView toolbarTitle = toolbar.findViewById(R.id.tv_toolbar_title);

        Intent intent = getIntent();
        boolean isNewNote = intent.getBooleanExtra(Constants.IS_NEW_NOTE, false);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            if (isNewNote)
                toolbarTitle.setText(getString(R.string.new_note));
            else
                toolbarTitle.setText(getString(R.string.edit_note));
        }

        final NotesE notes = new Gson().fromJson(getIntent().getStringExtra(Constants.MODEL_DATA), NotesE.class);
        if (notes != null)
            edtNote.setText(notes.getDescription());

        btnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = edtNote.getText().toString().trim();
                String currentDate = new SimpleDateFormat(Constants.NOTE_DATE_FORMAT, Locale.ENGLISH).format(new Date());
                if (notes != null && notes.getId_note() != 0) {
                    notes.setId_note(notes.getId_note());
                    notes.setDate(currentDate);
                    notes.setDescription(description);
                    DatabaseClient.getInstance(EditNotesActivity.this).getDataFactory().notesDao().updateNote(notes);
                    Toast.makeText(EditNotesActivity.this, getString(R.string.note_updated), Toast.LENGTH_SHORT).show();
                    finish();
                } else if (!description.isEmpty()) {
                    NotesE notes = new NotesE("Title ", currentDate, description);
                    DatabaseClient.getInstance(EditNotesActivity.this).getDataFactory().notesDao().insertNote(notes);
                    finish();
                } else {
                    Toast.makeText(EditNotesActivity.this, getString(R.string.please_enter_note), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
