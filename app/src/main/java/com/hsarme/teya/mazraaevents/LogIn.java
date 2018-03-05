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
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
              //  dataHandler();
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

    private void dataHandler()
    {
        String stemail=etEmail.getText().toString();
        String stPaswword=etPassword.getText().toString();
        boolean isOk= true;
        if(stemail.length()==0|| stemail.indexOf('@')<1)
        {
            etEmail.setError("wrong email");
            isOk=false;
        }
        if (stPaswword.length()<8)
        {
            etPassword.setError("bad password");
            isOk=false;
        }
        if (isOk)
        {
            creatAcount(stemail,stPaswword);

        }
    }


    private void creatAcount(String stemail, String stPaswword) {
        auth.createUserWithEmailAndPassword(stemail,stPaswword).addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {



            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(LogIn.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();

                    finish();
                }
                else
                {
                    Toast.makeText(LogIn.this, "Authentication failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
        FirebaseAuth.getInstance().signOut();



    }

    @Override
    public void onClick(View view) {
        if(btnIn==view)
        dataHandler();
    }
}





















