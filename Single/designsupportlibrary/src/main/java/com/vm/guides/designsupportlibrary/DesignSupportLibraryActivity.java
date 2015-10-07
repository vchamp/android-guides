package com.vm.guides.designsupportlibrary;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.vm.guides.common.util.FragmentUtil;
import com.vm.guides.designsupportlibrary.fragments.FormFragment;
import com.vm.guides.designsupportlibrary.fragments.ScrollingFragment;

public class DesignSupportLibraryActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String TAG = "DslActivity";

    private static final String NAV_ITEM_ID = "nav_item_id";

    private static final int DEFAULT_NAV_ITEM_ID = R.id.navigation_item_1;
    private static final long DRAWER_CLOSE_DELAY = 250;

    private final Handler drawerActionHandler = new Handler();
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int navItemId;

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_design_support_library);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        findViewById(R.id.floatingActionButton).setOnClickListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            navItemId = savedInstanceState.getInt(NAV_ITEM_ID, DEFAULT_NAV_ITEM_ID);
        } else {
            navItemId = DEFAULT_NAV_ITEM_ID;
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(navItemId);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigate(navItemId);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putInt(NAV_ITEM_ID, navItemId);
    }

    private void navigate(int navItemId) {

        if (navItemId == R.id.navigation_item_1) {
            FragmentUtil.addFragment(this, ScrollingFragment.class, R.id.fragment, true);
        } else if (navItemId == R.id.navigation_item_2) {
            FragmentUtil.addFragment(this, FormFragment.class, R.id.fragment, true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        navItemId = menuItem.getItemId();
        //        menuItem.setChecked(true);
        drawerActionHandler.postDelayed(new Runnable() {

            @Override
            public void run() {

                drawerLayout.closeDrawer(GravityCompat.START);
                navigate(navItemId);
            }
        }, DRAWER_CLOSE_DELAY);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    //    @Override
    //    public boolean onOptionsItemSelected(final MenuItem item) {
    //        if (item.getItemId() == android.support.v7.appcompat.R.id.home) {
    //            return drawerToggle.onOptionsItemSelected(item);
    //        }
    //        return super.onOptionsItemSelected(item);
    //    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.floatingActionButton) {
            Snackbar.make(coordinatorLayout, R.string.snackbar_text, Snackbar.LENGTH_LONG).setAction(R.string
                    .snackbar_action, snackbarActionListener).show();
        }
    }

    private View.OnClickListener snackbarActionListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Log.i(TAG, "Snackbar action");
        }
    };
}
