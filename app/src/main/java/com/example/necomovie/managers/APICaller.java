package com.example.necomovie.managers;

import android.util.Log;

import com.example.necomovie.model.MoviesResponse;
import com.example.necomovie.model.Sections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APICaller {
    static String API_KEY = "f14dabb7ec91ad6abc33ee277d4c4d07";
    static String BASE_URL = "https://api.themoviedb.org/";
    static MovieService movieService;
    public static APICaller INSTANCE;
    MovieService service;
    private APICaller() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(MovieService.class);
    }

    public static APICaller getINSTANCE() {
        if (null == INSTANCE){
            INSTANCE = new APICaller();
        }
        return INSTANCE;
    }
    public Call<MoviesResponse> getMovies(Sections section){

        String time = "";
        if (section.getTime() != ""){
            time = "/" + section.getTime();
        }
//        let url = "\(Constants.BASE_URL)/3/\(type)/\(status)\(tempTime!)?api_key=\(Constants.API_KEY)\("&query=" + query)&page=\(page)&with_genres=\(genre)"
        String url = "3/" + section.getType() +"/"+ section.getStatus() + time + "?api_key=" + API_KEY + "&with_genres=" + section.getGenreId();
        return service.getMovies(url);
    }

}
