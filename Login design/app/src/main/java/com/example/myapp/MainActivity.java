package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    Button loginBtn;
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password=findViewById(R.id.password);
        loginBtn=findViewById(R.id.btn1);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailid,pass;
                emailid = email.getText().toString();
                pass = password.getText().toString();
                Toast.makeText(getApplicationContext(),"login successfull"+emailid+pass,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
