package com.example.firebase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {
    public EditText emailId,password;
    Button btnlogin,signup,forgot;
    FirebaseAuth mFirebaseAuth;
//    private FirebaseAuth.AuthStateListener mAuthStatelistener;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth=FirebaseAuth.getInstance();
        emailId=findViewById(R.id.editText2);
        password=findViewById(R.id.editText3);
        btnlogin=findViewById(R.id.button2);
        signup=findViewById(R.id.button3);
        forgot=findViewById(R.id.button4);
        pd=new ProgressDialog(this);
        if(mFirebaseAuth.getCurrentUser()!=null){
            Intent i=new Intent(Main2Activity.this,MainActivity.class);
            startActivity(i);

        }
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(emailId.getText().toString()!=null){
                    if(password.getText().toString().length()>=7){
                        String mail=emailId.getText().toString();
                        String pass=password.getText().toString();
                        login(mail,pass);
                        pd.setMessage("loading...");
                        pd.show();

                    }
                    else {
                        Toast.makeText(Main2Activity.this,"enter password",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Main2Activity.this,"enter email",Toast.LENGTH_SHORT).show();

                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intSignUp=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intSignUp);
            }
        });

    }
    public void login(String email,String password){
        mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();

                    Intent i=new Intent(Main2Activity.this,MainActivity.class);
                    startActivity(i);
                    pd.dismiss();
                }
                else{
                    Toast.makeText(Main2Activity.this,"log failed",Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });
    }
}