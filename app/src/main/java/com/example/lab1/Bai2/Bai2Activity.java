package com.example.lab1.Bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.lab1.Bai1.Bai1Activity;
import com.example.lab1.MainActivity;
import com.example.lab1.R;

public class Bai2Activity extends Activity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Bai2Activity.this, Bai2_2Activity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}