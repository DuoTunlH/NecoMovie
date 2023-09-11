package com.example.necomovie.common;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.necomovie.R;
import com.example.necomovie.model.Movie;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    Context context;
    TextView title;
    ImageView poster;
    Movie movie;

    public void setMovie(Movie movie) {
        this.movie = movie;
        title.setText(movie.title);
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/" + movie.poster_path)
                .placeholder(R.drawable.photo)
                .into(poster);
    }

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        title = (TextView) itemView.findViewById(R.id.movieTitleTextView);
        poster = (ImageView) itemView.findViewById(R.id.movieImageView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
