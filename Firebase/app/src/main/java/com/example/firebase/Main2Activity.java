package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {
    EditText email,password;
    Button signup,forgetpassword;
    private FirebaseAuth fireBaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.blogin);
        forgetpassword=findViewById(R.id.forget);
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i =new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(i);
            }

        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = password.getText().toString();
                String mail = email.getText().toString();
                if(mail.length()>=6 && pass.length()>=6)
                {
                    signUp(mail,pass);
                }
                else
                {
                    return;
                }
            }
        });
    }
    void signUp(String mail,String password)
    {
        fireBaseAuth = FirebaseAuth.getInstance();
        fireBaseAuth.createUserWithEmailAndPassword(mail,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Main2Activity.this, "SignUp Successfull", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(Main2Activity.this, "ERROR!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
