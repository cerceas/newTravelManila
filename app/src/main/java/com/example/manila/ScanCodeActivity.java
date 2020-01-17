package com.example.manila;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.Result;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanCodeActivity  extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private DatabaseHelperForUsers mydb;
    ZXingScannerView ScannerView;
    private final int REQUEST_PERMISSION_CAMERA =1;
    private int userID;
    private int LandmarkID;
    private String date;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);
        showPhoneStatePermission();
     userID=getIntent().getIntExtra("User",0);
     LandmarkID=getIntent().getIntExtra("Landmark",0);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String permissions[],
            int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CAMERA:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void showPhoneStatePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                showExplanation("Permission Needed", "Rationale", Manifest.permission.CAMERA, REQUEST_PERMISSION_CAMERA);
            } else {
                requestPermission(Manifest.permission.CAMERA, REQUEST_PERMISSION_CAMERA);
            }
        } else {
            Toast.makeText(this, "Permission (already) Granted!", Toast.LENGTH_SHORT).show();
        }
    }
    private void showExplanation(String title,
                                 String message,
                                 final String permission,
                                 final int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        requestPermission(permission, permissionRequestCode);
                    }
                });
        builder.create().show();
    }
    private void requestPermission(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(this,
                new String[]{permissionName}, permissionRequestCode);
    }

    @Override
    public void handleResult(Result result) {
        mydb = new DatabaseHelperForUsers(this);
        String strResult = result.getText();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        if(Integer.valueOf(strResult)>=1){
            mydb.insertDataInVisited(userID,LandmarkID,date);
            int i = mydb.DeleteDataUserList(userID, LandmarkID);
            if(i==1){
                System.out.println("Successfully Deleted");
            }
        }
        int points = Integer.parseInt(strResult);
       int Exp= mydb.getExp(userID) +points;
       int Level=mydb.getLevel(userID);
       if(Exp>=100){
           mydb.updateLevel(userID,Level+1);
           mydb.updateExp(userID,Exp-100);
           Toast.makeText(this, "Wow! You level up! LEVEL: " + mydb.getLevel(userID), Toast.LENGTH_SHORT).show();
       }else{
           mydb.updateExp(userID,Exp);
       }

        onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        ScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }

    @Override
    public void onBackPressed() {
       startActivity(new Intent(this,MainActivity.class));
    }
}
