package com.project.fragmentsampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.reflect.Method;

public class splash_screen extends AppCompatActivity {
    ProgressBar progressBar;
    TextView splashTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar=findViewById(R.id.progressBar);
        splashTxt=findViewById(R.id.splashTxt);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashTxt.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
            }
        },1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splash_screen.this,MainActivity.class));
                finish();
            }
        },2200);
    }
}