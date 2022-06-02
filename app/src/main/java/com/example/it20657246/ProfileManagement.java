package com.example.it20657246;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.it20657246.Database.DbHandler;

public class ProfileManagement extends AppCompatActivity {

    EditText username , dob , password;
    Button add , updateProfile;
    RadioButton male , female ;
    String gender;
    String saveUser , saveDob , savePass ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username = findViewById(R.id.et_userName);
        dob = findViewById(R.id.et_dob);
        password = findViewById(R.id.et_pass);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        add = findViewById(R.id.btn_add);
        updateProfile = findViewById(R.id.btn_updatePro);

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext() , EditProfile.class);
                startActivity(i);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHandler dbHandler = new DbHandler(getApplicationContext());

                if(male.isChecked()){
                    gender = "Male";
                }else {
                    gender = "Female";
                }


                saveUser = username.getText().toString();
                saveDob = dob.getText().toString();
                savePass = password.getText().toString();

               Long newID = dbHandler.AddInfo(saveUser,saveDob,savePass,gender);
               Toast.makeText(ProfileManagement.this, "User Added SuccessFully" + newID , Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext() , EditProfile.class);
                startActivity(i);


            }
        });
    }
}