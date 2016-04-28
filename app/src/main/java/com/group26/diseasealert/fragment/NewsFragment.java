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
        /*
        Button testButton = (Button) rootView.findViewById(R.id.test);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                startActivity(intent);
            }
        });
        simpleCursorAdapter = new SimpleCursorAdapter(getContext(),
                android.R.layout.simple_list_item_1, null,
                fromColumns, toViews, 0);
        */
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.item_news,
                R.id.list_item_news_textview,
                new ArrayList<String>()
        );

        ListView listView = (ListView) rootView.findViewById(R.id.listview_news);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("News Fragment", "position =" + position);
                if(position == 0) {
                    String url = "http://www.chicagotribune.com/news/local/breaking/ct-mumps-outbreak-university-of-illinois-met-0805-20150804-story.html";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    /*
                    Intent intent = new Intent(getActivity(), InfoActivity.class);
                    intent.putExtra("disease", "Mumps");
                    startActivity(intent);
                    */
                }
            }
        });

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
        return rootView;
    }


}
