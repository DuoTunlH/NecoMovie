package com.example.necomovie;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.necomovie.ui.favourite.FavouriteFragment;
import com.example.necomovie.ui.home.HomeFragment;
import com.example.necomovie.ui.search.SearchFragment;
import com.google.android.material.appbar.MaterialToolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.necomovie.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    final Fragment homeFragment = new HomeFragment();
    final Fragment dashboardFragment = new FavouriteFragment();
    final Fragment notificationsFragment = new SearchFragment();
    private Fragment active = homeFragment;
    FragmentManager fragmentManager = getSupportFragmentManager();

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContentView(binding.getRoot());
        setUpFragments();
        replaceFragment(homeFragment);
        binding.navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    replaceFragment(homeFragment);
                } else if (itemId == R.id.navigation_favourite) {
                    replaceFragment(dashboardFragment);
                } else if (itemId == R.id.navigation_search) {
                   replaceFragment(notificationsFragment);
                }
                return true;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(active).show(fragment).commit();
        active = fragment;
    }

    private void setUpFragments() {
        fragmentManager.beginTransaction().add(R.id.frame_layout, notificationsFragment).hide(notificationsFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frame_layout, dashboardFragment).hide(dashboardFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frame_layout, homeFragment).commit();
    }

}