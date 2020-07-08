package com.example.lab1.Bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab1.Bai2.Bai2Activity;
import com.example.lab1.MainActivity;
import com.example.lab1.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Bai1Activity extends AppCompatActivity implements View.OnClickListener {


    private ImageView imgAndroid;
    private TextView tvMessage;
    private Button btnLoad, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);

        imgAndroid = (ImageView) findViewById(R.id.imgAndroid);
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnHome = (Button) findViewById(R.id.btnHome);
        btnLoad.setOnClickListener(this);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bai1Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //Tải ảnh online
    private Bitmap loadImageFromNetWork(String link) {
        URL url;
        Bitmap bmp = null;
        try {
            url = new URL(link);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    //Viết sự kiện click cho nút
    @Override
    public void onClick(View v) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = loadImageFromNetWork("http://i64.tinypic.com/28vaq8k.png");
                imgAndroid.post(new Runnable() {
                    @Override
                    public void run() {
                        tvMessage.setText("Image Downloaded");
                        imgAndroid.setImageBitmap(bitmap);
                    }
                });
            }
        });
        thread.start();
    }
}