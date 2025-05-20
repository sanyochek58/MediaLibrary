package com.example.homemedialibrary;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.homemedialibrary.Fragments.BookFragment;
import com.example.homemedialibrary.Fragments.MovieFragment;
import com.example.homemedialibrary.Fragments.MusicFragment;
import com.example.homemedialibrary.Fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnItemSelectedListener(this::SelectItem);

    }

    private Boolean SelectItem(MenuItem item){
        Fragment fragment;

        if(item.getItemId() == R.id.nav_books){
            fragment = new BookFragment();
        } else if (item.getItemId() == R.id.nav_movies) {
            fragment = new MovieFragment();
        } else if (item.getItemId() == R.id.nav_music) {
            fragment = new MusicFragment();
        } else if (item.getItemId() == R.id.nav_settings) {
            fragment = new SettingsFragment();
        }else {
            fragment = new BookFragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();

        return true;
    }
}