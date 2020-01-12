package com.example.manila;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RewardsActivity extends AppCompatActivity {
    private DatabaseHelperForUsers mydb;
    private ImageView image;
    private TextView text;
    private int userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        mydb=new DatabaseHelperForUsers(this);
        String title = getIntent().getStringExtra("Title");
      String user = getIntent().getStringExtra("User");
      userID=mydb.getIDFromTableUser(user);
      image=findViewById(R.id.RewardsActivityImage);
      text=findViewById(R.id.RewardsActivityCode);
      CreateSomething(title);

    }

    public void CreateSomething(String title){
        if(title.equals("FREE MCFLOAT AND FRIEST")){
           image.setImageResource(R.drawable.friesanddrinks);
           text.setText(mydb.getRewardsCode(title));
        }else if(title.equals("FREE Girlfriend for 1 hour")){
           image.setImageResource(R.drawable.girlfriend);
            text.setText(mydb.getRewardsCode(title));
        }else if(title.equals("FREE GOSURF 50 at Globe")){
            image.setImageResource(R.drawable.globe);
            text.setText(mydb.getRewardsCode(title));
        }else if(title.equals("FREE BigBite at 7-11")){
           image.setImageResource(R.drawable.seveneleven);
            text.setText(mydb.getRewardsCode(title));
        }else if(title.equals("FREE Test me")){
           image.setImageResource(R.drawable.girlfriend);
            text.setText(mydb.getRewardsCode(title));
        }
    }
}
