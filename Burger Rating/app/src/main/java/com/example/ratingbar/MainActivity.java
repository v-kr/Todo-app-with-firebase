package com.example.ratingbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1;
    EditText editText;
    ImageView imageView;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=findViewById(R.id.btnSubmit);
        editText=findViewById(R.id.edittext);
        imageView=findViewById(R.id.image1);
        ratingBar=findViewById(R.id.ratingBar);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Thanks for Feedback",Toast.LENGTH_SHORT).show();
                editText.setText(null);
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                String rating=String.valueOf(ratingBar.getRating());
                Toast.makeText(MainActivity.this,rating,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
