package com.example.necomovie.common;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.necomovie.R;
import com.example.necomovie.model.SectionMovies;

public class HomeViewHolder extends RecyclerView.ViewHolder {

    Context context;
    TextView sectionName;
    TextView seeAll;
    RecyclerView sectionRecyclerView;
    SectionMovies sectionMovies;
    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        sectionRecyclerView = itemView.findViewById(R.id.recycleView);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(0,40);
        sectionRecyclerView.addItemDecoration(itemDecorator);
        sectionName = itemView.findViewById(R.id.sectionName);
        seeAll = itemView.findViewById(R.id.seeAll);
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void setSectionMovies(SectionMovies sectionMovies) {
        this.sectionMovies = sectionMovies;
    }
}
