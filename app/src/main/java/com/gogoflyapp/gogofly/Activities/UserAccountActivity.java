package com.gogoflyapp.gogofly.Activities;

import android.os.Bundle;

import com.gogoflyapp.gogofly.R;

public class UserAccountActivity extends GoGoFlyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        // Header Stuff
        settleIn();
    }
}
