package com.example.necomovie.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.necomovie.managers.APICaller;
import com.example.necomovie.model.Movie;
import com.example.necomovie.model.MoviesResponse;
import com.example.necomovie.model.SectionMovies;
import com.example.necomovie.model.Sections;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    //private final MutableLiveData<String> mText;
    MutableLiveData<List<SectionMovies>> sectionMovies = new MutableLiveData<>();
    MutableLiveData<Boolean> fetchedSucessful = new MutableLiveData<>(false);
//    EnumMap<Sections,List<Movie>> sectionMovies1;
//    public String[] sections = {"Trending","Upcoming","Popular","Top Rated"};
//    MutableLiveData<List<Movie>> trendingMovies = new MutableLiveData<>();
//    MutableLiveData<List<Movie>> upcomingMovies = new MutableLiveData<>();
//    MutableLiveData<List<Movie>> popularMovies = new MutableLiveData<>();
//    MutableLiveData<List<Movie>> topRatedMovies = new MutableLiveData<>();
    private boolean hasData = false;
    void fetchData() {
        if (!hasData) {
            hasData = true;
            for (Sections section : Sections.values()) {
                APICaller.getINSTANCE().getMovies(section).enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
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
    }

}