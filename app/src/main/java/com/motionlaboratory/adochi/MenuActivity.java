package com.motionlaboratory.adochi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.HashMap;

import customfont.MyTextView;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SessionManager session;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment fragment = null;
    String email_I;
    DBHelperConfig myDb;
    String uname_I;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        session = new SessionManager(getApplicationContext());
        HashMap<String,String> user = session.getUserDetails();

        if(session.checkLogin()) {
            finish();
        }


        String email_get = user.get(SessionManager.KEY_EMAIL); //iki get session e email
//        Intent email = getIntent();
        email_I = email_get;


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getFragmentManager();

        if (savedInstanceState == null) {
            fragment = new BerandaFragment();
            callFragment(fragment);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(drawer.isDrawerOpen(GravityCompat.END)){
            finish();
            System.exit(0);
        }else{

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_transaksi){
            Intent i = new Intent(this,TransaksiActivity.class);
            startActivity(i);
            return true;
        }else if(id == R.id.action_notif){
            Intent i = new Intent(this,NotificationActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Bundle bundle = new Bundle();

        if (id == R.id.nav_profile) {
            fragment = new ProfileFragment();
            bundle.putString("email_key", email_I);

            fragment.setArguments(bundle);
            callFragment(fragment);

        } else if (id == R.id.nav_beranda) {
            fragment = new ProfileFragment();
            bundle.putString("email_key", email_I);

            fragment.setArguments(bundle);
            callFragment(fragment);

        } else if (id == R.id.nav_donasi) {
            fragment = new DonasiFragment();
            bundle.putString("email_key", email_I);

            fragment.setArguments(bundle);
            callFragment(fragment);

        } else if (id == R.id.nav_adobsi) {
            fragment = new AdopsiFragment();
            bundle.putString("email_key", email_I);
            bundle.putString("nameuser_key", uname_I);

            fragment.setArguments(bundle);
            callFragment(fragment);

        } else if (id == R.id.nav_help) {
            fragment = new HelpFragment();
            bundle.putString("email_key", email_I);

            fragment.setArguments(bundle);
            callFragment(fragment);

        } else if (id == R.id.nav_logout) {
//            myDb = new DBHelperConfig(this);

            session.logoutUser();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    // untuk mengganti isi kontainer menu yang dipiih
    private void callFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.remove(fragment);
        fragmentTransaction.replace(R.id.fragment_layout, fragment);
        fragmentTransaction.commit();
    }

}