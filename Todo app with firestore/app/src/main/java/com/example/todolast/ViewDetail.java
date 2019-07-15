package com.example.todolast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolast.Model.Todo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewDetail extends AppCompatActivity {

    Button bupdate,bdelete;
    EditText a1,a2;
    String titlesend,descsend;
    DatabaseReference mdatabaseref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);

        mdatabaseref= FirebaseDatabase.getInstance().getReference();

        bupdate=findViewById(R.id.btn2);
        bdelete=findViewById(R.id.btn3);
        a1=findViewById(R.id.e3);
        a2=findViewById(R.id.e4);

        Intent i = getIntent();
        String getTitle=i.getStringExtra("title");
        String getDesc=i.getStringExtra("desc");
        final String id=i.getStringExtra("id");
        Toast.makeText(this,"",Toast.LENGTH_SHORT).show();

        a1.setText(getTitle);
        a2.setText(getDesc);

        bupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(id);
            }
        });
        bdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(id);
            }
        });
    }
    public void update(String id){
        titlesend =a1.getText().toString();
        descsend=a2.getText().toString();
        Todo todo=new Todo(id,titlesend,descsend);
        mdatabaseref.child("notes").child(id).setValue(todo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(ViewDetail.this,"notes Updated",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }
    public void delete(String id){
        mdatabaseref.child("notes").child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ViewDetail.this,"Notes Update",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }
}
