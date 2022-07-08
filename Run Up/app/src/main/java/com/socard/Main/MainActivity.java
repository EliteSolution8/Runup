package com.socard.Main;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.socard.Adapter.PostAdapter;
import com.socard.Base.BaseActivity;
import com.socard.Common.Constant;
import com.socard.Model.PostEntity;
import com.socard.R;
import com.socard.RunUpApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recylerPost)
    RecyclerView lstPost;
    ArrayList<PostEntity> postModels  = new ArrayList<>();
    PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getPost();


        Log.d("photo url ===>", "https://picsum.photos/seed/" + getSha256Hash("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));


    }

    public void getPost(){

        String url = "https://jsonplaceholder.typicode.com/posts";


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

            JSONArray object = new JSONArray(json);

            Log.d("response==>" , json);


            for (int i = 0 ; i < object.length() ; i ++){

                JSONObject post = (JSONObject)object.get(i);

                PostEntity postEntity = new PostEntity();

                postEntity.setBody(post.getString("body"));
                postEntity.setUserId(post.getInt("userId"));
                postEntity.setTitle(post.getString("title"));
                postEntity.setId(post.getInt("id"));


                postModels.add(postEntity);
            }

            postAdapter = new PostAdapter(this, postModels);
            lstPost.setAdapter(postAdapter);
            lstPost.setLayoutManager(new LinearLayoutManager(this));


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
