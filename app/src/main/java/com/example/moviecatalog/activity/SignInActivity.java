package com.example.moviecatalog.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.moviecatalog.R;
import com.example.moviecatalog.helper.DBHelper;

public class SignInActivity extends AppCompatActivity {
    EditText username, password;
    Button signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);
        signin = (Button) findViewById(R.id.btn_signin_account);
        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        DB = new DBHelper(this);

        final Drawable backArrow = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_arrow_back_24, null);
        backArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        toolbar.setNavigationIcon(backArrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("")||pass.equals("")) {
                    Toast.makeText(SignInActivity.this, R.string.empty_credentials, Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checker = DB.checkUsernamePassword(user, pass);
                    if (checker == true) {
                        Toast.makeText(SignInActivity.this, R.string.sign_in_success, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), CatalogActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("EXIT", true);
                        SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
                        editor.putString("username", user);
                        editor.putString("password", pass);
                        editor.putBoolean("isLoggedIn", true);
                        editor.apply();
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignInActivity.this, R.string.sign_in_invalid, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }
}