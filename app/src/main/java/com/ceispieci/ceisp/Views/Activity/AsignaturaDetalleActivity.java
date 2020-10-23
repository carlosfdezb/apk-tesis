package com.ceispieci.ceisp.Views.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.ceispieci.ceisp.Data.Api.Api;
import com.ceispieci.ceisp.Data.Model.Asignatura;
import com.ceispieci.ceisp.Data.Preferences.SessionPreferences;
import com.ceispieci.ceisp.R;
import com.ceispieci.ceisp.Views.Adapter.AsignaturaDetalleViewPagerAdapter;
import com.ceispieci.ceisp.Views.Fragment.FragmentDetalleAsignatura;
import com.ceispieci.ceisp.Views.Fragment.FragmentNotasAsignatura;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsignaturaDetalleActivity extends AppCompatActivity {

    private SessionPreferences prefs;
    private TabLayout tabAsignatura;
    private ViewPager pagerAsignatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignatura_detalle);

        prefs = new SessionPreferences(getApplicationContext());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setElevation(0);
        actionBar.setTitle("Asignatura");
        pagerAsignatura = findViewById(R.id.pagerAsignatura);
        setupViewPager(pagerAsignatura);

        tabAsignatura = findViewById(R.id.tabAsignatura);
        tabAsignatura.setupWithViewPager(pagerAsignatura);


    }

    private void setupViewPager(ViewPager vp){
        AsignaturaDetalleViewPagerAdapter adapter = new AsignaturaDetalleViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentDetalleAsignatura(), "DETALLE");
        adapter.addFragment(new FragmentNotasAsignatura(), "NOTAS");
        vp.setAdapter(adapter);
    }



}
