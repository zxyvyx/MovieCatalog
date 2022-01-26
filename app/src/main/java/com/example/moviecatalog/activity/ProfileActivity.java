package com.example.moviecatalog.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviecatalog.R;

public class ProfileActivity extends AppCompatActivity {
    TextView greetingUser;
    Button signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences preferences = getSharedPreferences("name", MODE_PRIVATE);
        greetingUser = (TextView) findViewById(R.id.txt_greeting);
        greetingUser.setText("Hi, " + preferences.getString("username", null) + "!");

        final Drawable backArrow = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_arrow_back_24, null);
        backArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        toolbar.setNavigationIcon(backArrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signout = (Button) findViewById(R.id.btn_signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this, R.string.sign_out_success, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("EXIT", true);
                SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
                editor.putString("password", "");
                editor.putString("username", "");
                editor.putBoolean("isLoggedIn", false);
                editor.apply();
                startActivity(intent);
                finish();
            }
        });
    }
}