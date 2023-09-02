package com.example.necomovie;

import androidx.lifecycle.ViewModel;

import com.example.necomovie.managers.APICaller;
import com.example.necomovie.model.Movie;
import com.example.necomovie.model.MoviesResponse;
import com.example.necomovie.model.SectionMovies;
import com.example.necomovie.model.Sections;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends ViewModel {
    Movie movie;
    void fetchData() {
            APICaller.getINSTANCE().getMovieById(movie.id).enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    List<SectionMovies> sectionMoviesList = sectionMovies.getValue();
                    if (sectionMoviesList == null) {
                        sectionMoviesList = new ArrayList<>();
                        sectionMovies.setValue(sectionMoviesList);
                    }
                    sectionMoviesList.add(new SectionMovies(section, response.body().results));
                    if (sectionMoviesList.size() == Sections.values().length){
                        fetchedSucessful.setValue(true);
                    }
                }
                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {

                }
            });
    }
}
