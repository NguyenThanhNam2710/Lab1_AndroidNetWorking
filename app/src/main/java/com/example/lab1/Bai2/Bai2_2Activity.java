package com.example.lab1.Bai2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab1.MainActivity;
import com.example.lab1.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Bai2_2Activity extends AppCompatActivity implements View.OnClickListener {


    private ImageView imgAndroid;
    private TextView tvMessage;
    private Button btnLoad, btnHome;
    private ProgressDialog progressDialog;
    private String url = "http://i64.tinypic.com/28vaq8k.png";
    private Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2_2);

        imgAndroid = (ImageView) findViewById(R.id.imgAndroid);
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnHome = (Button) findViewById(R.id.btnHome);
        btnLoad.setOnClickListener(this);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bai2_2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //Tải ảnh online
    private Bitmap downLoadBitmap(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Handler messageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String message = bundle.getString("message");
            tvMessage.setText(message);
            imgAndroid.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }
    };

    //Viết sự kiện click cho nút
    @Override
    public void onClick(View v) {
        progressDialog = ProgressDialog.show(Bai2_2Activity.this, "", "Downloading...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //tải về img
                bitmap = downLoadBitmap(url);
                //tạo nơi gửi giữa thread
                Message msg = messageHandler.obtainMessage();
                //khởi tạo dữ liệu gửi
                Bundle bundle = new Bundle();
                String threadMessage = "Image downloaded";
                bundle.putString("message", threadMessage);
                msg.setData(bundle);
                //gửi dl khi chạy xong dialog
                messageHandler.sendMessage(msg);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}