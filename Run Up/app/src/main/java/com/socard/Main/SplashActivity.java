package com.socard.Main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.socard.Base.BaseActivity;
import com.socard.Common.Constant;
import com.socard.Model.PostEntity;

import com.socard.R;
import com.socard.RunUpApplication;
import com.socard.Util.ReqConst;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, Constant.SPLASH_TIME);



    }





}
