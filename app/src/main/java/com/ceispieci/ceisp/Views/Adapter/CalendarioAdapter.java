package com.ceispieci.ceisp.Views.Adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ceispieci.ceisp.Data.Model.Asignatura;
import com.ceispieci.ceisp.Data.Model.Calendario;
import com.ceispieci.ceisp.Data.Model.Noticia;
import com.ceispieci.ceisp.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CalendarioAdapter extends RecyclerView.Adapter<CalendarioAdapter.MyViewHolder>{

    private List<Calendario> calendarios;

    public CalendarioAdapter(List<Calendario> calendarios) {
        this.calendarios = calendarios;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_calendario,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarioAdapter.MyViewHolder holder, int position) {

        final Calendario _objCalendario = calendarios.get(position);

        DateFormat diaFormato = new SimpleDateFormat("dd");
        DateFormat mesFormato = new SimpleDateFormat("MMMM");
        holder.tvFecha.setText(diaFormato.format(_objCalendario.getFecha()));
        holder.tvMes.setText(mesFormato.format(_objCalendario.getFecha()));
        holder.tvDescripcion.setText(_objCalendario.getDescripcion());
        holder.tvAsignatura.setText(_objCalendario.getAsignatura().getNombre());

    }

    @Override
    public int getItemCount() {
        int i = 0;
        if(calendarios.size() > 0){
            i = calendarios.size();
        }

        return i;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvFecha, tvMes, tvDescripcion, tvAsignatura;

        public MyViewHolder(View view){
            super(view);

            tvFecha = view.findViewById(R.id.tvFecha_calendario);
            tvMes = view.findViewById(R.id.tvMes_calendario);
            tvDescripcion = view.findViewById(R.id.tvDescripcion_calendario);
            tvAsignatura = view.findViewById(R.id.tvAsignatura_calendario);



        }

    }
}
