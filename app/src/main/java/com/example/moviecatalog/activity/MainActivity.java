package com.example.moviecatalog.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.example.moviecatalog.R;
import com.example.moviecatalog.adapter.MovieAdapter;
import com.example.moviecatalog.model.Response;
import com.example.moviecatalog.model.Result;
import com.example.moviecatalog.rest.APIClient;
import com.example.moviecatalog.rest.APIInterface;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter adapter;
    String API_KEY = "a6342954148d3807748b4697f64a5474";
    String CATEGORY = "popular";
    int PAGE = 1;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rv_movie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CallRetrofit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private void CallRetrofit() {
        APIInterface apiInterface = APIClient.getCient().create(APIInterface.class);
        Call<Response> responseCall = apiInterface.getMovie(CATEGORY, API_KEY, PAGE);
        responseCall.enqueue(new Callback<Response>() {

            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                List<Result> resultList = response.body().getResults();
                adapter = new MovieAdapter(MainActivity.this, resultList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}