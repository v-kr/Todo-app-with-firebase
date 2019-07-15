package com.example.todolast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.todolast.Model.Todo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addNotes extends AppCompatActivity {
    EditText edTitle,edDesc;
    Button btnSubmit;
    DatabaseReference mDatabaseRef;
    String title,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        mDatabaseRef= FirebaseDatabase.getInstance().getReference();
        edTitle=findViewById(R.id.e1);
        edDesc=findViewById(R.id.e2);
        btnSubmit=findViewById(R.id.btn1);


        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                title=edTitle.getText().toString();
                desc=edDesc.getText().toString();
                senddata(title,desc);
                startActivity(new Intent(addNotes.this,MainActivity.class));
            }
        });

    }
    public void senddata(String strTitle,String strDesc){
        String UserId=mDatabaseRef.push().getKey();
        Todo todo=new Todo(strTitle,strDesc,UserId);
        mDatabaseRef.child("notes").child(UserId).setValue(todo);

    }
}
