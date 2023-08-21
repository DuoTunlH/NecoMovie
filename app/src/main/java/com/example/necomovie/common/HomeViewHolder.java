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
    TextView textView;
    RecyclerView sectionRecyclerView;
    SectionMovies sectionMovies;
    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        sectionRecyclerView = itemView.findViewById(R.id.recycleView);
        textView = itemView.findViewById(R.id.textView);
    }

    public void setSectionMovies(SectionMovies sectionMovies) {
        this.sectionMovies = sectionMovies;
    }
}
