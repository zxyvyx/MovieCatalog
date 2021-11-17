package com.example.moviecatalog.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.moviecatalog.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void openRepository(View view) {
        Intent intentOpenBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/zxyvyx/MovieDirectory"));
        startActivity(intentOpenBrowser);
    }
}