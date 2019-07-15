package com.example.newfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    EditText edusername,edemail,edphone,edcity,edaddress;
    Button btnsubmit;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setTitle("Signup");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDisplayDate=findViewById(R.id.date);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(Main2Activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month=month+1;
                String date=month+"/"+day+"/"+year;
                mDisplayDate.setText(date);

            }
        };

        edemail=findViewById(R.id.email2);
        edusername=findViewById(R.id.Username);
        edphone=findViewById(R.id.phonenumber2);
        edcity=findViewById(R.id.city2);
        btnsubmit=findViewById(R.id.submitbtn2);
        edaddress=findViewById(R.id.address);
//        edpostal=findViewById(R.id.postalcode);

        edusername.setEnabled(false);
        edemail.setEnabled(false);

        final String user =getIntent().getStringExtra("user");
        final String email =getIntent().getStringExtra("email");
        edusername.setText(user);
        edemail.setText(email);


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                writeNewUser(user,email,edphone.getText().toString().trim());
                Intent i =new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(i);

            }
        });
    }
    private void writeNewUser(String username, String email,String phone) {
        String userId=mDatabase.push().getKey();
        User user = new User(username,email,phone);
        // User.child(id).setValue(user);
        mDatabase.child(userId).setValue(user);
    }
    private void senddata(){
        Intent i=new Intent(Main2Activity.this,Main3Activity.class);
        i.putExtra("user",edusername.getText().toString());
        i.putExtra("email",edemail.getText().toString());
        i.putExtra("Phone",edphone.getText().toString());
        i.putExtra("City",edcity.getText().toString());
        i.putExtra("Address",edaddress.getText().toString());
        startActivity(i);


    }
}