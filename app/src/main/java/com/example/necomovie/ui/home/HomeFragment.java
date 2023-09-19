package com.example.necomovie.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.necomovie.R;
import com.example.necomovie.common.HomeRecycleViewAdapter;
import com.example.necomovie.common.SpacingItemDecorator;
import com.example.necomovie.databinding.FragmentHomeBinding;
import com.example.necomovie.model.SectionMovies;

import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {



    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        recyclerView = view.findViewById(R.id.homeRecycleView);

        List<SectionMovies> s = homeViewModel.sectionMovies.getValue();
        HomeRecycleViewAdapter adapter = new HomeRecycleViewAdapter(getContext(), s);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        SpacingItemDecorator itemDecorator = new  SpacingItemDecorator(50,0);
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(adapter);
        homeViewModel.fetchData();
        homeViewModel.isFetchedSucessful.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                Collections.sort(homeViewModel.sectionMovies.getValue());
                adapter.setSections(homeViewModel.sectionMovies.getValue());
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