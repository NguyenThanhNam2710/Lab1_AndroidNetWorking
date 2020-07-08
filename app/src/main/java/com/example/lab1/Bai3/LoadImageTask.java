package com.example.lab1.Bai3;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.sip.SipSession;
import android.os.AsyncTask;
import android.os.IInterface;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {


    public interface Listener {
        void onImageLoaded(Bitmap bitmap);

        void onError();
    }

    private Listener mListener;
    private ProgressDialog mProgressDialog;

    public LoadImageTask(Listener mListener, Context context) {
        this.mListener = mListener;
        mProgressDialog = new ProgressDialog(context);
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        try {
            return BitmapFactory.decodeStream((InputStream) new URL(params[0]).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog.setMessage("Downloading image...");
        mProgressDialog.show();
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        if (result != null) {
            mListener.onImageLoaded(result);
        } else {
            mListener.onError();
        }

    }
}
