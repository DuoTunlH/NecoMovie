package com.example.necomovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.necomovie.common.GridSpacingItemDecoration;
import com.example.necomovie.common.HomeRecycleViewAdapter;
import com.example.necomovie.common.SectionRecycleViewAdapter;
import com.example.necomovie.common.SpacingItemDecorator;
import com.example.necomovie.model.Movie;
import com.example.necomovie.model.SectionMovies;
import com.example.necomovie.model.Trailer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    YouTubePlayerView youTubePlayerView;
    TextView titleTextView;
    TextView yearTextView;
    TextView descriptionTextView;
    RecyclerView recyclerView;
    DetailViewModel detailViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        detailViewModel.movie = getIntent().getParcelableExtra("movie");
        detailViewModel.fetchData();
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        titleTextView = (TextView) findViewById(R.id.movieTitleTextView);
        yearTextView = (TextView) findViewById(R.id.yearTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        recyclerView = (RecyclerView) findViewById(R.id.detailRecycleView);
        titleTextView.setText(detailViewModel.movie.original_title);
        yearTextView.setText(detailViewModel.movie.release_date);
        descriptionTextView.setText(detailViewModel.movie.overview);
        List<Movie> movies = detailViewModel.similarMovies.getValue();
        SectionRecycleViewAdapter adapter = new SectionRecycleViewAdapter(this, movies);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        SpacingItemDecorator itemDecorator = new  SpacingItemDecorator(50,0);
//        recyclerView.addItemDecoration(itemDecorator);
        int spanCount = 3;
        int spacing = 20;
        boolean includeEdge = false;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        recyclerView.setAdapter(adapter);
        detailViewModel.trailers.observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                        for (Trailer trailer : detailViewModel.trailers.getValue()) {
                            if ("Trailer".equals(trailer.type)) {
                                youTubePlayer.cueVideo(trailer.key, 0);
                                break;
                            }
                        }
                    }
                });
            }
        });
        detailViewModel.similarMovies.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> list) {
                adapter.list = list;
                adapter.notifyDataSetChanged();
            }
        });
    }
}