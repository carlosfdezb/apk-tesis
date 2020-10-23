package com.ceispieci.ceisp.Views.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.ceispieci.ceisp.Data.Api.Api;
import com.ceispieci.ceisp.Data.Model.Asignatura;
import com.ceispieci.ceisp.Data.Model.Calendario;
import com.ceispieci.ceisp.Data.Preferences.SessionPreferences;
import com.ceispieci.ceisp.R;
import com.ceispieci.ceisp.Views.Adapter.AsignaturaAdapter;
import com.ceispieci.ceisp.Views.Adapter.CalendarioAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarioActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SessionPreferences prefs;
    private ProgressDialog pdDialogo;
    private List<Calendario> calendarios;

    private RecyclerView calendarioRecycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar pbCarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        prefs = new SessionPreferences(getApplicationContext());

        getSupportActionBar().setTitle("Calendario");

        getCalendario();

        calendarioRecycler = findViewById(R.id.CalendarioRecycler);
        calendarioRecycler.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 1);
        calendarioRecycler.setLayoutManager(layoutManager);
;
    }

    private void getCalendario(){
        prefs = new SessionPreferences(getApplicationContext());
        Call<List<Calendario>> callCalendarios= Api.getApi().getCalendario(prefs.getUsuario().getId());
        callCalendarios.enqueue(new Callback<List<Calendario>>() {
            @Override
            public void onResponse(Call<List<Calendario>> call, Response<List<Calendario>> response) {
                calendarios = response.body();
                adapter = new CalendarioAdapter(calendarios);
                calendarioRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Calendario>> call, Throwable t) {
            }
        });
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.cerrar_sesion) {
            pdDialogo = ProgressDialog.show(CalendarioActivity.this,"Cerrando sesión","Borrando datos...",true,false);
            prefs.cerrarSesion();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pdDialogo.dismiss();
                    Intent intent = new Intent(CalendarioActivity.this,LoginActivity.class);
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
