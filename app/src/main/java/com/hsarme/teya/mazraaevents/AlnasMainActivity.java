package com.hsarme.teya.mazraaevents;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AlnasMainActivity extends AppCompatActivity
{

    private static final String TAG = "MainActivity";
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private boolean shouldShow = false;
    private CompactCalendarView compactCalendarView;
    private ActionBar toolbar;
    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());










    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alnas_activity_main);















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














