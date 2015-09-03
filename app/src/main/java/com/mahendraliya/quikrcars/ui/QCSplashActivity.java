package com.mahendraliya.quikrcars.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mahendraliya.quikrcars.R;
import com.mahendraliya.quikrcars.utils.Constants;


public class QCSplashActivity extends QCBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(QCSplashActivity.this, QCMainActivity.class);
                startActivity(intent);
                finish();
            }
        }, Constants.SPLASH_DURATION);
    }

}
