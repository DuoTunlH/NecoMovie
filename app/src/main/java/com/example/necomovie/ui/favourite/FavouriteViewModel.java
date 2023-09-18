package com.example.necomovie.ui.favourite;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.necomovie.managers.APICaller;
import com.example.necomovie.model.Movie;
import com.example.necomovie.model.MoviesResponse;
import com.example.necomovie.model.Sections;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteViewModel extends ViewModel {
    public MutableLiveData<List<Movie>> movies = new MutableLiveData<>(new ArrayList<>());

    void fetchData() {
        FirebaseFirestore.getInstance().collection(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        APICaller.getINSTANCE().getMovieById(document.getData().get("id").toString()).enqueue(new Callback<Movie>() {
                            @Override
                            public void onResponse(Call<Movie> call, Response<Movie> response) {
                                movies.getValue().add(response.body());
                            }
                            @Override
                            public void onFailure(Call<Movie> call, Throwable t) {
                            }
                        });
                    }
                } else {
                    Log.w("aaa", "Error getting documents.", task.getException());
                }
            }
        });

    }
}