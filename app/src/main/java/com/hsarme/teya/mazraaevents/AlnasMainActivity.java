package com.hsarme.teya.mazraaevents;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hsarme.teya.mazraaevents.data.Note;
import com.hsarme.teya.mazraaevents.data.NoteAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
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
    private ListView lstView;
    private NoteAdapter noteAdapter;
    private TextView itmEvent;










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
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));

            }
        });
        lstView = (ListView) findViewById(R.id.lstview);
        noteAdapter = new NoteAdapter(getBaseContext(), R.layout.note_adapter);
        lstView.setAdapter(noteAdapter);
        readAndListen();

    }
    private void readAndListen()
    {
        FirebaseAuth auth = FirebaseAuth.getInstance();// to get user email.. user info
        FirebaseUser user = auth.getCurrentUser();
        String email = user.getEmail();
        email = email.replace('.', '*');
        DatabaseReference reference;// 3nwan entrnet
        //todo לקבלת קישט=ור למסך הניתונים שלנו
        //todo קישור הינו לשורש של המסך הניתונים
        //7. saving data on the firebase database
        reference = FirebaseDatabase.getInstance().getReference();
        // 8. add completeListener to check if the insertion done

        //// todo בפעם הראשונה שמופעל המאזין מרבלים בעתק לכל הנתונים תחת כתובת זו
        reference.child(email).child("my list").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) //// todo העתק מהנותנים שהורדנו
            {
                noteAdapter.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Note n = ds.getValue(Note.class);
                    EventObject notes;
                    Log.d("SL", n.toString());
                    noteAdapter.add(n);

                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    }









































