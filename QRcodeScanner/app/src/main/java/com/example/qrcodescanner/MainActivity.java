package com.example.qrcodescanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.DragStartHelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    CameraView cameraKitView;
    Button bdefect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        cameraKitView=findViewById(R.id.camera);
        bdefect=findViewById(R.id.btn_defect);

     bdefect.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             cameraKitView.start();
             cameraKitView.captureImage();

         }
     });
     cameraKitView.addCameraKitListener(new CameraKitEventListener() {
         @Override
         public void onEvent(CameraKitEvent cameraKitEvent) {

         }

         @Override
         public void onError(CameraKitError cameraKitError) {

         }

         @Override
         public void onImage(CameraKitImage cameraKitImage) {
             Bitmap bitmap=cameraKitImage.getBitmap();
             bitmap=Bitmap.createScaledBitmap(bitmap,cameraKitView.getWidth(),cameraKitView.getHeight(),false);
             cameraKitView.stop();

             rundetector(bitmap);

         }

         @Override
         public void onVideo(CameraKitVideo cameraKitVideo) {

         }
     });
    }

    private void rundetector(Bitmap bitmap) {
        FirebaseVisionImage image=FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionBarcodeDetectorOptions options=new FirebaseVisionBarcodeDetectorOptions.Builder().setBarcodeFormats(
                FirebaseVisionBarcode.FORMAT_QR_CODE,
                FirebaseVisionBarcode.FORMAT_PDF417
        )
                .build();

        FirebaseVisionBarcodeDetector detector= FirebaseVision.getInstance().getVisionBarcodeDetector(options);

        detector.detectInImage(image)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {
                        processResult(firebaseVisionBarcodes);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void processResult(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {
        for(FirebaseVisionBarcode item:firebaseVisionBarcodes){
            int value_type=item.getValueType();
            switch (value_type){
                case FirebaseVisionBarcode.TYPE_TEXT:
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setMessage(item.getRawValue());
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog dialog=builder.create();
                    dialog.show();
                }
                break;

                case FirebaseVisionBarcode.TYPE_URL:
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(item.getRawValue()));
                }
                break;
                case FirebaseVisionBarcode.TYPE_CONTACT_INFO:
                {
                    String info=new StringBuilder("Name:")
                            .append(item.getContactInfo().getName().getFormattedName())
                            .append("\n")
                            .append("Address:")
                            .append(item.getContactInfo().getEmails().get(0).getAddress())
                            .append("Email:")
                            .append(item.getContactInfo().getEmails().get(0).getAddress())
                            .toString();

                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setMessage(info);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog dialog=builder.create();
                    dialog.show();
                }
                break;
                default:
                    break;
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraKitView.start();
    }
}
