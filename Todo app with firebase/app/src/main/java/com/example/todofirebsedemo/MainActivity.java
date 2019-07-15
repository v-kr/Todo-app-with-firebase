package com.example.todofirebsedemo;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.todofirebsedemo.AddNotes;
import com.example.todofirebsedemo.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.todofirebsedemo.Adapter.CourceAdapter;
import com.example.todofirebsedemo.Model.Todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ///FloatingActionButton floatingActionButton;
////array list
    List<Todo> list=new ArrayList<>();
    Context context;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db=FirebaseFirestore.getInstance();
        db.collection("notebook");


        recyclerView=findViewById(R.id.recycler);
        final CourceAdapter notesAdapter=new CourceAdapter(list,this);
        recyclerView.setAdapter(notesAdapter);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final CourceAdapter adapter=new CourceAdapter(list,this);
        recyclerView.setAdapter(adapter);
        //////////////////////////////////////////////////
        db.collection("notebook").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> l=queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d:l){
                                Todo todo=d.toObject(Todo.class);
                                list.add(todo);

                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });



//        mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){//value layega datasnapshot////pojo hamesha model k aandar
//                    Todo listdata=dataSnapshot1.getValue(Todo.class);
//                    list.add(listdata);
//                }
//                notesAdapter.notifyDataSetChanged();
//
//            }




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(MainActivity.this, AddNotes.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

