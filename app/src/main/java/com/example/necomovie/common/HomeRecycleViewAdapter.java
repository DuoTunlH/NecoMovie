package com.example.necomovie.common;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.necomovie.R;
import com.example.necomovie.model.SectionMovies;
import com.example.necomovie.model.Sections;

import java.util.List;

public class HomeRecycleViewAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    Context context;
    List<SectionMovies> sections;

    public HomeRecycleViewAdapter(Context context, List<SectionMovies> sections) {
        this.context = context;
        this.sections = sections;
    }
    public void setSections(List<SectionMovies> sections){
        this.sections = sections;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(LayoutInflater.from(context).inflate(R.layout.home_sections_recycleview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Sections a[] = Sections.values();
        if(sections != null){
            for (SectionMovies section : sections
            ) {
                Log.e("section",section.getSection() +" " +  a[position]);
                if(section.getSection() == a[position]) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                    holder.sectionRecyclerView.setLayoutManager(layoutManager);
                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    holder.sectionRecyclerView.setAdapter(new SectionRecycleViewAdapter(context, sections.get(position).getMovies()));
                    holder.setSectionMovies(sections.get(position));
                    holder.textView.setText(sections.get(position).getSection().name());
                    return;
                }
            }
        }


    }


    @Override
    public int getItemCount() {
        if(sections != null)
            return sections.size();
        return 0;
    }
}
