package com.socard.Main;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.socard.Common.Constant;
import com.socard.RunUpApplication;
import com.socard.Util.ReqConst;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class GpsService extends Service {

    final Handler mHandler = new Handler();
    Timer mTimer = new Timer();

    public LocationManager myLocationManager;
    public boolean w_bGpsEnabled, w_bNetworkEnabled;

    public  static double w_fLatitude = 0;
    public  static double w_fLongitude = 0;

    public static int MY_PERMISSION_LOCATION = 123;

    public GpsService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("Gps Info Service", "Service start to run.");

        // scheduling the current position updating task (Asynchronous)
        mTimer.schedule(doAsynchronousTask, 0, Constant.LOCATION_UPDATE_TIME);

        return super.onStartCommand(intent, flags, startId);
    }

    TimerTask doAsynchronousTask = new TimerTask() {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {


                    initLocationListener();
                    // send receivedLocation to server

                    String url = ReqConst.SERVER_URL + ReqConst.REQ_UPDATEPOS;

                    String params = String.format("/%d/%s/%s",  SignUpActivity._idx, w_fLatitude, w_fLongitude);

                    url += params;

                    Log.d("Url ===>", url);

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String json) {

                            parseLoginResponse(json);

                            Log.d("response==>", json);
                        }
                    }, new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {


                        }
                    });

                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(Constant.VOLLEY_TIME_OUT,
                            0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    RunUpApplication.getInstance().addToRequestQueue(stringRequest, url);
                }
            });
        }
    };


    public void parseLoginResponse(String json){

        try{

            JSONObject object = new JSONObject(json);

            Log.d("response==>" , json);


            int result_code = object.getInt(ReqConst.RES_CODE);

            if(result_code == ReqConst.CODE_SUCCESS){



            }else {


            }
        }catch (JSONException e){

            e.printStackTrace();

        }
    }

    public void initLocationListener() {

        LocationManager myLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean w_bGpsEnabled = myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean w_bNetworkEnabled = myLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!w_bGpsEnabled && !w_bNetworkEnabled) {
             //

            //
            setMyLocation(null);
        } else {
            tryGetLocation();
        }

    }




    private void tryGetLocation() {
        LocationManager myLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        w_bGpsEnabled = myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        w_bNetworkEnabled = myLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (w_bNetworkEnabled) {
            Location locationNet = myLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (locationNet != null) {
                setMyLocation(locationNet);
                return;
            }
        }
        if (w_bGpsEnabled) {

            Location locationGps = myLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGps != null) {
                setMyLocation(locationGps);
                return;
            }
        }

        if (w_bNetworkEnabled) {
            myLocationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, m_myLocationListener, null);
        }
        if (w_bGpsEnabled) {
            myLocationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, m_myLocationListener, null);
        }
    }

    private void setMyLocation(Location p_location) {

        if (p_location != null) {
            w_fLatitude = p_location.getLatitude();
            w_fLongitude = p_location.getLongitude();
        }

        System.out.println("Latitude: " + String.valueOf(w_fLatitude) + "Longitude: " + String.valueOf(w_fLongitude));

    }


    LocationListener m_myLocationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }


        @Override
        public void onLocationChanged(Location location) {
            setMyLocation(location);
        }
    };

}
