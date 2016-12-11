package com.gogoflyapp.gogofly.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gogoflyapp.gogofly.R;

public class GoGoFlyActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void settleIn() {
        ImageView imageViewHome = (ImageView) findViewById(R.id.imageView_home);
        imageViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SetupActivity.class);
                startActivity(intent);
            }
        });

        ImageView imageViewUser = (ImageView) findViewById(R.id.imageView_user);
        imageViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserAccountActivity.class);
                startActivity(intent);
            }
        });

        ImageView imageViewTicket = (ImageView) findViewById(R.id.imageView_ticket);
        imageViewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TicketActivity.class);
                startActivity(intent);
            }
        });
    }
}
