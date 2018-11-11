package com.example.gilman.maybemajor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

public class CustomAdapter extends ArrayAdapter {
    public CustomAdapter(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
    }
}
