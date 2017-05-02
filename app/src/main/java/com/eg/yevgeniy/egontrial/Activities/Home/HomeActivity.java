package com.eg.yevgeniy.egontrial.Activities.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.eg.yevgeniy.egontrial.Activities.Bill_Share.GraphHolderFragment;
import com.eg.yevgeniy.egontrial.Activities.Dashboard.CustomerFragment;
import com.eg.yevgeniy.egontrial.Activities.E_Car.CarShare;
import com.eg.yevgeniy.egontrial.Activities.Login.LoginActivity;
import com.eg.yevgeniy.egontrial.Activities.ManageEnergy.ManageFragment;
import com.eg.yevgeniy.egontrial.Activities.Moniter.LightsFragment;
import com.eg.yevgeniy.egontrial.Activities.Moniter.MainMoniterFrag;
import com.eg.yevgeniy.egontrial.Activities.Moniter.OvenFragment;
import com.eg.yevgeniy.egontrial.Activities.Moniter.TVFragment;
import com.eg.yevgeniy.egontrial.Activities.Moniter.VehicleFragment;
import com.eg.yevgeniy.egontrial.Activities.Moniter.WasherFragment;
import com.eg.yevgeniy.egontrial.R;

public class HomeActivity extends LoginActivity implements NavigationView.OnNavigationItemSelectedListener  {

    MainMoniterFrag mainMoniterFrag;
    public DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //listViewArtists = (ListView) findViewById(R.id.listViewArtists);
        //personList = new ArrayList<>();
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CustomerFragment customerFragment = new CustomerFragment();
        getSupportFragmentManager().findFragmentById(R.id.containerMain);


        getSupportFragmentManager().beginTransaction().add(R.id.containerMain, customerFragment).commit();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_e_car) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerMain, new CarShare())
                    .commit();

        }else if (id == R.id.nav_dash) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerMain, new CustomerFragment())
                    .commit();

        }else if (id == R.id.nav_bill_share) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerMain, new GraphHolderFragment())
                    .commit();

        }else if (id == R.id.manage) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerMain, new ManageFragment())
                    .commit();


        } else if (id == R.id.heater) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerMain, new MainMoniterFrag())
                    .commit();

        } else if (id == R.id.e_car) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerMain, new VehicleFragment())
                    .commit();
        }
        else if (id == R.id.wash) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerMain, new WasherFragment())
                    .commit();
        }
        else if (id == R.id.oven) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerMain, new OvenFragment())
                    .commit();
        }
        else if (id == R.id.tv) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerMain, new TVFragment())
                    .commit();
        }
        else if (id == R.id.lights) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerMain, new LightsFragment())
                    .commit();


        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void closeDrawer(){
        drawerLayout.closeDrawers();
    }

    @Override
    public void onBackPressed() {
        ;
    }
}
