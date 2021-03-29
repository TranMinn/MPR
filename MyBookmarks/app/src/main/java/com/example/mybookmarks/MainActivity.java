package com.example.mybookmarks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init Fragment
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = new BookmarksFragment();

        manager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}