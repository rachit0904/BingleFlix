package com.project.fragmentsampleapp;

import Adapter.RecyclerViewAdapter;
import Model.MovieData;
import Util.Constants;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class searchfragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private String URL,query;
    Context ctx;
    private List<MovieData> moviesDataList;
    private RequestQueue queue;
    public static searchfragment newInstance() {
        return new searchfragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        {
            View view = inflater.inflate(R.layout.searchfragment, container, false);
            ctx=getContext();
            SharedPreferences preferences=getActivity().getSharedPreferences("user query",0);
            query=preferences.getString("query","");
            URL=Constants.URL_LEFT+query+Constants.URL_RIGHT;
            search(ctx,view);
            return view;
        }
    }

    private void search(final Context ctx, final View view) {
        queue = Volley.newRequestQueue(ctx);
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    moviesDataList=new ArrayList<>();
                    JSONArray jsonArray = response.getJSONArray("Search");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objectRequest = jsonArray.getJSONObject(i);
                        MovieData moviesData=new MovieData();
                        moviesData.setTitle(objectRequest.getString("Title"));
                        moviesData.setYear(objectRequest.getString("Year"));
                        moviesData.setType(objectRequest.getString("Type"));
                        moviesData.setPoster(objectRequest.getString("Poster"));
                        moviesData.setImdbId(objectRequest.getString("imdbID"));
                        moviesDataList.add(moviesData);
                    }
                    recyclerView(moviesDataList,view);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, "No internet connection", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);
    }

    private void recyclerView(List<MovieData> moviesDataList,View v) {
        recyclerView=(RecyclerView) v.findViewById(R.id.recyclerId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        List<MovieData> list=new ArrayList<>();
        for(MovieData x:moviesDataList){
            MovieData movies =new MovieData();
            movies.setTitle(x.getTitle());
            movies.setYear(x.getYear());
            movies.setType(x.getType());
            movies.setPoster(x.getPoster());
            movies.setImdbId(x.getImdbId());
            list.add(movies);
        }
        adapter=new RecyclerViewAdapter( ctx,list);
        recyclerView.setAdapter(adapter);    }

}