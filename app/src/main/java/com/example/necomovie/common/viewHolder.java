package com.example.necomovie.common;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.necomovie.R;

public class viewHolder extends RecyclerView.ViewHolder {
    ImageView image;

    public viewHolder(@NonNull View itemView) {
        super(itemView);
        //image = itemView.findViewById(R.id.imageView);
    }

}
