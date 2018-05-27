package com.example.android.tourguide4;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class restaurants extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final ArrayList<ListCreator> mallsDisp = new ArrayList<ListCreator>();
        mallsDisp.add(new ListCreator(getString(R.string.rest1_name), getString(R.string.rest1_add) , -1 ,R.drawable.rest2, getString(R.string.rest1_phno)));
        mallsDisp.add(new ListCreator(getString(R.string.rest2_name), getString(R.string.rest2_add) , -1 ,R.drawable.rest2, getString(R.string.rest2_phno)));
        mallsDisp.add(new ListCreator(getString(R.string.rest3_name), getString(R.string.rest3_add) , -1 ,R.drawable.rest2, getString(R.string.rest3_phno)));
        mallsDisp.add(new ListCreator(getString(R.string.rest4_name), getString(R.string.rest4_add) , -1 ,R.drawable.rest2, getString(R.string.rest4_phno)));

        View rootView = inflater.inflate(R.layout.activity_malls, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.MallList);
        ListAdapter la = new ListAdapter((Activity) rootView.getContext(),mallsDisp);
        lv.setAdapter(la);

        return rootView;
    }
}


