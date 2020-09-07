package com.project.fragmentsampleapp;

import android.content.Intent;
import android.os.Bundle;

import Adapter.homepageRecyclerAdapter;
import Model.MovieCardsData;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class home_fragment extends Fragment implements View.OnClickListener {
    TextView moreRecent,moreRelased,morePopular,moreWishlist;
   RecyclerView recyclerView;
   RecyclerView.Adapter adapter;
   List<MovieCardsData> list,list2,list3,list4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);
        recyclerView=view.findViewById(R.id.RecyclerViewId);
        moreRecent=view.findViewById(R.id.moreRecentSearched);
        moreRelased=view.findViewById(R.id.moreRecentReleased);
        morePopular=view.findViewById(R.id.morePopular);
        moreWishlist=view.findViewById(R.id.moreFromWishlist);
        moreRecent.setOnClickListener(this);
        moreRelased.setOnClickListener(this);
        morePopular.setOnClickListener(this);
        moreWishlist.setOnClickListener(this);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        list=new ArrayList<>();
        for(int i=0;i<7;i++){
            MovieCardsData data=new MovieCardsData();
            data.setMovieTitle("Fast and Furious");
            data.setRating("7.0");
            list.add(data);
        }
        adapter=new homepageRecyclerAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        recyclerView=view.findViewById(R.id.RecyclerViewId2);
        LinearLayoutManager manager2=new LinearLayoutManager(getContext());
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager2);
        recyclerView.setHasFixedSize(true);
        list2=new ArrayList<>();
        for(int i=0;i<7;i++){
            MovieCardsData data=new MovieCardsData();
            data.setMovieTitle("Fast and Furious");
            data.setRating("7.0");
            list2.add(data);
        }
        adapter=new homepageRecyclerAdapter(getContext(),list2);
        recyclerView.setAdapter(adapter);

        recyclerView=view.findViewById(R.id.RecyclerViewId3);
        LinearLayoutManager manager3=new LinearLayoutManager(getContext());
        manager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager3);
        recyclerView.setHasFixedSize(true);
        list3=new ArrayList<>();
        for(int i=0;i<7;i++){
            MovieCardsData data=new MovieCardsData();
            data.setMovieTitle("Fast and Furious");
            data.setRating("7.0");
            list3.add(data);
        }
        adapter=new homepageRecyclerAdapter(getContext(),list3);
        recyclerView.setAdapter(adapter);

        recyclerView=view.findViewById(R.id.RecyclerViewId4);
        LinearLayoutManager manager4=new LinearLayoutManager(getContext());
        manager4.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager4);
        recyclerView.setHasFixedSize(true);
        list4=new ArrayList<>();
        for(int i=0;i<7;i++){
            MovieCardsData data=new MovieCardsData();
            data.setMovieTitle("Fast and Furious");
            data.setRating("7.0");
            list4.add(data);
        }
        adapter=new homepageRecyclerAdapter(getContext(),list4);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(),categorydetails.class);
        switch (v.getId()){
            case R.id.moreRecentSearched:{
                intent.putExtra("title","Based on your recent search");
                startActivity(intent);
                break;
            }
            case R.id.moreRecentReleased:{
                intent.putExtra("title","Recently released");
                startActivity(intent);
                break;
            }
            case R.id.morePopular:{
                intent.putExtra("title","Most popular");
                startActivity(intent);
                break;
            }
            case R.id.moreFromWishlist:{
                intent.putExtra("title","From on your watchlist");
                startActivity(intent);
                break;
            }
        }
    }
}