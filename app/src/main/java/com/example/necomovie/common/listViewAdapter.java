package com.example.necomovie.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.necomovie.R;
import com.example.necomovie.model.SectionMovies;

import java.util.List;

public class listViewAdapter extends BaseAdapter {
    private List<SectionMovies> sections;
    private Context context;

    public listViewAdapter(List<SectionMovies> sections, Context context) {
        this.sections = sections;
        this.context = context;
    }
    public void setSections(List<SectionMovies> sections){
        this.sections = sections;
    }
                            @Override
    public int getCount() {
        if (sections != null)
        return sections.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return sections.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.home_sections_recycleview, null);
        }

        TextView textView = convertView.findViewById(R.id.textView);
        RecyclerView recyclerView = convertView.findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setAdapter(new SectionRecycleViewAdapter(context, sections.get(i).getMovies()));
        textView.setText(sections.get(i).getSection().name());
        return convertView;
    }
}
