package com.hsarme.teya.mazraaevents.data;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by user on 11/03/2018.
 */

public class NoteAdapter extends ArrayAdapter<Note>
{


    public NoteAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }
}
