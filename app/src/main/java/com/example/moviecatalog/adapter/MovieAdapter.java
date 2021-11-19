package com.example.moviecatalog.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviecatalog.R;
import com.example.moviecatalog.activity.DetailActivity;
import com.example.moviecatalog.model.Result;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyOwnHolder> {
    private Context context;
    private List<Result> resultList;

    public MovieAdapter(Context context, List<Result> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public MovieAdapter.MyOwnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.item_movie, parent, false);
        MovieAdapter.MyOwnHolder holder = new MovieAdapter.MyOwnHolder(view);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parent.getContext(), DetailActivity.class);
                Result result = new Result();
                result.setTitle(resultList.get(holder.getAbsoluteAdapterPosition()).getTitle());
                result.setOriginalTitle(resultList.get(holder.getAbsoluteAdapterPosition()).getOriginalTitle());
                result.setReleaseDate(resultList.get(holder.getAbsoluteAdapterPosition()).getReleaseDate());
                result.setVoteCount(resultList.get(holder.getAbsoluteAdapterPosition()).getVoteCount());
                result.setVoteAverage(resultList.get(holder.getAbsoluteAdapterPosition()).getVoteAverage());
                result.setOverview(resultList.get(holder.getAbsoluteAdapterPosition()).getOverview());
                result.setOriginalLanguage(resultList.get(holder.getAbsoluteAdapterPosition()).getOriginalLanguage().toUpperCase());
                result.setPosterPath(resultList.get(holder.getAbsoluteAdapterPosition()).getPosterPath());
                result.setPopularity(resultList.get(holder.getAbsoluteAdapterPosition()).getPopularity());
                intent.putExtra(DetailActivity.EXTRA_MOVIE, result);
                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyOwnHolder holder, int position) {
        holder.txtTitle.setText(resultList.get(position).getTitle());
        holder.txtReleaseDate.setText(resultList.get(position).getReleaseDate());
        holder.txtRating.setText(resultList.get(position).getVoteAverage().toString());
        holder.txtDescription.setText(resultList.get(position).getOverview());
        Glide.with(context).load("https://themoviedb.org/t/p/w500/"
                + resultList.get(position).getBackdropPath()).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class MyOwnHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView txtTitle, txtDescription, txtReleaseDate, txtRating;
        LinearLayout linearLayout;

        public MyOwnHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.img_movie);
            imgView.setClipToOutline(true);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtDescription = itemView.findViewById(R.id.txt_desc);
            txtReleaseDate = itemView.findViewById(R.id.txt_release_date);
            txtRating = itemView.findViewById(R.id.txt_rating);
            linearLayout = itemView.findViewById(R.id.layoutMovie);
        }
    }
}
