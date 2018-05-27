package com.example.android.tourguide4;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class Malls extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final ArrayList<ListCreator> mallsDisp = new ArrayList<ListCreator>();
        mallsDisp.add(new ListCreator(getString(R.string.mall1_name), getString(R.string.mall1_add) , R.drawable.mall1 ,-1,  getString(R.string.mall1_phno)));
        mallsDisp.add(new ListCreator(getString(R.string.mall2_name), getString(R.string.mall2_add) , R.drawable.mall2 ,-1, getString(R.string.mall2_phno)));
        mallsDisp.add(new ListCreator(getString(R.string.mall3_name), getString(R.string.mall3_add) , R.drawable.mall3 ,-1, getString(R.string.mall3_phno)));
        mallsDisp.add(new ListCreator(getString(R.string.mall4_name), getString(R.string.mall4_add) , R.drawable.hist1 ,-1, getString(R.string.mall4_phno)));

        View rootView = inflater.inflate(R.layout.activity_malls, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.MallList);
        ListAdapter la = new ListAdapter((Activity) rootView.getContext(),mallsDisp);
        lv.setAdapter(la);

        return rootView;
    }


}
