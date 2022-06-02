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

import java.util.List;

public class EditProfile extends AppCompatActivity {

    EditText username , dob , password;
    Button add , edit,delete,search ;
    RadioButton male , female ;
    String gender;
    String saveUser , saveDob , savePass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        username = findViewById(R.id.et_nameEp);
        dob = findViewById(R.id.et_dateEp);
        password = findViewById(R.id.et_passEp);
        male = findViewById(R.id.btn_male);
        female = findViewById(R.id.btn_female);
        add = findViewById(R.id.btn_add);
        edit = findViewById(R.id.btn_edit);
        delete = findViewById(R.id.btn_delete);
        search = findViewById(R.id.btn_search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHandler dbHandler = new DbHandler(getApplicationContext());
                saveUser = username.getText().toString();
                 List user = dbHandler.readAllInfo(saveUser);

                 if(user.isEmpty()){
                     Toast.makeText(EditProfile.this, "user null", Toast.LENGTH_SHORT).show();
                     username.setText(null);

                 }else {

                     Toast.makeText(EditProfile.this, "user found", Toast.LENGTH_SHORT).show();
                     username.setText(user.get(0).toString());
                     dob.setText(user.get(1).toString());
                     password.setText(user.get(2).toString());

                     if(user.get(3).toString().equals("Male")){
                         male.setChecked(true);
                     }else {
                         female.setChecked(true);
                     }
                 }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
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

                boolean status = dbHandler.updateInfo(saveUser,saveDob,savePass,gender);
                if(status){
                    Toast.makeText(EditProfile.this, "user Updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditProfile.this, "update fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHandler dbHandler = new DbHandler(getApplicationContext());
                saveUser = username.getText().toString();

                dbHandler.deleteInfo(saveUser);
                Toast.makeText(EditProfile.this, "User deleted", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext() , EditProfile.class);
                startActivity(i);

                username.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(true);
                female.setChecked(false);

            }
        });



    }
}