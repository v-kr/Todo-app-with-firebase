package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main4Activity extends AppCompatActivity {
    TextView textView;
    FirebaseAuth firebaseAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        textView = findViewById(R.id.text2);
        FirebaseUser firebaseAuth=FirebaseAuth.getInstance().getCurrentUser();
        String email=((FirebaseUser) firebaseAuth).getEmail();

        textView.setText(email);

    }
}
