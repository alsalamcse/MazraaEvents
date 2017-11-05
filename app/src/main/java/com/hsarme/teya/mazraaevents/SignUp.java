package com.hsarme.teya.mazraaevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
    private EditText etName,etemail,etpassword,etPhone;
    private Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etName=(EditText)findViewById(R.id.etName);
        etemail=(EditText)findViewById(R.id.etemail);
        etpassword=(EditText)findViewById(R.id.etpassword);
        etPhone=(EditText)findViewById(R.id.etPhone);
        btnsave=(Button) findViewById(R.id.btnsave);
    }
}
