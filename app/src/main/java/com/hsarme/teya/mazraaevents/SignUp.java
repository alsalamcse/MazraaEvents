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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    private EditText etName,etemail,etpassword,etPhone;
    private Button btnsave;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etName=(EditText)findViewById(R.id.etName);
        etemail=(EditText)findViewById(R.id.etemail);
        etpassword=(EditText)findViewById(R.id.etpassword);
        etPhone=(EditText)findViewById(R.id.etPhone);
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        btnsave=(Button) findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();

            }
        });




    }
    private void dataHandler()
    {
        String stemail=etemail.getText().toString();
        String stPaswword=etpassword.getText().toString();
        String name=etName.getText().toString();
        String phone=etPhone.getText().toString();
        boolean isOk= true;
        if(stemail.length()==0|| stemail.indexOf('@')<1)
        {
            etemail.setError("wrong email");
            isOk=false;
        }
        if (stPaswword.length()<8)
        {
            etpassword.setError("bad password");
            isOk=false;
        }
        if (isOk)
        {
            creatAcount(stemail,stPaswword);

        }
    }
    private void creatAcount(String email, String passw) {
        auth.createUserWithEmailAndPassword(email,passw).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {



            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(SignUp.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();

                    finish();
                }
                else
                {
                    Toast.makeText(SignUp.this, "Authentication failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
        FirebaseAuth.getInstance().signOut();


    }


    @Override
    public void onClick(View view) {
        if(btnsave==view){
            dataHandler();
    }
}
}
