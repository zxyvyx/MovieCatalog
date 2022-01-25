package com.example.moviecatalog.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviecatalog.R;
import com.example.moviecatalog.model.Result;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    String title, description, image;
    ImageView imgDetail;
    TextView txtViewTitle, txtViewDescription, txtViewRating,
            txtViewOriginalTitle, txtViewLanguage, txtViewReleaseDate, txtPopularity;
    Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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

        txtViewTitle = findViewById(R.id.txt_detail_title);
        txtViewDescription = findViewById(R.id.txt_detail_desc);
        imgDetail = findViewById(R.id.img_detail_movie);
        txtViewRating = findViewById(R.id.txt_detail_rating);
        txtViewOriginalTitle = findViewById(R.id.txt_detail_ori_title);
        txtViewLanguage = findViewById(R.id.txt_detail_language);
        txtViewReleaseDate = findViewById(R.id.txt_detail_release_date);
        txtPopularity = findViewById(R.id.txt_detail_popularity);

        result = getIntent().getParcelableExtra(EXTRA_MOVIE);

        title = result.getTitle();
        description = result.getOverview();
        image = result.getPosterPath();

        txtViewTitle.setText(title);
        txtViewDescription.setText(description);
        txtViewRating.setText(result.getVoteAverage().toString());
        txtViewOriginalTitle.setText(result.getOriginalTitle());
        txtViewLanguage.setText(result.getOriginalLanguage());
        txtViewReleaseDate.setText(result.getReleaseDate());
        txtPopularity.setText(result.getPopularity().toString());

        Glide.with(getApplicationContext()).load("https://themoviedb.org/t/p/w500" + image)
                .into(imgDetail);
    }
}