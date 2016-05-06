package com.group26.diseasealert.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.group26.diseasealert.LeDeviceListAdapter;
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

        ListView listView = (ListView) rootView.findViewById(R.id.device_list);
        LeDeviceListAdapter listAdapter = new LeDeviceListAdapter(getContext());
        listView.setAdapter(listAdapter);

        listAdapter.notifyDataSetChanged();

        return rootView;
    }
}
