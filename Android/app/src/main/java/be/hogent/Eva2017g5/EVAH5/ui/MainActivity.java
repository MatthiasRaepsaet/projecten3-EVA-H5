package be.hogent.Eva2017g5.EVAH5.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import  be.hogent.Eva2017g5.EVAH5.ui.fragments.*;
import be.hogent.Eva2017g5.R;

public class MainActivity extends Activity implements NavigationView.OnNavigationItemSelectedListener {
    private FrameLayout contentPanelFrame;
    private FrameLayout contentFrame;
    private Menu menu;

    // index to identify current nav menu item
    public static int navItemIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Bundle bundle = this.getIntent().getExtras();
        FragmentManager fragmentManager =  getFragmentManager();

        contentFrame = (FrameLayout) findViewById(R.id.content_navigation);
        contentPanelFrame = (FrameLayout) findViewById(R.id.content_minor_panel);
        Fragment frag = new OverEVAFragment();

        fragmentManager.beginTransaction().replace(R.id.content_navigation, frag).addToBackStack(null).commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //    setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
      //  TextView username = (TextView) header.findViewById(R.id.username);
       // username.setText(bundle.getString("USERNAME"));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        switch (id){
            case R.id.nav_challenges: fragment = (Fragment) new ChallengesFragment();
                break;
            case R.id.nav_overEva: fragment = new OverEVAFragment();
                break;
            case R.id.nav_vegvsVeg: fragment = new VegvsVegFragment();
                break;
            case R.id.nav_recipes: fragment = new RecipesFragment();
                break;
            case R.id.nav_logout:
                Intent navIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(navIntent);
                return true;
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_navigation, fragment).commit();

        item.setChecked(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }







}
