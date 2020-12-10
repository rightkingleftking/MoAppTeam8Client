package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import static com.google.android.gms.location.LocationSettingsRequest.*;

public class MapsFragment extends Fragment {
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    //대구시청
    private LatLng currentLocation = new LatLng(35.8714, 128.6019);
    private Marker currentMarker = null;
    private Marker[] superMarker = new Marker[MarketLocation.superLocation.length];
    private Marker[] tradiMarker = new Marker[MarketLocation.tradiLocation.length];

    private LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);

            List<Location> locationList = locationResult.getLocations();

            if (locationList.size() > 0) {
                LatLng lastLocation = currentLocation;
                currentLocation = new LatLng(locationList.get(locationList.size() - 1).getLatitude(), locationList.get(locationList.size() - 1).getLongitude());
                if(currentMarker != null)
                    currentMarker.remove();
                if(currentMarker == null || MarketLocation.calcDistance(currentLocation, lastLocation) >= 30){
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
                }
                currentMarker = mMap.addMarker(new MarkerOptions().position(currentLocation).title("현재위치"));
            }
        }
    };
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            while (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            }

            for(int i = 0; i < MarketLocation.superLocation.length; i++) {
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(MarketLocation.superLocation[i]);
                markerOption.title(MarketLocation.superName[i]);
                markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                superMarker[i] = mMap.addMarker(markerOption);
            }
            for(int i = 0; i < MarketLocation.tradiLocation.length; i++) {
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(MarketLocation.tradiLocation[i]);
                markerOption.title(MarketLocation.tradiName[i]);
                markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                tradiMarker[i] = mMap.addMarker(markerOption);
            }

            if(currentLocation != null) {
                mMap.moveCamera((CameraUpdateFactory.newLatLngZoom(currentLocation, 16)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
            }

            mFusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    }
                }
            });
            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1000)
                .setFastestInterval(500);


        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}