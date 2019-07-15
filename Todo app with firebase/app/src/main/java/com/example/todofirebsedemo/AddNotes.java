package com.example.todofirebsedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.todofirebsedemo.Model.Todo;

public class AddNotes extends AppCompatActivity {
    FirebaseFirestore db;
    Button addbtn;
    EditText editTexttitle,editTextDesc;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        //collection/reference\//
        db=FirebaseFirestore.getInstance();
        final CollectionReference collection=db.collection("notebook");
        editTexttitle=findViewById(R.id.e1);
        editTextDesc=findViewById(R.id.e2);
        addbtn=findViewById(R.id.btn1);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=editTexttitle.getText().toString();
                String desc=editTextDesc.getText().toString();
                Todo item=new Todo(title,desc);
                collection.add(item)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(AddNotes.this,"Netes",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddNotes.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
    }/////
}
