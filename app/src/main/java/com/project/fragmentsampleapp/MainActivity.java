package com.project.fragmentsampleapp;

import Model.MovieData;
import Util.Constants;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private static final String PREF_FILE="user query";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        boolean connection=isNetworkAvailable();
        if(connection){
            addFragment(new home_fragment());
        }else{
            addFragment(new networkfailure());
        }
    }
    public boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager=(ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return networkInfo !=null;
    }
    public void addFragment(Fragment fragment){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.search){
            addFragment(new BlankFragment());
            SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
            searchView.setQueryHint("search movies, series or people");
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    preferences=getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
                    preferences.edit().putString("query",query).commit();
                    addFragment(new searchfragment());
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
            searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){
                        addFragment(new BlankFragment());
                    }else{
                        addFragment(new home_fragment());
                    }
                }
            });
        }
        if(item.getItemId()==R.id.account){
            Intent intent=new Intent(this,movieDetails.class);
            intent.putExtra("choice","profile");
            startActivity(intent);
        }
        if(item.getItemId()==R.id.history){
            Intent intent=new Intent(this,historyPage.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.help){
            Intent intent=new Intent(this,movieDetails.class);
            intent.putExtra("choice","help");
            startActivity(intent);
        }
        if(item.getItemId()==R.id.about){
            Intent intent=new Intent(this,movieDetails.class);
            intent.putExtra("choice","about");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}