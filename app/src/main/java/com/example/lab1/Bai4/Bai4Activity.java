package com.example.lab1.Bai4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab1.R;

public class Bai4Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvResult;
    private EditText edtTimes;
    private Button btnRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4);

        tvResult = (TextView) findViewById(R.id.textView);
        edtTimes = (EditText) findViewById(R.id.edtTimes);
        btnRun = (Button) findViewById(R.id.btnRun);
        btnRun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRun:
                AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner(this, tvResult, edtTimes);
                String sleepTime = edtTimes.getText().toString();
                asyncTaskRunner.execute(sleepTime);
                break;
        }
    }
}