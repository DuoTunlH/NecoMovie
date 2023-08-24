package com.example.necomovie;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.necomovie.ui.dashboard.DashboardFragment;
import com.example.necomovie.ui.home.HomeFragment;
import com.example.necomovie.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.necomovie.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    final Fragment homeFragment = new HomeFragment();
    final Fragment dashboardFragment = new DashboardFragment();
    final Fragment notificationsFragment = new NotificationsFragment();
    private Fragment active = homeFragment;
    FragmentManager fragmentManager = getSupportFragmentManager();

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpFragments();
        replaceFragment(homeFragment);
        binding.navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    replaceFragment(homeFragment);
                } else if (itemId == R.id.navigation_upcoming) {
                    replaceFragment(dashboardFragment);
                } else if (itemId == R.id.navigation_search) {
                   replaceFragment(notificationsFragment);
                }
                return true;
            }
        });
        //BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_upcoming, R.id.navigation_search)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
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