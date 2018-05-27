package com.example.android.tourguide4;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Gardens extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final ArrayList<ListCreator> mallsDisp = new ArrayList<ListCreator>();
        mallsDisp.add(new ListCreator(getString(R.string.gard1_name), getString(R.string.gard1_add) , -1 ,R.drawable.mall1, "null"));
        mallsDisp.add(new ListCreator(getString(R.string.gard2_name), getString(R.string.gard2_add) , -1 ,R.drawable.mall2, "null"));
        mallsDisp.add(new ListCreator(getString(R.string.gard3_name), getString(R.string.gard3_add) , -1 ,R.drawable.mall3, "null"));
        mallsDisp.add(new ListCreator(getString(R.string.gard4_name), getString(R.string.gard4_add) , -1 ,R.drawable.hist4, "null"));

        View rootView = inflater.inflate(R.layout.activity_malls, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.MallList);
        ListAdapter la = new ListAdapter((Activity) rootView.getContext(),mallsDisp);
        lv.setAdapter(la);

        return rootView;
    }
}
