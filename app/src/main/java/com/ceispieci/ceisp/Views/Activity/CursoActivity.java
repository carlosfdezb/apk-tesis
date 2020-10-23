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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.ceispieci.ceisp.Data.Api.Api;
import com.ceispieci.ceisp.Data.Model.Calendario;
import com.ceispieci.ceisp.Data.Model.Curso;
import com.ceispieci.ceisp.Data.Preferences.SessionPreferences;
import com.ceispieci.ceisp.R;
import com.ceispieci.ceisp.Views.Adapter.CalendarioAdapter;
import com.ceispieci.ceisp.Views.Adapter.CursoAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CursoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SessionPreferences prefs;
    private ProgressDialog pdDialogo;
    private List<Curso> cursos;

    private RecyclerView cursoRecycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar pbCarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        prefs = new SessionPreferences(getApplicationContext());

        getSupportActionBar().setTitle("Datos del curso");

        getCurso();

        cursoRecycler = findViewById(R.id.CursoRecycler);
        cursoRecycler.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 1);
        cursoRecycler.setLayoutManager(layoutManager);
    }

    private void getCurso(){
        prefs = new SessionPreferences(getApplicationContext());
        Call<List<Curso>> callCursos= Api.getApi().getCurso(prefs.getUsuario().getId());
        callCursos.enqueue(new Callback<List<Curso>>() {
            @Override
            public void onResponse(Call<List<Curso>> call, Response<List<Curso>> response) {
                cursos = response.body();
                adapter = new CursoAdapter(cursos);
                cursoRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Curso>> call, Throwable t) {
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
            pdDialogo = ProgressDialog.show(CursoActivity.this,"Cerrando sesión","Borrando datos...",true,false);
            prefs.cerrarSesion();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pdDialogo.dismiss();
                    Intent intent = new Intent(CursoActivity.this,LoginActivity.class);
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
