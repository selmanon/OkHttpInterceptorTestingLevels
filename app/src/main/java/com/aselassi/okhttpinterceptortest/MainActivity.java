package com.aselassi.okhttpinterceptortest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity {

  OkHttpClient client;
  HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    logging.setLevel(HttpLoggingInterceptor.Level.NONE);
    client = new OkHttpClient.Builder().addInterceptor(logging).build();

    AsyncTask.execute(new Runnable() {
      @Override public void run() {
        try {
          MainActivity.this.run("http://www.google.com");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  String run(String url) throws IOException {
    Request request = new Request.Builder()
        .url(url)
        .build();

    Response response = client.newCall(request).execute();
    return response.body().string();
  }
}
