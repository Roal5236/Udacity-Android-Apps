package com.example.android.tourguide4;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rohaan on 16-Oct-17.
 */

public class ListAdapter extends ArrayAdapter<ListCreator>{

    public ListAdapter(Activity context, ArrayList<ListCreator> l){
        super(context,0,l);
    }

    public View getView(int pos, View convertView, ViewGroup parent){

        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_layout, parent, false);
        }


        ListCreator listC = getItem(pos);

        TextView tv =(TextView) listItemView.findViewById(R.id.mallName);
        tv.setText(listC.getLoc_name());

        TextView tv1 =(TextView) listItemView.findViewById(R.id.mallLocation);
        tv1.setText(listC.getLoc_Location());

        if(listC.hasPhno()){

            TextView tv2 =(TextView) listItemView.findViewById(R.id.mallPhno);
            tv2.setText(listC.getLoc_Phno());

        }
        else {
            TextView tv2 =(TextView) listItemView.findViewById(R.id.mallPhno);
            tv2.setVisibility(View.GONE);
        }

        if(listC.hasImage1()){

            ImageView iv =(ImageView) listItemView.findViewById(R.id.mallImg);
            iv.setImageResource(listC.getLoc_imageid());

        }
        else {
            ImageView iv =(ImageView) listItemView.findViewById(R.id.mallImg);
            iv.setVisibility(View.GONE);
        }

        if(listC.hasImage2()) {
            ImageView iv =(ImageView) listItemView.findViewById(R.id.mallImg2);
            iv.setImageResource(listC.getLoc_imageid2());
        }
        else {
            ImageView iv =(ImageView) listItemView.findViewById(R.id.mallImg2);
            iv.setVisibility(View.GONE);
        }


        return listItemView;
    }



}
