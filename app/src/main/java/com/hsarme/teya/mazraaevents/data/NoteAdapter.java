package com.hsarme.teya.mazraaevents.data;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hsarme.teya.mazraaevents.R;

/**
 * Created by user on 11/03/2018.
 */

public class NoteAdapter extends ArrayAdapter<Note> {


    public NoteAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.note_adapter, parent, false);
        TextView itmEvent = (TextView) view.findViewById(R.id.itmEvent);
        TextView itmEvent2 = (TextView) view.findViewById(R.id.itmEvent2);
        TextView itmEvent3 = (TextView) view.findViewById(R.id.itmEvent3);
        Note n = getItem(position);
        itmEvent.setText(n.getNotes());
        itmEvent2.setText(n.getNotes());
        itmEvent3.setText(n.getNotes());


        return view;


    }
}

