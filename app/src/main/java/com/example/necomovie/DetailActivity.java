package com.example.necomovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.necomovie.model.Movie;

public class DetailActivity extends AppCompatActivity {
    WebView webview;
    TextView titleTextView;
    TextView yearTextView;
    TextView descriptionTextView;
    RecyclerView recyclerView;
    Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        movie = getIntent().getParcelableExtra("movie");
        webview = (WebView) findViewById(R.id.webView);
        titleTextView = (TextView) findViewById(R.id.movieTitleTextView);
        yearTextView = (TextView) findViewById(R.id.yearTextView);
        descriptionTextView = (TextView) findViewById(R.id.yearTextView);
        recyclerView = (RecyclerView) findViewById(R.id.detailRecycleView);
        titleTextView.setText(movie.original_title);
    }
}