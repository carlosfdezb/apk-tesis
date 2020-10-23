package com.ceispieci.ceisp.Views.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ceispieci.ceisp.Data.Api.Api;
import com.ceispieci.ceisp.Data.Model.Asignatura;
import com.ceispieci.ceisp.Data.Model.Noticia;
import com.ceispieci.ceisp.Data.Preferences.SessionPreferences;
import com.ceispieci.ceisp.R;
import com.ceispieci.ceisp.Views.Adapter.AsignaturaAdapter;
import com.ceispieci.ceisp.Views.Adapter.NoticiaAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class AsignaturasActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AsignaturaAdapter.AsignaturaAdapterListener {

    private SessionPreferences prefs;
    private ProgressDialog pdDialogo;
    private List<Asignatura> asignaturas;

    private RecyclerView asignaturaRecycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar pbCarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);

        prefs = new SessionPreferences(getApplicationContext());

        getSupportActionBar().setTitle("Mis asignaturas");

        getAsignaturas();

        asignaturaRecycler = findViewById(R.id.AsignaturasRecycler);
        asignaturaRecycler.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 1);
        asignaturaRecycler.setLayoutManager(layoutManager);
    }


    private void getAsignaturas(){
        prefs = new SessionPreferences(getApplicationContext());
        Call<List<Asignatura>> callAsignaturas = Api.getApi().getAsignaturas(prefs.getUsuario().getId());
        callAsignaturas.enqueue(new Callback<List<Asignatura>>() {
            @Override
            public void onResponse(Call<List<Asignatura>> call, Response<List<Asignatura>> response) {
                asignaturas = response.body();
                adapter = new AsignaturaAdapter(asignaturas, AsignaturasActivity.this);
                asignaturaRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Asignatura>> call, Throwable t) {

            }
        });
    }

    @Override
    public void OnItemClicked(int id) {
         Log.d("TEST", "OnItemClicked: "+id);
        Intent i = new Intent(AsignaturasActivity.this, AsignaturaDetalleActivity.class);
        i.putExtra("asignatura_id",id);
        startActivity(i);
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
            pdDialogo = ProgressDialog.show(AsignaturasActivity.this,"Cerrando sesión","Borrando datos...",true,false);
            prefs.cerrarSesion();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pdDialogo.dismiss();
                    Intent intent = new Intent(AsignaturasActivity.this,LoginActivity.class);
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
