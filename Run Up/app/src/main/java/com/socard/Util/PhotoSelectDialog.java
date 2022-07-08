package com.socard.Util;

/**
 * Created by HGS on 12/20/2015.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.socard.R;


public class PhotoSelectDialog extends Dialog {

    private TextView ui_txvCamera, ui_txvAlbum;
    private Button ui_btnCancel;

    private View.OnClickListener cameraListener;
    private View.OnClickListener albumListener;
    private View.OnClickListener cancelListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.alert_photo_select);

        ui_txvCamera = (TextView)findViewById(R.id.txv_camera);
        ui_txvAlbum = (TextView)findViewById(R.id.txv_album);
        ui_btnCancel = (Button)findViewById(R.id.btn_alertCancel);



        if (cameraListener != null && albumListener != null && cancelListener != null) {
            ui_txvCamera.setOnClickListener(cameraListener);
            ui_txvAlbum.setOnClickListener(albumListener);
            ui_btnCancel.setOnClickListener(cancelListener);
        }else if(cameraListener != null && albumListener == null && cancelListener == null){
            ui_txvCamera.setOnClickListener(cameraListener);
        }else if(cameraListener == null && albumListener != null && cancelListener == null){
            ui_txvAlbum.setOnClickListener(albumListener);
        }else if(cameraListener == null && albumListener == null && cancelListener != null){
            ui_btnCancel.setOnClickListener(cancelListener);
        }
    }


    public PhotoSelectDialog(Context context, View.OnClickListener cameraListener,
                             View.OnClickListener albumListener, View.OnClickListener cancelListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);

        this.cameraListener = cameraListener;
        this.albumListener = albumListener;
        this.cancelListener = cancelListener;
    }
}
