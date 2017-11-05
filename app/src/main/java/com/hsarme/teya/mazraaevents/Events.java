package com.hsarme.teya.mazraaevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Events extends AppCompatActivity {
    private Button btjan,btfeb,btmar,btapril,btmay,btjune,btjuly,btaug,btsep,btoct,btnov,btndec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        btjan=(Button) findViewById(R.id.btjan);
        btfeb=(Button) findViewById(R.id.btfeb);
        btmar=(Button) findViewById(R.id.btmar);
        btapril=(Button) findViewById(R.id.btapril);
        btmay=(Button) findViewById(R.id.btmay);
        btjune=(Button) findViewById(R.id.btjune);
       btjuly=(Button) findViewById(R.id.btjuly);
        btaug=(Button) findViewById(R.id.btaug);
        btsep=(Button) findViewById(R.id.btsep);
        btoct=(Button) findViewById(R.id.btoct);
        btnov=(Button) findViewById(R.id.btnov);
        btndec=(Button) findViewById(R.id.btndec);
    }
}
