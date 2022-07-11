package com.example.shall_we_merge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.example.shall_we_merge.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //NavController navController = Navigation.findNavController(this, R.id.myNavHostFragment);
        //drawerLayout = binding.drawerLayout;
        //NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout);
        //NavigationUI.setupWithNavController(binding.navView, navController);
    }

//    @Override
//    public boolean onSupportNavigateUp(){
//        NavController navController = Navigation.findNavController(this, R.id.myNavHostFragment);
//        return NavigationUI.navigateUp(navController, drawerLayout);
//    }

}