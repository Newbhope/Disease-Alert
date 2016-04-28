package com.group26.diseasealert.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.group26.diseasealert.InfoActivity;
import com.group26.diseasealert.R;

/**
 * Created by jp45275 on 4/15/2016.
 */
public class DiseaseMapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_disease_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mMap.setMyLocationEnabled(true);


        //LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);


        // Add a marker in Sydney and move the camera
        LatLng uiuc = new LatLng(40.108196, -88.227184);
        mMap.addCircle(new CircleOptions()
                .center(uiuc)
                .radius(500)
                .strokeColor(0xffff0000)
                .fillColor(R.color.colorPrimaryLight)
        );
        mMap.addMarker(new MarkerOptions()
                .position(uiuc)
                .title("Mumps Outbreak, 23 Affected")

        );
        mMap.addCircle(new CircleOptions()
                .center(new LatLng(41.871213, -87.629018))
                .radius(250)
                .strokeColor(0xffff0000)
                .fillColor(R.color.colorPrimaryLight)
        );
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.871213, -87.629018))
                .title("Measles Outbreak, 10 Affected")
        );

        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (android.location.Location) locationListener);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uiuc, 15));
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Log.e("test", "" + marker);
                String url;
                if (marker.getTitle().equals(("Mumps Outbreak, 23 Affected"))) {
                    url = "http://www.chicagotribune.com/news/local/breaking/ct-mumps-outbreak-university-of-illinois-met-0805-20150804-story.html";
                } else {
                    url = "http://chicago.suntimes.com/news/chicago-measles-case-among-10-confirmed-in-illinois/";
                }
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                /*
                Intent intent = new Intent(getContext(), InfoActivity.class);
                intent.putExtra("disease", "Mumps");
                startActivity(intent);
                */
            }
        });
    }
}
