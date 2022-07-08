package com.socard.Main;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.socard.Base.BaseActivity;
import com.socard.Common.Constant;
import com.socard.R;
import com.socard.RunUpApplication;
import com.socard.Util.ReqConst;
import com.socard.Widget.CustomButton;
import com.socard.Widget.CustomEditText;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {



    public LocationManager myLocationManager;
    public boolean w_bGpsEnabled, w_bNetworkEnabled;

    public  static double w_fLatitude = 0;
    public  static double w_fLongitude = 0;

    public static int MY_PERMISSION_LOCATION = 123;
    boolean GpsStatus = false ;
    Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);



        loadLayout();
    }


    public void checkGpsStatus(){

        myLocationManager = (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        GpsStatus = myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }



    private void loadLayout(){


        checkGpsStatus();

        if (GpsStatus){

            marshmallowGPSPremissionCheck();

            if (location != null){

                startActivity(new Intent(this, MainActivity.class));
                finish();

            }
        }else {

            showAlert();
        }
    }







    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            onExit();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }



    private void marshmallowGPSPremissionCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSION_LOCATION);
        } else {
            //   gps functions.
            tryGetLocation();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSION_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //  gps functionality
            tryGetLocation();
        }
    }



    private void tryGetLocation() {
        LocationManager myLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        w_bGpsEnabled = myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        w_bNetworkEnabled = myLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
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

            location = p_location;
        }

        System.out.println("Latitude: " + String.valueOf(w_fLatitude) + "Longitude: " + String.valueOf(w_fLongitude));

    }

    private void showAlert(){

        new MaterialDialog.Builder(this)
                .title(R.string.warning)
                .content(R.string.msg_location_service_enable_confirm)
                .positiveText("Yes")
                .positiveColorRes(R.color.primary)
                .negativeText("No")
                .negativeColorRes(R.color.red_light)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        startActivity(new Intent(
                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                    }
                })
                .show();
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
