package com.shyam.stayadobe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shyam.stayadobe.R;
import com.shyam.stayadobe.adapters.NotesListAdapter;
import com.shyam.stayadobe.db.DatabaseClient;
import com.shyam.stayadobe.db.entities.NotesE;
import com.shyam.stayadobe.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class NotesListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TextView tvNoRecords;
    private FloatingActionButton fabAddNote;
    private List<NotesE> listOfNotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = toolbar.findViewById(R.id.tv_toolbar_title);
        if (getSupportActionBar() != null)
            textView.setText(getString(R.string.notes));

        mRecyclerView = findViewById(R.id.recycler_view);
        tvNoRecords = findViewById(R.id.tv_no_records);
        fabAddNote = findViewById(R.id.fab);
        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesListActivity.this, EditNotesActivity.class);
                intent.putExtra(Constants.IS_NEW_NOTE, true);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (listOfNotes != null)
            listOfNotes.clear();
        listOfNotes = DatabaseClient.getInstance(NotesListActivity.this).getDataFactory().notesDao().getAllNotes();
        populateView(listOfNotes);
    }

    /* Populate views on recycler view*/
    private void populateView(List<NotesE> listOfNotes) {
        if (listOfNotes != null && listOfNotes.size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            tvNoRecords.setVisibility(View.GONE);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(layoutManager);
            NotesListAdapter adapter = new NotesListAdapter(this, listOfNotes);
            mRecyclerView.setAdapter(adapter);
        } else {
            tvNoRecords.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }

}
