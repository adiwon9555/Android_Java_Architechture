package com.example.architectureexample.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.architectureexample.R;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.architectureexample.ui.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.architectureexample.ui.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.architectureexample.ui.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.architectureexample.ui.EXTRA_PRIORITY";


    private EditText text_Title;
    private EditText text_Description;
    private NumberPicker number_Picker_Priority;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_form);
        text_Title = findViewById(R.id.text_Title);
        text_Description = findViewById(R.id.text_Description);
        number_Picker_Priority = findViewById(R.id.number_Picker_Priority);
        number_Picker_Priority.setMinValue(1);
        number_Picker_Priority.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent data = getIntent();
        if (data.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            text_Title.setText(data.getStringExtra(EXTRA_TITLE));
            text_Description.setText(data.getStringExtra(EXTRA_DESCRIPTION));
            number_Picker_Priority.setValue(data.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
        String title = text_Title.getText().toString();
        String description = text_Description.getText().toString();
        int priority = number_Picker_Priority.getValue();
        if (title.trim().equals("") || description.trim().equals("")) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);
        setResult(RESULT_OK, data);
        finish();

    }
}
