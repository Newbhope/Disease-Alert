package com.group26.diseasealert.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.group26.diseasealert.LeDeviceListAdapter;
import com.group26.diseasealert.NewsDeviceListAdapter;
import com.group26.diseasealert.R;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by jp45275 on 4/15/2016.
 */
public class NewsFragment extends Fragment {

    SimpleCursorAdapter simpleCursorAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        NewsDeviceListAdapter listAdapter = new NewsDeviceListAdapter(getContext());

        ListView listView = (ListView) rootView.findViewById(R.id.listview_news);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = null;
                Log.e("News Fragment", "position =" + position);
                if(position == 0) {
                    url = "http://www.chicagotribune.com/news/local/breaking/ct-mumps-outbreak-university-of-illinois-met-0805-20150804-story.html";
                    /*
                    Intent intent = new Intent(getActivity(), InfoActivity.class);
                    intent.putExtra("disease", "Mumps");
                    startActivity(intent);
                    */
                }
                else if(position == 1){
                    url = "http://www.nbcchicago.com/news/local/15th-Measles-Case-Confirmed-in-Illinois-294092201.html";
                }
                if(url!=null) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            }
        });
        listAdapter.notifyDataSetChanged();
        //listAdapter.bind(0, rootView, "test", "test", "test" );

        /*
        arrayAdapter.add("Mumps Outbreak in Champaign, Illinois" +
                "   23 Affected");
        arrayAdapter.add("Measles Outbreak in Chicago, Illinois      10 Affected");
        arrayAdapter.add("Zika Outbreak in Rio De Janeiro, Brazil    45 Affected");
        arrayAdapter.add("Mumps Outbreak in Champaign, Illinois    5 Affected");
        arrayAdapter.add("Ebola Outbreak in Monrovia, Liberia    4809 Affected");
        arrayAdapter.add("Chikungunya Outbreak in the Americas     679 Affected");
        arrayAdapter.add("Multistate Outbreak of Salmonella Montevideo Infections Linked to Pistachios                                             11 Affected");
        arrayAdapter.add("Listeriosis in the USA                    " +
                "           146 Affected");
        arrayAdapter.add("ugh");
        */
        return rootView;
    }


}
