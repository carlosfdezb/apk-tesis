package com.ceispieci.ceisp.Views.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ceispieci.ceisp.Data.Api.Api;
import com.ceispieci.ceisp.Data.Model.Alumno;
import com.ceispieci.ceisp.Data.Model.Asignatura;
import com.ceispieci.ceisp.Data.Model.Nota;
import com.ceispieci.ceisp.Data.Model.Promedio;
import com.ceispieci.ceisp.Data.Preferences.SessionPreferences;
import com.ceispieci.ceisp.R;
import com.ceispieci.ceisp.Views.Adapter.AlumnoAdapter;
import com.ceispieci.ceisp.Views.Adapter.FragmentNotasAsignaturaAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentNotasAsignatura extends Fragment {
    private List<Nota> notas;
    private SessionPreferences prefs;
    private RecyclerView notasRecycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notas_asignatura, container, false);


        notasRecycler = view.findViewById(R.id.NotasRecycler);
        notasRecycler.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getContext(), 1);
        notasRecycler.setLayoutManager(layoutManager);
        getNotas();
        return notasRecycler;
    }

    private void getNotas(){
        prefs = new SessionPreferences(getActivity().getApplicationContext());
        Call<List<Nota>> callNota= Api.getApi().getNotas(prefs.getUsuario().getId(), getActivity().getIntent().getIntExtra("asignatura_id",0));
        callNota.enqueue(new Callback<List<Nota>>() {
            @Override
            public void onResponse(Call<List<Nota>> call, Response<List<Nota>> response) {
                notas = response.body();
                adapter = new FragmentNotasAsignaturaAdapter(notas);
                notasRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Nota>> call, Throwable t) {
            }
        });
    }
}
