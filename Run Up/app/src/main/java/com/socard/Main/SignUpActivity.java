package com.socard.Main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.socard.Base.BaseActivity;
import com.socard.Common.Constant;
import com.socard.R;
import com.socard.RunUpApplication;
import com.socard.Util.PhotoSelectDialog;
import com.socard.Util.ReqConst;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ui_imvProfile;
    private EditText ui_edtEmail, ui_edtPassword, ui_edtPassCon, ui_edtUserName, ui_edtGivenName, ui_edtPhoneNumber, ui_edtGender, ui_edtBirth, ui_edtState, ui_edtAddress;
    private Button ui_btnSignUp;
    private TextView ui_txvTitle;
    public static int _idx;

    private Uri _imageCaptureUri;
    private String _photoPath;
    private PhotoSelectDialog _photoSelectDialog;

    private String user_name, password, email, given_name, phone_number, gender, date_birth, marital_state, address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        loadLayout();
    }

    private void loadLayout(){

        ui_txvTitle = (TextView)findViewById(R.id.txv_title);
        ui_txvTitle.setText("Sign Up");



        ui_edtEmail = (EditText)findViewById(R.id.edtEmail);
        ui_edtPassword = (EditText)findViewById(R.id.edtPasword);
        ui_edtPassCon = (EditText)findViewById(R.id.edit_confpass);
        ui_edtUserName = (EditText)findViewById(R.id.user_name);
        ui_edtGivenName = (EditText)findViewById(R.id.edtGivenName);
        ui_edtPhoneNumber = (EditText)findViewById(R.id.edtphonenumber);
        ui_edtGender = (EditText)findViewById(R.id.edtGender);
        ui_edtGivenName = (EditText)findViewById(R.id.edtGivenName);
        ui_edtState = (EditText)findViewById(R.id.edtMarital);
        ui_edtAddress = (EditText)findViewById(R.id.edtAddress);
        ui_edtBirth = (EditText)findViewById(R.id.edtBirth);
        ui_btnSignUp = (Button)findViewById(R.id.btn_signup);
        ui_btnSignUp.setOnClickListener(this);

    }

    private boolean isVaild(){

        if (ui_edtUserName.getText().toString().length() == 0){

            showAlertDialog("Please Insert your UserName");
            return false;
        }else if (ui_edtEmail.getText().toString().length() == 0){
            showAlertDialog(getString(R.string.inputEmail));
            return false;
        }else if (ui_edtPassword.getText().toString().length() == 0){
            showAlertDialog(getString(R.string.inputPass));
            return false;
        }else if (ui_edtPassCon.getText().toString().length() == 0){
            showAlertDialog(getString(R.string.inputPassConf));
            return false;
        }else if (ui_edtPassword.getText().toString().length() < 6){
            showAlertDialog(getString(R.string.passlength));
            return false;
        }else if (!ui_edtPassword.getText().toString().equals(ui_edtPassCon.getText().toString())){
            showAlertDialog(getString(R.string.checkPass));
            return false;
        }else if (ui_edtPhoneNumber.getText().toString().length() == 0 ){
            showAlertDialog("Please Insert your PhoneNumber");
            return false;
        }else if (ui_edtGivenName.getText().toString().length() == 0 ){
            showAlertDialog("Please Insert your GivenName");
            return false;
        }else if (ui_edtBirth.getText().toString().length() == 0 ){
            showAlertDialog("Please Insert your Birthday");
            return false;
        }else if (ui_edtState.getText().toString().length() == 0 ){
            showAlertDialog("Please Insert your Marital State");
            return false;
        }else if (ui_edtAddress.getText().toString().length() == 0){
            showAlertDialog("Please Insert your Address");
            return false;
        }

        return true;
    }

    public void processRegister() {

        email = ui_edtEmail.getText().toString().trim();
        user_name = ui_edtUserName.getText().toString().trim().replace(" ", "%20");
        password = ui_edtPassword.getText().toString().trim();
        given_name = ui_edtGivenName.getText().toString().trim();
        phone_number = ui_edtPhoneNumber.getText().toString().trim();
        gender = ui_edtGender.getText().toString().trim();
        date_birth = ui_edtBirth.getText().toString().trim();
        marital_state = ui_edtState.getText().toString().trim();
        address = ui_edtAddress.getText().toString().trim();



        String url = ReqConst.SERVER_URL + ReqConst.REQ_SINGUP;

        try {

            String params = null;

            if (LoginActivity.w_fLatitude > 0){


                params  = String.format("/%s/%s/%s/%s/%s/%s/%s/%s/%s/%s/%s", user_name, password, given_name, email, phone_number, gender, date_birth, marital_state, address, LoginActivity.w_fLatitude, LoginActivity.w_fLongitude );
            }



            url += params;

            Log.d("params=====>", url);
        } catch (Exception ex) {

        }

        showProgress();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url , new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {

                Log.d("response====>", json);
                parseRegisterResponse(json);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                closeProgress();
                showAlertDialog(getString(R.string.register_fail));
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(Constant.VOLLEY_TIME_OUT,
                0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RunUpApplication.getInstance().addToRequestQueue(stringRequest, url);
    }

    public void parseRegisterResponse(String json){

        try{
            JSONObject response = new JSONObject(json);

            int result_code = response.getInt(ReqConst.RES_CODE);

            if(result_code == ReqConst.CODE_SUCCESS){


                _idx = response.getInt(ReqConst.RES_IDX);


                showToast("Sign Up Success");

                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));

                closeProgress();

            } else if (result_code == ReqConst.CODE_UNREGUSER) {

                closeProgress();
                showAlertDialog(getString(R.string.email_exist));

            }

        } catch (JSONException e){
            closeProgress();
            showAlertDialog(getString(R.string.register_fail));

            e.printStackTrace();
        }

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_signup:
                if (isVaild()){

                    processRegister();

                    Intent serviceIntent = new Intent(SignUpActivity.this, GpsService.class);
                    startService(serviceIntent);
                }

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
