package com.example.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    RecyclerView courselist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] images={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};
        courselist=findViewById(R.id.recycle);
        courselist.setLayoutManager(new GridLayoutManager(this,2));
        String course[] = {"java","c","c++","Python","JS","PHP","PPL"};
        courselist.setAdapter(new CourceAdapter(images,course));
    }
}
