package com.project.fragmentsampleapp;

import android.content.Intent;
import android.os.Bundle;

import Adapter.homepageRecyclerAdapter;
import Model.MovieCardsData;
import Util.Constants;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class home_fragment extends Fragment implements View.OnClickListener {
    TextView moreRecent,moreRelased,morePopular,moreWishlist,rated;
   RecyclerView recyclerView,recyclerView2,recyclerView3,recyclerView4,recyclerView5;
   RecyclerView.Adapter adapter;
   List<MovieCardsData> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);
        recyclerView=view.findViewById(R.id.RecyclerViewId2);
        recyclerView2=view.findViewById(R.id.RecyclerViewId);
        recyclerView3=view.findViewById(R.id.RecyclerViewId3);
        recyclerView4=view.findViewById(R.id.RecyclerViewId4);
        recyclerView5=view.findViewById(R.id.RecyclerViewId5);

        moreRecent=view.findViewById(R.id.trending);
        moreRelased=view.findViewById(R.id.moreRecentReleased);
        morePopular=view.findViewById(R.id.morePopular);
        rated=view.findViewById(R.id.topRated);
        moreWishlist=view.findViewById(R.id.moreFromWishlist);

        moreRecent.setOnClickListener(this);
        moreRelased.setOnClickListener(this);
        morePopular.setOnClickListener(this);
        rated.setOnClickListener(this);
        moreWishlist.setOnClickListener(this);

        //recent
        setData(Constants.getRecentRelease,1);
        //trending
        setData(Constants.getTrending,2);
        //popular
        setData(Constants.getPopular,3);
        //top-rated
        setData(Constants.getTopRated,4);

        return view;
    }

    private void setData(String url, final int ch) {
        list=new ArrayList<>();
        RequestQueue requestQueue=Volley.newRequestQueue(getContext());
        final JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array=response.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject objectRequest = array.getJSONObject(i);
                        final MovieCardsData data=new MovieCardsData();
                        data.setMovieTitle(objectRequest.getString("title"));
                        data.setRating(objectRequest.getString("vote_average"));
                        data.setPoster(objectRequest.getString("poster_path"));
                        data.setImdbId(objectRequest.getString("id"));
                        list.add(data);
                    }
                    recycler(list,ch);
                    list.clear();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "issue!", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(objectRequest);
    }

    private void recycler(List<MovieCardsData> list,int ch) {
        switch (ch)
        {
            case 1: {
                LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
                manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(manager2);
                recyclerView.setHasFixedSize(true);
                List<MovieCardsData> movieCardsData = new ArrayList<>();
                for (MovieCardsData x : list) {
                    MovieCardsData data = new MovieCardsData();
                    data.setMovieTitle(x.getMovieTitle());
                    data.setRating(x.getRating());
                    data.setPoster(x.getPoster());
                    data.setImdbId(x.getImdbId());
                    movieCardsData.add(data);
                }
                adapter = new homepageRecyclerAdapter(getContext(), movieCardsData);
                recyclerView.setAdapter(adapter);
                break;
            }
            case 2:{
                LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
                manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView2.setLayoutManager(manager2);
                recyclerView2.setHasFixedSize(true);
                List<MovieCardsData> movieCardsData = new ArrayList<>();
                for (MovieCardsData x : list) {
                    MovieCardsData data = new MovieCardsData();
                    data.setMovieTitle(x.getMovieTitle());
                    data.setRating(x.getRating());
                    data.setPoster(x.getPoster());
                    data.setImdbId(x.getImdbId());
                    movieCardsData.add(data);
                }
                adapter = new homepageRecyclerAdapter(getContext(), movieCardsData);
                recyclerView2.setAdapter(adapter);
                break;
            }
            case 3:{
                LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
                manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView3.setLayoutManager(manager2);
                recyclerView3.setHasFixedSize(true);
                List<MovieCardsData> movieCardsData = new ArrayList<>();
                for (MovieCardsData x : list) {
                    MovieCardsData data = new MovieCardsData();
                    data.setMovieTitle(x.getMovieTitle());
                    data.setRating(x.getRating());
                    data.setPoster(x.getPoster());
                    data.setImdbId(x.getImdbId());
                    movieCardsData.add(data);
                }
                adapter = new homepageRecyclerAdapter(getContext(), movieCardsData);
                recyclerView3.setAdapter(adapter);
                break;
            }
            case 4:{
                LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
                manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView4.setLayoutManager(manager2);
                recyclerView4.setHasFixedSize(true);
                List<MovieCardsData> movieCardsData = new ArrayList<>();
                for (MovieCardsData x : list) {
                    MovieCardsData data = new MovieCardsData();
                    data.setMovieTitle(x.getMovieTitle());
                    data.setRating(x.getRating());
                    data.setPoster(x.getPoster());
                    data.setImdbId(x.getImdbId());
                    movieCardsData.add(data);
                }
                adapter = new homepageRecyclerAdapter(getContext(), movieCardsData);
                recyclerView4.setAdapter(adapter);
                break;
            }
            case 5:{
               /*LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
                manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(manager2);
                recyclerView.setHasFixedSize(true);
                List<MovieCardsData> movieCardsData = new ArrayList<>();
                for (MovieCardsData x : list) {
                    MovieCardsData data = new MovieCardsData();
                    data.setMovieTitle(x.getMovieTitle());
                    data.setRating(x.getRating());
                    data.setPoster(x.getPoster());
                    movieCardsData.add(data);
                }
                adapter = new homepageRecyclerAdapter(getContext(), movieCardsData);
                recyclerView.setAdapter(adapter);
                break;
                    */
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(),categorydetails.class);
        switch (v.getId()){
            case R.id.trending:{
                intent.putExtra("title","Trending movies");
                intent.putExtra("url",Constants.getTrending);
                startActivity(intent);
                break;
            }
            case R.id.moreRecentReleased:{
                intent.putExtra("title","Recently released");
                intent.putExtra("url",Constants.getRecentRelease);
                startActivity(intent);
                break;
            }
            case R.id.morePopular:{
                intent.putExtra("title","Most popular");
                intent.putExtra("url",Constants.getPopular);
                startActivity(intent);
                break;
            }
            case R.id.topRated:{
                intent.putExtra("title","Top-Rated");
                intent.putExtra("url",Constants.getTopRated);
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