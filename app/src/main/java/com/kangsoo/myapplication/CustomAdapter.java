package com.kangsoo.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by bsnc on 2015-04-01.
 */
class CustomAdapter extends ArrayAdapter{

    CustomAdapter(Context context, String[] foods) {
        //Use layout Custom_row
        super(context, R.layout.custom_row, foods);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myLayoutInf = LayoutInflater.from(getContext());
        View customView = myLayoutInf.inflate(R.layout.custom_row, parent, false);

        String singleFoodItem = getItem(position).toString();
        TextView myText = (TextView) customView.findViewById(R.id.tvCustomRow);
        ImageView myImage = (ImageView) customView.findViewById(R.id.ivCustomRow);

        myText.setText(singleFoodItem);
        myImage.setImageResource(R.drawable.myface);

        //oh my god~
        return customView;
    }
}
