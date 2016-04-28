package com.group26.diseasealert.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class DiscussionFragment extends Fragment{

    public DiscussionFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_discussion, container, false);

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.item_discussion,
                R.id.list_item_discussion,
                new ArrayList<String>()
        );

        ListView listView = (ListView) rootView.findViewById(R.id.listview_discussion);
        listView.setAdapter(arrayAdapter);

        arrayAdapter.add("Discussion 1");
        arrayAdapter.add("Discussion 2");

        return rootView;
    }
}
