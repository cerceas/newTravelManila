package com.example.manila;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    EditText etName, etPassword;
    Button btnLogin;
    private DatabaseHelperForUsers mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        etName = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        if (getIntent().getBooleanExtra("LOGOUT", false)) {
            finish();
        }
    }

    public void login(View view) {
        if(etName.getText().toString().isEmpty()||etPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Fill all the TextField", Toast.LENGTH_SHORT).show();
            return;
        }
        mydb= new DatabaseHelperForUsers(this);
       Boolean check= mydb.CheckLogin(etName.getText().toString(),etPassword.getText().toString());
        if(check){
            mydb = new DatabaseHelperForUsers(view.getContext());
            Cursor res = mydb.getAllDataForLandmarks();
            if (res.getCount() == 0) {
                CreateData();
            }
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("User", etName.getText().toString());
            startActivity(intent);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "You fail to log in", Toast.LENGTH_SHORT).show();
        }

    }

    public void LoginRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
    public void CreateData(){
        mydb.insertDataInLandmarks("National Planetarium","The National Museum has reopened the National Planetarium. It houses the Philippines’ Ethno astronomy, which shows how planets and stars have guided the country in sea navigation, agriculture, fishing and “the right timing of celebrating life ”. \n\n Location: Dr. Jose P. Rizal Park, Padre Burgos Ave, Ermita  \n\n Entrance Fee: Free for the gallery, P40.00 for scheduled shows.  \n\n Open Hours: Open Tuesdays to Sundays ");
        mydb.insertDataInLandmarks("National Museum of Natural History","The majestic Tree of Life and 12 galleries highlight the rich biodiversity of the Philippines, putting it on par with the Amazon and Madagascar.  \n\n Location: Teodoro F. Valencia Circle, Ermita P. Burgos Drive, Rizal Park  \n\n Entrance Fee: Free  \n\n Open Hours: Open Tuesdays to Sundays ");
        mydb.insertDataInLandmarks("National Museum of Anthropology","The former Museum of the Filipino People shows you what life was like from decades to centuries past. Check out the artefacts such as weapons, household tools, and musical instruments from different ethnicities.  \n\n Location: Padre Burgos Ave, Ermita Manila.  \n\n Entrance Fee: Free \n" +
                "Open Hours: Open Tuesdays to Sundays \n");
        mydb.insertDataInLandmarks("National Museum of Fine Arts","Housing the famous Spoliarium by Juan Luna, the museum also showcases works from the colonial Spanish period by Félix Resurreccion Hidalgo and Fernando Amorsolo. More contemporary paintings by Carlos “Botong” Francisco and Vicente Manansala are also on display. If you want to explore the country and its history through the eyes of its visual artists, the Fine Arts Museum is top priority. Housed in the old Congress building, it transports you to Manila’s boom pre-World War II. \n\n Location: Padre Burgos Ave, Ermita Manila. \n\n Entrance Fee: Free \n\n Open Hours: Open Tuesdays to Sundays");
        mydb.insertDataInLandmarks("Museo ni Jose Rizal","Test 2 here");
        mydb.insertDataInLandmarks("Testing test2","Test 2 here");
        mydb.insertDataInLandmarks("Testing test2","Test 2 here");
        mydb.insertDataInLandmarks("Testing test2","Test 2 here");

        mydb.insertDataInRewards("FREE MCFLOAT AND FRIEST","CODE: @1203939");
        mydb.insertDataInRewards("FREE Girlfriend for 1 hour","CODE: @walang ganun tanga HAHAHA");
        mydb.insertDataInRewards("FREE GOSURF 50 at Globe","CODE: @212312");
        mydb.insertDataInRewards("FREE BigBite at 7-11","CODE: @111");
        mydb.insertDataInRewards("FREE Test me","CODE: @2");
        mydb.insertDataInRewards("FREE Test me","CODE: @4");
        mydb.insertDataInRewards("FREE Test me","CODE: @3");

    }

}

