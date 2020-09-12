package com.project.fragmentsampleapp;

import Util.Constants;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class player extends YouTubeBaseActivity {
    YouTubePlayerView playerView;
    public static final String API_KEY = Constants.ytApiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Bundle bundle=getIntent().getExtras();
        final String VIDEO_ID=bundle.getString("movie_id");
        playerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        playerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {
                    player.loadVideo(VIDEO_ID);
                    player.setFullscreen(true);
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(player.this, youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}