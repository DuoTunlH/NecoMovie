package com.example.necomovie.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.necomovie.R;
import com.example.necomovie.common.GridSpacingItemDecoration;
import com.example.necomovie.common.MovieRecycleViewAdapter;
import com.example.necomovie.common.SectionRecycleViewAdapter;
import com.example.necomovie.databinding.FragmentSearchBinding;
import com.example.necomovie.model.Movie;

import java.util.List;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    RecyclerView recyclerView;
    SearchViewModel searchViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);
        recyclerView = (RecyclerView) view.findViewById(R.id.searchRecycleView);
        List<Movie> movies = searchViewModel.movies.getValue();
        MovieRecycleViewAdapter adapter = new MovieRecycleViewAdapter(getContext(), movies);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(adapter);
        searchViewModel.fetchData();
        searchViewModel.movies.observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> list) {
                adapter.list = list;
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}