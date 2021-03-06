package com.example.mobizone.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mobizone.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * @author Patel Dhruv
 * @author Gakhar Tanvi
 * @author Kaur Sarbjit
 * @author Kaur Kamaljit
 * @author Varma Akshay
 * Java class for Dash activity
 */
public class DashActivity extends AppCompatActivity {

    /**
     * Variable for BottomNavigation
     */
    BottomNavigationView bottomNav;

    /**
     * Variable for navController
     */
    NavController navController;

    /**
     * onCreate method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        bottomNav=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListner);
    }

    /**
     * BottomNavigation item click listener
     */
    private BottomNavigationView.OnNavigationItemSelectedListener  navListner=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.fragment_home:
                            navController= Navigation.findNavController(DashActivity.this,R.id.nav_host_dash);
                            navController.navigate(R.id.homeFragment);
                            break;
                        case R.id.fragment_cart:
                            navController=Navigation.findNavController(DashActivity.this,R.id.nav_host_dash);
                            navController.navigate(R.id.cartFragment);
                            break;
                        case R.id.fragment_profile:
                            navController=Navigation.findNavController(DashActivity.this,R.id.nav_host_dash);
                            navController.navigate(R.id.profileFragment);
                            break;
                    }
                    return true;
                }
            };

}