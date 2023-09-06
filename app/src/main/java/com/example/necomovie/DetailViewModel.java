package com.example.necomovie;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.necomovie.managers.APICaller;
import com.example.necomovie.model.Movie;
import com.example.necomovie.model.MoviesResponse;
import com.example.necomovie.model.SectionMovies;
import com.example.necomovie.model.Sections;
import com.example.necomovie.model.Trailer;
import com.example.necomovie.model.TrailersResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends ViewModel {
    Movie movie;
    MutableLiveData<List<Trailer>> trailers = new MutableLiveData<>();
    MutableLiveData<List<Movie>> similarMovies = new MutableLiveData<>();
    void fetchData() {
            APICaller.getINSTANCE().getTrailers(movie.id).enqueue(new Callback<TrailersResponse>() {
                @Override
                public void onResponse(Call<TrailersResponse> call, Response<TrailersResponse> response) {
                    trailers.setValue(response.body().results);
                }
                @Override
                public void onFailure(Call<TrailersResponse> call, Throwable t) {

                }
            });
        APICaller.getINSTANCE().getSimilarsById(movie.id).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                similarMovies.setValue(response.body().results);
            }
            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
            }
        });
    }
}
