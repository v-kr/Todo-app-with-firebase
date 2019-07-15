package com.example.newfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText edemail,eduser,edpass1,edpass2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();


        b1=findViewById(R.id.signupbtn);
        edemail=findViewById(R.id.email);
        eduser=findViewById(R.id.Username);
        edpass1=findViewById(R.id.pass1);

        //edpass2=findViewById(R.id.pass2);
        DatabaseReference passwordconfirm=database.getReference("Password");
        DatabaseReference password=database.getReference("Password");
        String password1,password2;
        password1=edpass1.getText().toString();
        // password2=edpass2.getText().toString();
         password.setValue(password1);
        //  passwordconfirm.setValue(password2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=edemail.getText().toString();
                String pasword=edpass1.getText().toString();
                senddata();
                signup(email,pasword);
            }
        });

    }
    private void senddata(){
        Intent i=new Intent(MainActivity.this,Main2Activity.class);
        i.putExtra("user",eduser.getText().toString());
        i.putExtra("email",edemail.getText().toString());
        startActivity(i);


    }
    public void  signup(String email,String password){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "succesfull", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent i=new Intent(MainActivity.this,Main3Activity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();

                }
            }

        });

    }

}