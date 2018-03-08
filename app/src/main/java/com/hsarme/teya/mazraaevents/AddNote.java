package com.hsarme.teya.mazraaevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNote extends AppCompatActivity {

    private EditText etNote;
    private Button btSent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        etNote=(EditText)findViewById(R.id.etNote);
        btSent=(Button)findViewById(R.id.btSent);
        btSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




    }
    public void dataHandler()
    {
        String stnote=etNote.getText().toString();


    }
}
