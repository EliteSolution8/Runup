package com.socard.Base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.socard.R;


public class BaseActivity extends AppCompatActivity implements Handler.Callback {

    public Context _context;
    private ProgressDialog _progressDlg;

    public Handler _handler = null;

    KProgressHUD kProgressHUD;

    public boolean _isEndFlag; // dobule click back button to kill the app

    public static final int BACK_TWO_CLICK_DELAY_TIME = 3000; //ms

    public Runnable _exitRunner = new Runnable() {
        @Override
        public void run() {
            _isEndFlag = false;
        }
    };

    protected void setupActionBar(){

    }

    public void showToast(String toast_string){
        Toast.makeText(this,toast_string,Toast.LENGTH_SHORT).show();
    }

    // show progressDialog
    public void showProgress(String strMsg,boolean cancellable){
        closeProgress();
        _progressDlg = ProgressDialog.show(this,null,null);
        _progressDlg.setMessage(strMsg);
        _progressDlg.setCancelable(cancellable);
        _progressDlg.setContentView(R.layout.progressbar);
        _progressDlg.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        _progressDlg.getWindow().setDimAmount(0.6f);
    }

    public void showKProgress(){

        closeKProgress();
        kProgressHUD = KProgressHUD.create(this);
        kProgressHUD
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel("Please wait")
//                .setDetailsLabel("Downloading data")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }
    public void closeKProgress(){

        if (kProgressHUD == null){
            return;
        }
        kProgressHUD.dismiss();
    }


    public void showProgress(){

        showProgress(_context.getString(R.string.loading), true);

    }

    public void closeProgress(){
        if (_progressDlg == null){
            return;
        }

        _progressDlg.dismiss();
        _progressDlg = null;

    }

    public void showAlertDialog(String msg){

        AlertDialog alertDialog = new AlertDialog.Builder(_context).create();

        alertDialog.setTitle(_context.getString(R.string.app_name));
        alertDialog.setMessage(msg);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, _context.getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what){
            default:
                break;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _context = this;
        _handler = new Handler(this);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onExit() {

        if (_isEndFlag == false) {

            Toast.makeText(this, getString(R.string.str_back_one_more_end),
                    Toast.LENGTH_SHORT).show();
            _isEndFlag = true;

            _handler.postDelayed(_exitRunner, BACK_TWO_CLICK_DELAY_TIME);

        } else if (_isEndFlag == true) {


            finish();
        }
    }


}
