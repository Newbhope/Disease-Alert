package com.group26.diseasealert;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.group26.diseasealert.fragment.DiscussionFragment;
import com.group26.diseasealert.fragment.DoctorFragment;
import com.group26.diseasealert.fragment.InfoFragment;
import com.group26.diseasealert.fragment.MeaslesFragment;
import com.group26.diseasealert.fragment.NewsFragment;
import com.group26.diseasealert.settings.SettingsActivity;
import com.group26.diseasealert.settings.SettingsPreferenceActivity;

public class InfoActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private String disease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        String disease = bundle.getString("disease");
        if(disease==null){ //should be from search intent
            Uri data = intent.getData();
            String id = intent.getStringExtra(SearchManager.EXTRA_DATA_KEY);
            disease = data.toString();
        }
        if(disease!=null) getSupportActionBar().setTitle(disease);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        assert mViewPager != null;
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        assert tabLayout != null;
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            String disease = bundle.getString("disease");
            if(disease==null){ //should be from search intent
                Uri data = intent.getData();
                String id = intent.getStringExtra(SearchManager.EXTRA_DATA_KEY);
                disease = data.toString();
            }

            if(position == 0){
                return InfoFragment.newInstance(disease);
            }
            /*
            else if(position == 0 && disease.equals("Measles")){
                return new MeaslesFragment();
            }*/
            else if(position == 1){
                return new DoctorFragment();
            }
            else{ // position == 2
                return new DiscussionFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Info";
                case 1:
                    return "Doctors";
                case 2:
                    return "Discussions";
            }
            return null;
        }
    }
}
