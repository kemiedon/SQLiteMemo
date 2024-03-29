package com.example.sqlitememo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SpinnerAdapter  extends BaseAdapter {
    ArrayList<ColorData> color_list;
    LayoutInflater inflater;
    Context c;

    public SpinnerAdapter(ArrayList<ColorData> color_list, Context c) {
        this.color_list = color_list;
        inflater = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return color_list.size();
    }

    @Override
    public Object getItem(int position) {
        return color_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return color_list.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ColorData item = (ColorData) getItem(position);
        convertView = inflater.inflate(R.layout.color_view, null);
        ImageView ticket = convertView.findViewById(R.id.ticket);
        TextView color_name =convertView.findViewById(R.id.color_name);
        ticket.setBackgroundColor(Color.parseColor(item.code));
        color_name.setText(item.name);
        return convertView;
    }
}
