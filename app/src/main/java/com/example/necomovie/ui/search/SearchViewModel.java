package com.example.necomovie.ui.search;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.necomovie.managers.APICaller;
import com.example.necomovie.model.Movie;
import com.example.necomovie.model.MoviesResponse;
import com.example.necomovie.model.Sections;
import com.example.necomovie.model.TrailersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends ViewModel {

    MutableLiveData<List<Movie>> movies = new MutableLiveData<>();

    void fetchData() {
        APICaller.getINSTANCE().getMovies(Sections.TRENDING).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                movies.setValue(response.body().results);
            }
            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
            }
        });
    }
}