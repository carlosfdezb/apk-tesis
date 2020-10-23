package com.ceispieci.ceisp.Views.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import com.ceispieci.ceisp.Data.Api.Api;

import com.ceispieci.ceisp.Data.Model.Asignatura;
import com.ceispieci.ceisp.Data.Model.Promedio;
import com.ceispieci.ceisp.Data.Preferences.SessionPreferences;
import com.ceispieci.ceisp.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDetalleAsignatura extends Fragment{

    private SessionPreferences prefs;
    ImageView imgAsignatura_detalle;
    TextView tvTitulo_detalle, tvProfesor_nombre_detalle, tvProfesor_rut_detalle, tvProfesor_correo_detalle, tvNota_promedio_parcial;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new SessionPreferences(getActivity().getApplicationContext());

        getAsignatura();
        getPromedio();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_asignatura,container,false);
        imgAsignatura_detalle = view.findViewById(R.id.imgAsignatura_detalle);
        tvTitulo_detalle = view.findViewById(R.id.tvTitulo_detalle);
        tvProfesor_nombre_detalle = view.findViewById(R.id.tvProfesor_nombre_detalle);
        tvProfesor_rut_detalle = view.findViewById(R.id.tvProfesor_rut_detalle);
        tvProfesor_correo_detalle = view.findViewById(R.id.tvProfesor_correo_detalle);
        tvNota_promedio_parcial = view.findViewById(R.id.tvNota_promedio_parcial);


        return view;
    }

    public void getAsignatura(){
        Call<Asignatura> callAsignatura = Api.getApi().getAsignatura(prefs.getUsuario().getId(), getActivity().getIntent().getIntExtra("asignatura_id",0));
        callAsignatura.enqueue(new Callback<Asignatura>() {
            @Override
            public void onResponse(Call<Asignatura> call, Response<Asignatura> response) {
                Picasso.get().load("http://192.168.0.9/Tesis/public/android/resources/background/"+response.body().getAsignatura_id()+".png").into(imgAsignatura_detalle);
                tvTitulo_detalle.setText(response.body().getNombre());
                tvProfesor_nombre_detalle.setText(response.body().getPrimer_nombre()+" "+response.body().getApellido_paterno()+" "+response.body().getApellido_materno());
                tvProfesor_rut_detalle.setText(response.body().getRut());
                tvProfesor_correo_detalle.setText(response.body().getCorreo());

            }

            @Override
            public void onFailure(Call<Asignatura> call, Throwable t) {

            }
        });
    }

    public void getPromedio(){
        Call<Promedio> callPromedio = Api.getApi().getPromedio(prefs.getUsuario().getId(), getActivity().getIntent().getIntExtra("asignatura_id",0));
        callPromedio.enqueue(new Callback<Promedio>() {
            @Override
            public void onResponse(Call<Promedio> call, Response<Promedio> response) {
                if(Double.parseDouble(response.body().getPromedio()) > 3.9){
                    tvNota_promedio_parcial.setText(response.body().getPromedio());
                    tvNota_promedio_parcial.setTextColor(Color.parseColor("#2A3888"));
                }else{
                    tvNota_promedio_parcial.setText(response.body().getPromedio());
                    tvNota_promedio_parcial.setTextColor(Color.parseColor("#D62424"));
                }

            }

            @Override
            public void onFailure(Call<Promedio> call, Throwable t) {

            }
        });
    }
}
