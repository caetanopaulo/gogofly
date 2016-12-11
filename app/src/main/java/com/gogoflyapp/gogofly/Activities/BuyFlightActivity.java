package com.gogoflyapp.gogofly.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.gogoflyapp.gogofly.R;

public class BuyFlightActivity extends GoGoFlyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_flight);

        settleIn();

        ImageView imageViewGo = (ImageView) findViewById(R.id.imageView_payment);
        imageViewGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to new Activity
                Intent intent = new Intent(getApplicationContext(), TicketActivity.class);
                startActivity(intent);
            }
        });
    }

}
