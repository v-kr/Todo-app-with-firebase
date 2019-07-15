package com.example.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RecyclerView courselist;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        courselist=findViewById(R.id.recycler);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        courselist.setLayoutManager(new GridLayoutManager(this,2));
        String course[] = {"Vivek","Gulshan","Asif","Yuvraj","Garvit","Jayant","Mehul","Ranvijay","Shobit"};
        courselist.setAdapter(new com.example.layout.CourceAdapter(course));
    }
}
