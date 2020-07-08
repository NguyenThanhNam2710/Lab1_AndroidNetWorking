package com.example.lab1.Bai3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab1.MainActivity;
import com.example.lab1.R;

import java.io.IOException;
import java.net.URL;

public class Bai3Activity extends AppCompatActivity implements View.OnClickListener, LoadImageTask.Listener {


    private ImageView imgAndroid;
    private TextView tvMessage;
    private Button btnLoad, btnHome;
    String IMAGE_URL = "https://www.w3schools.com/images/w3schools_green.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        imgAndroid = (ImageView) findViewById(R.id.imgAndroid);
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnHome = (Button) findViewById(R.id.btnHome);
        btnLoad.setOnClickListener(this);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bai3Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //Viết sự kiện click cho nút
    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.btnLoad:
                new LoadImageTask(this, this).execute(IMAGE_URL);
                break;
        }
    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {
        imgAndroid.setImageBitmap(bitmap);
        tvMessage.setText("Image Downloaded");
    }

    @Override
    public void onError() {
        tvMessage.setText("Error download image");
    }
}