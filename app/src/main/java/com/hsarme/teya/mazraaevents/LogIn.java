package com.hsarme.teya.mazraaevents;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity implements View.OnClickListener{
    private EditText etEmail,etPassword;
    private Button btnIn,btnUp;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        if (firebaseUser != null || firebaseUser.getEmail().length()==0)
        {

        }
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnIn = (Button) findViewById(R.id.btnIn);
        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {;
              dataHandler();
            }
        });

        btnUp = (Button) findViewById(R.id.btnUp);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), SignUp.class);
                startActivity(i);
            }
        });

    }








    private void signIn(String email, String passw) {
        auth.signInWithEmailAndPassword(email, passw).addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LogIn.this, "signIn Successful.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LogIn.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(LogIn.this, "signIn failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }

    private void dataHandler() {
        String stemail = etEmail.getText().toString();
        String stpassword = etPassword.getText().toString();
        signIn(stemail, stpassword);

    }








    @Override
    public void onClick(View view) {
        if(btnIn==view)
        dataHandler();
    }
}





















