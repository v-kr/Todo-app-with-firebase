package com.example.combine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PHONECALL=1;
   private EditText ans;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.btn1);
        b2 = (Button)findViewById(R.id.btn2);
        b3 = (Button)findViewById(R.id.btn3);
        b4 = (Button)findViewById(R.id.btn4);
        b5 = (Button)findViewById(R.id.btn5);
        b6 = (Button)findViewById(R.id.btn6);
        b7 = (Button)findViewById(R.id.btn7);
        b8 = (Button)findViewById(R.id.btn8);
        b9 = (Button)findViewById(R.id.btn9);
        b10 = (Button)findViewById(R.id.btn0);
        b11 = (Button)findViewById(R.id.btnhash);
        b12 = (Button)findViewById(R.id.btnstar);
        b14 = (Button)findViewById(R.id.whatsapp);
        b15 = (Button)findViewById(R.id.message);

        b13=findViewById(R.id.phone);
        ans=findViewById(R.id.edit);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans.setText(ans.getText()+"1");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans.setText(ans.getText()+"2");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans.setText(ans.getText()+"3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans.setText(ans.getText()+"4");
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans.setText(ans.getText()+"5");
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans.setText(ans.getText()+"6");
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans.setText(ans.getText()+"7");
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans.setText(ans.getText()+"8");
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans.setText(ans.getText()+"9");
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans.setText(ans.getText()+"0");
            }
        });

        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opencall();
            }
            public void opencall(){
                String number=ans.getText().toString();
                if(number.trim().length()>0){
                    if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},PHONECALL);

                    }
                    else{
                        String dial="tel:"+number;
                        Intent i=new Intent(Intent.ACTION_CALL);
                        i.setData(Uri.parse(dial));
                        startActivity(i);
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Enter phone number", Toast.LENGTH_SHORT).show();
                }

            }
            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                if(requestCode==PHONECALL){
                    if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        opencall();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Permission denied",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num =ans.getText().toString();
                Uri uri=Uri.parse("smsto:"+num);
                Intent sendintent=new Intent(Intent.ACTION_SENDTO,uri);
                sendintent.putExtra(Intent.EXTRA_TEXT,"vivek");
                startActivity(sendintent);
            }
        });
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num =ans.getText().toString();
                Uri uri=Uri.parse("smsto:"+num);
                Intent sendintent=new Intent(Intent.ACTION_SENDTO,uri);
                sendintent.putExtra(Intent.EXTRA_TEXT,"vivek");
                sendintent.setPackage("com.whatsapp");
                startActivity(sendintent);
            }
        });



    }
}
