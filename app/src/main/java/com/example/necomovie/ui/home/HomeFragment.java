package com.example.necomovie.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.necomovie.R;
import com.example.necomovie.common.listViewAdapter;
import com.example.necomovie.databinding.FragmentHomeBinding;
import com.example.necomovie.model.Movie;
import com.example.necomovie.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class HomeFragment extends Fragment {

    public interface GitHubService {
        @GET
            //("v1/current.json?key={key}&q=London")
        Call<MoviesResponse> weather(@Url String url);
    }

    private FragmentHomeBinding binding;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        service.weather("3/movie/popular?api_key=f14dabb7ec91ad6abc33ee277d4c4d07").enqueue(new Callback<MoviesResponse>(){
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                Log.d("app111", response.body().results[0].id);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d("app111",t.toString());
            }
        });
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = root.findViewById(R.id.homeListView);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                getContext(), android.R.layout.simple_list_item_1,
//                homeViewModel.sections
//        );
        listViewAdapter adapter = new listViewAdapter(homeViewModel.sections, getContext());
        listView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}