package com.example.navigationdrawerdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import kotlin.contracts.Returns;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.nav_view);

        navigationView.setItemIconTintList(null); //it removes the default background of menu icon
        setUpNavContent(navigationView);
    }
    private void setUpNavContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                setScreen(item);
                return true;
            }
        });
    }

    private void setScreen(MenuItem item){
        Fragment fragment=null;
        Class fragmentClass;
        if (item.getItemId() == R.id.java) {
            fragmentClass = JavaFragment.class;
        }else if(item.getItemId()==R.id.kotlin){
            fragmentClass = KotlinFragment.class;
        }else if(item.getItemId()== R.id.flutter){
            fragmentClass = FlutterFragment.class;
        }else{
            fragmentClass = JavaFragment.class;
        }
        try {
            fragment=(Fragment) fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frlayout,fragment).commit();

        item.setChecked(true);

        setTitle("SWIPE TO CHECK OPTIONS");

        drawerLayout.closeDrawers();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: drawerLayout.openDrawer(GravityCompat.START); // this is for the " <- " button that
                //android adds itself with toolbar when line 31 code is written
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}