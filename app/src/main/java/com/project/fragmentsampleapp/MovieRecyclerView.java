package com.project.fragmentsampleapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieRecyclerView extends RecyclerView.Adapter<MovieRecyclerView.ViewHolder>{
    Context context;
    List<MoviesData> moviesDataList;
    public MovieRecyclerView(Context context, List<MoviesData> moviesDataList){
        this.context=context;
        this.moviesDataList=moviesDataList;
    }
    @NonNull
    @Override
    public MovieRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerView.ViewHolder holder, int position) {
        MoviesData moviesData=moviesDataList.get(position);
        holder.name.setText(moviesData.getTitle());
    }

    @Override
    public int getItemCount() {
        return moviesDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.movieName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
