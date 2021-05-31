package com.example.assessmenttask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTagger" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://backend-test-zypher.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<ResponseData> responseCall = retrofitAPI.postRequest();
        responseCall.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG,response.toString() + response.code());
                    return;
                }
                String res =response.body().getImageURL();
                ResponseDialogBox responseDialogBox = ResponseDialogBox.getResponseDialogBoxInstance();

                responseDialogBox.show(MainActivity.this,response.body().getTitle(),response.body().getImageURL(),response.body().getSuccess_url());


            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });

    }


}