package com.socard.Main;

import android.os.Bundle;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideExtension;
import com.socard.Adapter.PostAdapter;
import com.socard.Base.BaseActivity;
import com.socard.Common.Constant;
import com.socard.Model.PostEntity;
import com.socard.R;
import com.socard.RunUpApplication;
import com.socard.Widget.CustomTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import butterknife.BindView;
import butterknife.ButterKnife;

public class SuccessActivity extends BaseActivity {


    @BindView(R.id.imgPost) ImageView imagePost;
    @BindView(R.id.txvtitle) CustomTextView txtTitle;
    @BindView(R.id.txvEmail) CustomTextView txtEmail;
    @BindView(R.id.txvPhone) CustomTextView txtPhone;
    @BindView(R.id.txvWebsite) CustomTextView txvWebsite;
    @BindView(R.id.txvName) CustomTextView txvName;

    String id, email, userName;
    PostEntity postEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        ButterKnife.bind(this);


        loadLayout();

    }

    private void loadLayout(){

        postEntity = (PostEntity)getIntent().getSerializableExtra("post");

        getPost(String.valueOf(postEntity.getUserId()));

    }

    public void getPost(String id){

        String url = "https://jsonplaceholder.typicode.com/users/" + id;


        showProgress();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {

                Log.d("response==>", json);

                parsePostResponse(json);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                closeProgress();
//                showAlertDialog(getString(R.string.error));
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(Constant.VOLLEY_TIME_OUT,
                0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RunUpApplication.getInstance().addToRequestQueue(stringRequest, url);

    }

    public void parsePostResponse(String json){

        try{

            Log.d("response==>" , json);

            JSONObject post = new JSONObject(json);

            txtTitle.setText(postEntity.getTitle());
            txvWebsite.setText(post.getString("website"));
            txvName.setText(post.getString("name"));
            txtPhone.setText(post.getString("phone"));
            txtEmail.setText(post.getString("email"));

            Glide.with(this).load("https://picsum.photos/seed/" + getSha256Hash(postEntity.getTitle())+"/128/128").into(imagePost);


            closeProgress();

        }catch (JSONException e){
            closeProgress();
            e.printStackTrace();

        }
    }

    public static String getSha256Hash(String password) {
        try {
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }
            digest.reset();
            return bin2hex(digest.digest(password.getBytes()));
        } catch (Exception ignored) {
            return null;
        }
    }

    private static String bin2hex(byte[] data) {
        StringBuilder hex = new StringBuilder(data.length * 2);
        for (byte b : data)
            hex.append(String.format("%02x", b & 0xFF));
        return hex.toString();
    }
}
