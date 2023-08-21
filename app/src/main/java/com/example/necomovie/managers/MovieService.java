package com.example.necomovie.managers;

import com.example.necomovie.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MovieService {
    @GET
        //("v1/current.json?key={key}&q=London")
    Call<MoviesResponse> getMovies(@Url String url);
}
