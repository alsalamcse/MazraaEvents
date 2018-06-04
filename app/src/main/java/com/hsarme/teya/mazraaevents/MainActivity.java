package com.hsarme.teya.mazraaevents;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private boolean shouldShow = false;
    private CompactCalendarView compactCalendarView;
    private ActionBar toolbar;
    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private ImageButton btnAdd;
    private Date choosedDate;
    private ListView lstView;
    private NoteAdapter noteAdapter;
    private TextView itmEvent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        choosedDate=Calendar.getInstance().getTime();//altare5 el7ali
        btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (choosedDate == null) {
                    Toast.makeText(getBaseContext(), "CHOOSE A DATE TO ADD ", Toast.LENGTH_SHORT).show();

                } else {
                    Intent i = new Intent(getBaseContext(), AddNote.class);
                    i.putExtra("date", choosedDate);
                    startActivity(i);
                }


            }
        });



        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        //set an event
        final Date date = new Date(10, 3, 2018);
        Event ev1 = new Event(Color.BLUE, date.getTime(), "Teacher Day");
        compactCalendar.addEvent(ev1);


        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                choosedDate = dateClicked;
                readAndListen();// kol ma a5tar tare5 lazm y2rahn kman mra
                if (dateClicked.compareTo(date) == 0) {
                    Toast.makeText(context, "Teacher Day ", Toast.LENGTH_SHORT).show();


                } else
                    Toast.makeText(context, "No Event planned ", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));

            }
        });
        lstView = (ListView)findViewById(R.id.lstview);
        noteAdapter = new NoteAdapter(getBaseContext(), R.layout.note_adapter);
        lstView.setAdapter(noteAdapter);
        readAndListen();
    }


    //    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.activity_main, container, false);
//        itmEvent = (TextView) view.findViewById(R.id.itmEvent);
//        lstView = (ListView) view.findViewById(R.id.lstview);
//        noteAdapter = new NoteAdapter(getBaseContext(), R.layout.note_adapter);
//        lstView.setAdapter(noteAdapter);
//       //read and listen
//        return view;
//
//
//    }
    private void readAndListen() {
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

        //todo orderByChild("month").equalTo(choosedDate.getMonth())
        //// todo בפעם הראשונה שמופעל המאזין מרבלים בעתק לכל הנתונים תחת כתובת זו
        reference.child(email).child("myNote").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) //// todo העתק מהנותנים שהורדנו
            {
                noteAdapter.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Note n = ds.getValue(Note.class);
                    if (n.getDate().getMonth()==choosedDate.getMonth()&& n.getDate().getDay()==choosedDate.getDay()&& n.getDate().getYear()==choosedDate.getYear()) {
                        Log.d("SL", n.toString());
                        noteAdapter.add(n);
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }


}






















