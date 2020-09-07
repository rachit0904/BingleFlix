package com.project.fragmentsampleapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;

import Model.PageViewModel;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final RecyclerView recyclerView=root.findViewById(R.id.recyclerViewID);
        final RecyclerView recyclerView2=root.findViewById(R.id.recyclerViewID2);
        final RecyclerView recyclerView3=root.findViewById(R.id.recyclerViewID3);
        final ImageView fold=root.findViewById(R.id.fold);
        final ImageView fold2=root.findViewById(R.id.fold2);
        final ImageView fold3=root.findViewById(R.id.fold3);
        FrameLayout tble1=root.findViewById(R.id.tble1);
        tble1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.Adapter adapter;
                recyclerView.setVisibility(View.VISIBLE);
                fold.setVisibility(View.VISIBLE);
                fold.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerView.setVisibility(View.GONE);
                        fold.setVisibility(View.GONE);
                    }
                });
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                ArrayList arrayList=new ArrayList();
                for(int i=0;i<4;i++){
                    MoviesData data=new MoviesData();
                    data.setTitle("Fast & Furious");
                    arrayList.add(data);
                }
                adapter=new MovieRecyclerView(getContext(),arrayList);
                recyclerView.setAdapter(adapter);
            }
        });
        FrameLayout tble2=root.findViewById(R.id.tble2);
        tble2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.Adapter adapter;
                recyclerView2.setVisibility(View.VISIBLE);
                fold2.setVisibility(View.VISIBLE);
                fold2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerView2.setVisibility(View.GONE);
                        fold2.setVisibility(View.GONE);
                    }
                });
                recyclerView2.setHasFixedSize(true);
                recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
                ArrayList arrayList=new ArrayList();
                for(int i=0;i<7;i++){
                    MoviesData data=new MoviesData();
                    data.setTitle("Fast & Furious");
                    arrayList.add(data);
                }
                adapter=new MovieRecyclerView(getContext(),arrayList);
                recyclerView2.setAdapter(adapter);
            }
        });
        FrameLayout tble3=root.findViewById(R.id.tble3);
        tble3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.Adapter adapter;
                recyclerView3.setVisibility(View.VISIBLE);
                fold3.setVisibility(View.VISIBLE);
                fold3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerView3.setVisibility(View.GONE);
                        fold3.setVisibility(View.GONE);
                    }
                });
                recyclerView3.setHasFixedSize(true);
                recyclerView3.setLayoutManager(new LinearLayoutManager(getContext()));
                ArrayList arrayList=new ArrayList();
                for(int i=0;i<12;i++){
                    MoviesData data=new MoviesData();
                    data.setTitle("Fast & Furious");
                    arrayList.add(data);
                }
                adapter=new MovieRecyclerView(getContext(),arrayList);
                recyclerView3.setAdapter(adapter);
            }
        });
        return root;
    }
}