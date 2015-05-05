package com.example.anirudh_pc.anti_poaching;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class track_Map extends Activity implements OnMapReadyCallback {
    private LatLng LOCATION_LNMIIT=new LatLng(0,0);
    private JSONObject jsonObject;
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_track__map);

        MapFragment mapFragment = ((MapFragment) getFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
//Do networking

        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Get the location manager
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        //LocationListner
        LocationListener listener = new LocationListener() {

            public void onLocationChanged(final Location location) {
                System.out.println("Called Again!!!");
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                        postParameters.add(new BasicNameValuePair("type", "receiver"));

                        String response = null;
                        try {
                            response = SimpleHttpClient.executeHttpPost("http://192.168.137.1:8080/MyServletProject/Gps_Coordinates", postParameters);
                            String res = response.toString();
                            jsonObject = new JSONObject(res);
                            System.out.println("lon: " + jsonObject.getString("lon") + "lat: " + jsonObject.getString("lat"));

                            LOCATION_LNMIIT = new LatLng(Double.parseDouble(jsonObject.getString("lat")), Double.parseDouble(jsonObject.getString("lon")));
                            if (marker != null)
                                marker.remove();

                            System.out.println("Latitude: " + LOCATION_LNMIIT.latitude + " Longitude: " + LOCATION_LNMIIT.longitude);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getMessage();
                }
                // googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LOCATION_LNMIIT, 13));
                marker = googleMap.addMarker(new MarkerOptions()
                        .position(LOCATION_LNMIIT)
                        .title("Lat: " + LOCATION_LNMIIT.latitude + " Lon: " + LOCATION_LNMIIT.longitude));

                CameraPosition cameraPosition = CameraPosition.builder()
                        .target(LOCATION_LNMIIT)
                        .zoom(20)
                        .bearing(90)
                        .build();

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 1000, null);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
    }
}
