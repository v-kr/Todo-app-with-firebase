package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView courselist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        courselist=findViewById(R.id.recycler);
        courselist.setLayoutManager(new LinearLayoutManager(this));
        String course[] = {"java","html","android","flutter","python","c","c++","PHP","JS","Machine learning"};
        courselist.setAdapter(new com.example.recyclerview.CourceAdapter(course));
    }
}
