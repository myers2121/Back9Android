package com.astral.back9;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendsAdapter extends ArrayAdapter<Person> {

    public FriendsAdapter(ArrayList<Person> friendsList, Context ctx) {
        super(ctx,0, friendsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Person p = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.friends_clicked_layout, parent,false);
        }

        TextView nameTv = (TextView) convertView.findViewById(R.id.friends_name);
        TextView locationTV = (TextView) convertView.findViewById(R.id.friends_location);


        nameTv.setText(p.name);
        locationTV.setText(p.location);
        return convertView;
    }
}
