package com.kangsoo.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class CameraActivity extends ActionBarActivity implements View.OnClickListener {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    Button btnCapture;
    Button btnSend;
    ImageView ivCaptureImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        InitializeControls();

    }

    private void InitializeControls() {
        btnCapture = (Button) findViewById(R.id.btnCameraCapture);
        btnSend = (Button) findViewById(R.id.btnCameraSend);
        ivCaptureImage = (ImageView) findViewById(R.id.ivCamera);

        btnCapture.setOnClickListener(this);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCameraCapture:
                if(hasCamera()){
                    launchCamera(v);
                }
                break;

            case R.id.btnCameraSend:
                break;

        }
    }

    //Check whenever has a camera
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }


    private void launchCamera(View v){

        Intent it = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if((requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) && (resultCode == RESULT_OK)){
            Bundle resultData = data.getExtras();
            Bitmap resultImage = (Bitmap)resultData.get("data");
            ImageView showImage = (ImageView) findViewById(R.id.ivCamera);
            showImage.setImageBitmap(resultImage);
        }
    }
}
