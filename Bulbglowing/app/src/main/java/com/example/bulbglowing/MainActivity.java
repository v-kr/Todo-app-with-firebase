package com.example.bulbglowing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private CameraManager cameraManager;
    private ImageView ivOnOFF;
    private String frontCameraId,backCameraId;
    private Boolean isTorchOn;
    public void turnOnLight() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode((backCameraId), true);
                ivOnOFF.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void turnoffLight() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode((backCameraId), false);
                ivOnOFF.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivOnOFF = findViewById(R.id.btnimage);
        isTorchOn = false;

        ivOnOFF.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           try {
                                               if (isTorchOn) {
                                                   turnoffLight();
                                                   isTorchOn = false;
                                               } else {
                                                   turnOnLight();
                                                   isTorchOn = true;
                                               }
                                           } catch (Exception e) {
                                               e.printStackTrace();
                                           }
                                       }

                                   });
                Boolean isFlashAvailable = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if(!isFlashAvailable){
            AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
            alert.setTitle("FlashLightApp");
            alert.setMessage("flash not found");
            alert.setButton(DialogInterface.BUTTON_POSITIVE, "ok", (new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }));
            alert.show();
            return;

        }
        else{
            cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            try{
                backCameraId = cameraManager.getCameraIdList()[0];
            }
            catch (CameraAccessException e){
                e.printStackTrace();
            }
        }

    }

    protected void onStop() {
        super.onStop();
        if (isTorchOn) {
            turnoffLight();
        }
    }
    }
