package com.project.fragmentsampleapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import Model.MovieData;
import Util.Constants;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class moviedetails_frag extends Fragment {
    private TextView movietitle, releaseYr, duration, rating, plot, actors, director, writer, genere, awards, typeDesc, likeTxt, dislikeTxt, watchlistTxt, userRating;
    private ImageButton like, dislike, watchlist, back;
    private ImageView poster, star;
    private Button rate, play;
    private String URL;
    private RequestQueue queue;
    private SwipeRefreshLayout refreshLayout;
    private int state_like = 0, state_dislike = 0, state_watchlist = 0;
    private List<MovieData> moviesDataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_moviedetails_frag, container, false);
        initializeId(view);
        Bundle bundle = getActivity().getIntent().getExtras();
        String id = bundle.getString("imdbId");
        URL = Constants.URL_INFO_LEFT + id + Constants.URL_RIGHT;
        queue = Volley.newRequestQueue(getContext());
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    movietitle.setText(response.getString("Title"));
                    releaseYr.setText(response.getString("Year"));
                    duration.setText("|  " + response.getString("Runtime"));
                    rating.setText(response.getString("imdbRating") + "/10");
                    plot.setText(response.getString("Plot"));
                    genere.setText(response.getString("Genre"));
                    actors.setText("Staring: " + response.getString("Actors"));
                    director.setText("Directed By: " + response.getString("Director"));
                    writer.setText("Written By: " + response.getString("Writer"));
                    String rslt = response.getString("Type");
                    if (rslt.equals("movie")) {
                        String rsltMovies = response.getString("Production");
                        typeDesc.setVisibility(View.VISIBLE);
                        typeDesc.setText("Produced By: " + rsltMovies);
                    } else if (rslt.equals("series")) {
                        typeDesc.setVisibility(View.VISIBLE);
                        String rsltSeries = response.getString("series");
                        typeDesc.setText(String.valueOf(rslt.charAt(0)).toUpperCase() + rslt.substring(1) + " | Total Seasons: " + rsltSeries);
                    }
                    String award = response.getString("Awards");
                    if (!award.isEmpty()) {
                        awards.setVisibility(View.VISIBLE);
                        awards.setText("Awards: " + award);
                    }
                    String posterURL = response.getString("Poster");
                    Picasso.
                            with(getContext())
                            .load(posterURL)
                            .fit()
                            .error(R.drawable.movie_icon)
                            .placeholder(R.drawable.movie_icon) // can also be a drawable
                            .into(poster);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);

        RequestQueue requestQueue=Volley.newRequestQueue(getContext());
        final JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.getImageDataLeft+id+Constants.getImageDataRight, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array=response.getJSONArray("movie_results");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject objectRequest = array.getJSONObject(i);
                        ImageView backdrpImg=view.findViewById(R.id.backdropImage);
                        String bckdrpImg=Constants.imagePath+objectRequest.getString("backdrop_path");
                        Picasso.
                                with(getContext())
                                .load(bckdrpImg)
                                .noFade()
                                .fit()
                                .into(backdrpImg);
                    }
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

            back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (state_like) {
                    case 0: {
                        if (state_dislike == 1) {
                            like.setBackground(getContext().getDrawable(R.drawable.liked));
                            dislike.setBackground(getContext().getDrawable(R.drawable.dislike));
                            likeTxt.setText("Liked");
                            dislikeTxt.setText("Dislike");
                            Snackbar.make(v, "Great, will suggest similar content!", Snackbar.LENGTH_SHORT)
                                    .setBackgroundTint(Color.WHITE)
                                    .setTextColor(Color.BLACK)
                                    .show();
                            state_like++;
                            state_dislike--;
                        } else if (state_dislike != 1) {
                            like.setBackground(getContext().getDrawable(R.drawable.liked));
                            likeTxt.setText("Liked");
                            Snackbar.make(v, "Great, will suggest similar content!", Snackbar.LENGTH_SHORT)
                                    .setBackgroundTint(Color.WHITE)
                                    .setTextColor(Color.BLACK)
                                    .show();
                            state_like++;
                        }
                        break;
                    }
                    case 1: {
                        like.setBackground(getContext().getDrawable(R.drawable.like));
                        likeTxt.setText("Like");
                        state_like--;
                        break;
                    }
                }
            }
        });
        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (state_dislike) {
                    case 0: {
                        if (state_like == 1) {
                            dislike.setBackground(getContext().getDrawable(R.drawable.disliked));
                            like.setBackground(getContext().getDrawable(R.drawable.like));
                            dislikeTxt.setText("Disliked");
                            likeTxt.setText("Like");
                            Snackbar.make(v, "No problem! Will try get better content for you..", Snackbar.LENGTH_SHORT)
                                    .setBackgroundTint(Color.WHITE)
                                    .setTextColor(Color.BLACK)
                                    .show();
                            state_dislike++;
                            state_like--;
                        } else if (state_like != 1) {
                            dislike.setBackground(getContext().getDrawable(R.drawable.disliked));
                            dislikeTxt.setText("Disliked");
                            Snackbar.make(v, "Will try get better content for you!", Snackbar.LENGTH_SHORT)
                                    .setBackgroundTint(Color.WHITE)
                                    .setTextColor(Color.BLACK)
                                    .show();
                            state_dislike++;
                        }
                        break;
                    }
                    case 1: {
                        dislike.setBackground(getContext().getDrawable(R.drawable.dislike));
                        dislikeTxt.setText("Dislike");
                        state_dislike--;
                        break;
                    }
                }
            }
        });
        watchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (state_watchlist) {
                    case 0: {
                        watchlist.setBackground(getContext().getDrawable(R.drawable.addedto_watchlist));
                        watchlistTxt.setText("Added");
                        Snackbar.make(v, "Added to watchlist", Snackbar.LENGTH_SHORT)
                                .setBackgroundTint(Color.WHITE)
                                .setTextColor(Color.BLACK)
                                .show();
                        state_watchlist++;
                        break;
                    }
                    case 1: {
                        watchlist.setBackground(getContext().getDrawable(R.drawable.addto_watchlist));
                        watchlistTxt.setText("Watchlist");
                        state_watchlist--;
                        break;
                    }
                }
            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackPopup();
            }
        });
        return view;
    }
    private void feedbackPopup() {
        View view = getLayoutInflater().inflate(R.layout.userreview_popup, null);
        final RatingBar bar = (RatingBar) view.findViewById(R.id.rateBar);
        EditText feedbck = (EditText) view.findViewById(R.id.feedbckIp);
        Button submitBtn = (Button) view.findViewById(R.id.submitBtn);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                float rating = bar.getRating();
                if (Math.round(rating) != 0) {
                    userRating.setText(String.valueOf(rating) + "/5");
                    userRating.setVisibility(View.VISIBLE);
                    star.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getContext(), "Too less to rate an effort!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void initializeId(View view) {
        userRating = view.findViewById(R.id.userRating);
        star = view.findViewById(R.id.star);
        back = view.findViewById(R.id.backBtn);
        poster = view.findViewById(R.id.poster);
        movietitle = view.findViewById(R.id.movietitle);
        releaseYr = view.findViewById(R.id.releaseYear);
        duration = view.findViewById(R.id.duration);
        rating = view.findViewById(R.id.imdbrating);
        like = view.findViewById(R.id.like);
        likeTxt = view.findViewById(R.id.likeTxtview);
        dislike = view.findViewById(R.id.dislike);
        dislikeTxt = view.findViewById(R.id.dislikeTxtview);
        watchlist = view.findViewById(R.id.watchlist);
        watchlistTxt = view.findViewById(R.id.watchlistTxtview);
        plot = view.findViewById(R.id.plot);
        genere = view.findViewById(R.id.genere);
        actors = view.findViewById(R.id.actors);
        director = view.findViewById(R.id.directors);
        writer = view.findViewById(R.id.writers);
        typeDesc = view.findViewById(R.id.typeDesc);
        awards = view.findViewById(R.id.awards);
        rate = view.findViewById(R.id.rateBtn);
        play = view.findViewById(R.id.playBtn);
    }
}