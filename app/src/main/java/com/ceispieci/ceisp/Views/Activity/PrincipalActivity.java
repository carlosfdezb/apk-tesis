package com.ceispieci.ceisp.Views.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ceispieci.ceisp.Data.Api.Api;

import com.ceispieci.ceisp.Data.Model.Noticia;
import com.ceispieci.ceisp.Data.Preferences.SessionPreferences;

import com.ceispieci.ceisp.R;

import com.ceispieci.ceisp.Views.Adapter.NoticiaAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SessionPreferences prefs;
    private ProgressDialog pdDialogo;
    private List<Noticia> noticias;



    private RecyclerView noticiaRecycler;
    private RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    private ProgressBar pbCarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        prefs = new SessionPreferences(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("CEISP");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View header = navigationView.getHeaderView(0);
        TextView tvNombre = header.findViewById(R.id.tvNombre);
        TextView tvEmail = header.findViewById(R.id.tvEmail);

        tvNombre.setText(prefs.getUsuario().getName());
        tvEmail.setText(prefs.getUsuario().getEmail());
        pbCarga = findViewById(R.id.pbCarga);




        getNoticias();
        noticiaRecycler = findViewById(R.id.noticiaRecycler);
        noticiaRecycler.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        noticiaRecycler.setLayoutManager(layoutManager);




    }

    private void getNoticias(){
        Call<List<Noticia>> callNoticias = Api.getApi().noticias();
        callNoticias.enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {
                noticias = response.body();
                pbCarga.setVisibility(GONE);
                adapter = new NoticiaAdapter(noticias);
                noticiaRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {

            }
        });
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_asignaturas) {
            Intent i = new Intent(PrincipalActivity.this,AsignaturasActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_calendario) {
            Intent i = new Intent(PrincipalActivity.this,CalendarioActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_curso) {
            Intent i = new Intent(PrincipalActivity.this,CursoActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_alumno) {
            Intent i = new Intent(PrincipalActivity.this,AlumnoActivity.class);
            startActivity(i);
        } else if (id == R.id.cerrar_sesion) {
            pdDialogo = ProgressDialog.show(PrincipalActivity.this,"Cerrando sesión","Borrando datos...",true,false);
            prefs.cerrarSesion();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pdDialogo.dismiss();
                    Intent intent = new Intent(PrincipalActivity.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            },2000);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}