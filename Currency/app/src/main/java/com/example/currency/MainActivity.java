package com.example.currency;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Button USDollar,YER,CAD,EUR,AUD,CLEAR;
TextView ans;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                USDollar=findViewById(R.id.USDollar);
                   YER=findViewById(R.id.YER);
                CAD=findViewById(R.id.CAD);
                EUR=findViewById(R.id.EUR);
                AUD=findViewById(R.id.AUD);
                CLEAR=findViewById(R.id.CLEAR);
                ans = findViewById(R.id.result);

        USDollar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float value=Float.parseFloat(ans.getText()+"");
                ans.setText(value/69.35+"");
            }
        });
        YER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float value=Float.parseFloat(ans.getText()+"");
                ans.setText(value/0.28+"");
            }
        });
        CAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float value=Float.parseFloat(ans.getText()+"");
                ans.setText(value/52.04+"");
            }
        });
        EUR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float value=Float.parseFloat(ans.getText()+"");
                ans.setText(value/78.36+"");
            }
        });
        AUD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float value=Float.parseFloat(ans.getText()+"");
                ans.setText(value/47.92+"");
            }
        });
        CLEAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ans.setText(null);
            }
        });
    }

}
