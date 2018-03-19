package com.hsarme.teya.mazraaevents;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hsarme.teya.mazraaevents.data.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity
{


    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private ImageButton btnAdd;









    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnAdd=(ImageButton)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),AddNote.class);
                startActivity(i);

            }
        });











        final ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);

        compactCalendar=(CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        //set an event
        final Date date=new Date(10,3,2018);
        Event ev1=new Event(Color.BLUE, date.getTime(),"Teacher Day");
        compactCalendar.addEvent( ev1);


        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                if (dateClicked.compareTo(date)== 0 ) {
                    Toast.makeText(context, "Teacher Day ", Toast.LENGTH_SHORT).show();


                }
                else
                    Toast.makeText(context,"No Event planned ",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth)
            {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));

            }
        });
}
}














