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
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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

        LatLng uiuc = new LatLng(40.108196, -88.227184);
        mMap.addCircle(new CircleOptions()
                .center(uiuc)
                .radius(500)
                .strokeColor(0xffff0000)
                .fillColor(R.color.colorPrimaryLight)
        );
        final Marker uofi = mMap.addMarker(new MarkerOptions()
                .position(uiuc)
                .title("Mumps Outbreak, 23 Affected")

        );
        mMap.addCircle(new CircleOptions()
                .center(new LatLng(41.871213, -87.629018))
                .radius(250)
                .strokeColor(0xffff0000)
                .fillColor(R.color.colorPrimaryLight)
        );
        final Marker chicago = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.871213, -87.629018))
                .title("Measles Outbreak, 15 Affected")
        );
        final Marker cupertino = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.346513, -122.038803))
                .title("Mumps Outbreak, 2 Affected"));
        mMap.addCircle(new CircleOptions().center(new LatLng(37.346513, -122.038803))
                .radius(100)
                .strokeColor(0xffff0000)
                .fillColor(R.color.colorPrimaryLight));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.795659, -106.354569))
                .title("Measles Outbreak, 4 Affected"));
        mMap.addCircle(new CircleOptions().center(new LatLng(42.795659, -106.354569)).radius(500).strokeColor(0xffff0000).fillColor(R.color.colorPrimaryLight));

        mMap.addMarker(new MarkerOptions().position(new LatLng(44.264500, -72.577475)).title("Mumps Outbreak, 3 Affected"));
        mMap.addCircle(new CircleOptions().center(new LatLng(44.264500, -72.577475)).radius(300).strokeColor(0xffff0000).fillColor(R.color.colorPrimaryLight));


        //Locations
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(40.112351, -88.242232))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title("Primary Location")
        );
        Marker prospect = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.102639, -87.936701))
                .title("Secondary Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.319898, -122.032830))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                .title("Tertiary Location")
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

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.112351, -88.242232), 15));
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (marker.getTitle().equals("Mumps Outbreak, 23 Affected") || marker.getTitle().equals("Mumps Outbreak, 2 Affected") || marker.getTitle().equals("Mumps Outbreak, 3 Affected")) {
                    Intent intent = new Intent(getContext(), InfoActivity.class);
                    intent.putExtra("disease", "Mumps");
                    startActivity(intent);
                } else if (marker.getTitle().equals("Measles Outbreak, 15 Affected") || marker.getTitle().equals("Measles Outbreak, 4 Affected")) {
                    Intent intent = new Intent(getContext(), InfoActivity.class);
                    intent.putExtra("disease", "Measles");
                    startActivity(intent);
                }
                /*
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
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                float[] result = new float[1];
                Location.distanceBetween(latLng.latitude, latLng.longitude, 40.108196, -88.227184, result);
                if (result[0] <= 500) {
                    uofi.showInfoWindow();
                    return;
                }
                Location.distanceBetween(latLng.latitude, latLng.longitude, 41.871213, -87.629018, result);
                if (result[0] <= 250) {
                    chicago.showInfoWindow();
                    return;
                }
                //Location.distanceBetween();
            }
        });
        Button primary = (Button) getActivity().findViewById(R.id.primary_location_button);
        primary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.112351, -88.242232), 13));
            }
        });
        Button secondary = (Button) getActivity().findViewById(R.id.secondary_location_button);
        secondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.102639, -87.936701), 13));
            }
        });
        Button tertiary = (Button) getActivity().findViewById(R.id.tertiary_location_button);
        tertiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.319898, -122.032830), 13));
            }
        });
    }
}
