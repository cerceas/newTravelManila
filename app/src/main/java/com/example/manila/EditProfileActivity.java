package com.example.manila;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity {
    private DatabaseHelperForUsers mydb;
    private EditText FirstName;
    private EditText LastName;
    private EditText email;
    private EditText Pass;
    int userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        FirstName=findViewById(R.id.UpdateFirstName);
        LastName=findViewById(R.id.UpdateLastName);
        email=findViewById(R.id.UpdateEmail);
        Pass=findViewById(R.id.UpdatePassword);
        userID=getIntent().getIntExtra("User",0);
    }

    public void UpdateProfile(View view){
        mydb = new DatabaseHelperForUsers(this);
        mydb.UpdateProfileData(userID,FirstName.getText().toString(),LastName.getText().toString(),email.getText().toString(),Pass.getText().toString());
        onBackPressed();

    }
}
