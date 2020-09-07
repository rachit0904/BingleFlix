package com.project.fragmentsampleapp;

import android.os.Bundle;

import Adapter.homepageRecyclerAdapter;
import Adapter.moviesgridRecyclerAdapter;
import Model.MovieCardsData;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class categorygrid_fragment extends Fragment {
    TextView title;
    List<MovieCardsData> list;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_categorygrid_fragment, container, false);
        recyclerView=view.findViewById(R.id.RecyclerId);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setHasFixedSize(true);
        list=new ArrayList<>();
        for(int i=0;i<17;i++){
            MovieCardsData data=new MovieCardsData();
            data.setMovieTitle("Fast and Furious");
            data.setRating("7.0");
            list.add(data);
        }
        adapter=new moviesgridRecyclerAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        return view;
    }
}