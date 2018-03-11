package com.hsarme.teya.mazraaevents;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsarme.teya.mazraaevents.data.Note;

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
                dataHandler();

            }
        });




    }





    public void dataHandler() {
        String stnote = etNote.getText().toString();

        Note n = new Note();
        n.setNotes(stnote);


        FirebaseAuth auth = FirebaseAuth.getInstance();// to get user email.. user info
        FirebaseUser user = auth.getCurrentUser();
        String email = user.getEmail();
        email=email.replace('.','*');

        DatabaseReference reference;
        reference= FirebaseDatabase.getInstance().getReference();
        reference.child(email).child("my Note").push().setValue(n).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful()){
                    Toast.makeText(AddNote.this, "Add Note Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    Toast.makeText(AddNote.this, "Add Note Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
