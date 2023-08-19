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

public class listViewAdapter extends BaseAdapter {
    private String[] sections;
    private Context context;

    public listViewAdapter(String[] sections, Context context) {
        this.sections = sections;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sections.length;
    }

    @Override
    public Object getItem(int i) {
        return sections[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.home_listview_recycleview, null);
        }

        TextView textView = convertView.findViewById(R.id.textView);
        RecyclerView recyclerView = convertView.findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setAdapter(new recycleViewAdapter(context));
        textView.setText(sections[i]);
        return convertView;
    }
}
