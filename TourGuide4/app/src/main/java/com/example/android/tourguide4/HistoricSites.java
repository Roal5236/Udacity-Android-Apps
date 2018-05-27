package com.example.android.tourguide4;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoricSites extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final ArrayList<ListCreator> mallsDisp = new ArrayList<ListCreator>();
        mallsDisp.add(new ListCreator(getString(R.string.hist1_name), getString(R.string.hist1_add) , -1 ,R.drawable.hist1, "null"));
        mallsDisp.add(new ListCreator(getString(R.string.hist2_name), getString(R.string.hist2_add) , -1 ,R.drawable.hist2, "null"));
        mallsDisp.add(new ListCreator(getString(R.string.hist3_name), getString(R.string.hist3_add) , -1 ,R.drawable.hist3, "null"));
        mallsDisp.add(new ListCreator(getString(R.string.hist4_name), getString(R.string.hist4_add) , -1 ,R.drawable.hist4, "null"));

        View rootView = inflater.inflate(R.layout.activity_malls, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.MallList);
        ListAdapter la = new ListAdapter((Activity) rootView.getContext(),mallsDisp);
        lv.setAdapter(la);

        return rootView;
    }
}
