package com.example.necomovie.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.necomovie.R;
import com.example.necomovie.model.Movie;
import com.squareup.picasso.Picasso;
import java.util.List;

public class SectionRecycleViewAdapter extends RecyclerView.Adapter<SectionViewHolder> {
    Context context;
    List<Movie> list;

    public SectionRecycleViewAdapter(Context context, List<Movie> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SectionViewHolder(LayoutInflater.from(context).inflate(R.layout.home_recycleview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
//        Picasso.with(this)
//                .load("https://media.geeksforgeeks.org/wp-content/cdn-uploads/logo-new-2.svg")
//                .into(imageView);
        holder.setMovie(list.get(position));
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + list.get(position).poster_path).into(holder.image);
    }


    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 10;
    }
}
