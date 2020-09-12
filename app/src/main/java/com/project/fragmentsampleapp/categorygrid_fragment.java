package com.project.fragmentsampleapp;

import android.os.Bundle;

import Adapter.homepageRecyclerAdapter;
import Adapter.moviesgridRecyclerAdapter;
import Model.MovieCardsData;
import Util.Constants;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        Bundle bundle=getActivity().getIntent().getExtras();
        setData(bundle.getString("url"));
        recyclerView=view.findViewById(R.id.RecyclerId);
        return view;
    }
    private void setData(String url) {
        list=new ArrayList<>();
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
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
                    recycler(list);
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
    private void recycler(List<MovieCardsData> list) {
       {
           recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
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
           adapter=new moviesgridRecyclerAdapter(getContext(),movieCardsData);
           recyclerView.setAdapter(adapter);
            }
            }
        }

