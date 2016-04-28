package com.group26.diseasealert.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.group26.diseasealert.R;

import java.util.ArrayList;

/**
 * Created by newho on 4/28/2016.
 */
public class DoctorFragment extends Fragment{

    public DoctorFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_doctor, container, false);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.news_item,
                R.id.list_item_news_textview,
                new ArrayList<String>()
        );

        ListView listView = (ListView) rootView.findViewById(R.id.listview_doctor);
        listView.setAdapter(arrayAdapter);

        arrayAdapter.add("Doctor 1");
        arrayAdapter.add("Doctor 2");

        return rootView;
    }
}
