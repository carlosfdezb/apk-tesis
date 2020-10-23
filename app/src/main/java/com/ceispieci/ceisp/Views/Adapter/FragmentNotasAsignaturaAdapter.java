package com.ceispieci.ceisp.Views.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ceispieci.ceisp.Data.Model.Nota;
import com.ceispieci.ceisp.Data.Model.Noticia;
import com.ceispieci.ceisp.Data.Model.Promedio;
import com.ceispieci.ceisp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FragmentNotasAsignaturaAdapter extends RecyclerView.Adapter<FragmentNotasAsignaturaAdapter.MyViewHolder> {

    private List<Nota> notas;

    public FragmentNotasAsignaturaAdapter(List<Nota> notas) {
        this.notas = notas;
    }

    @NonNull
    @Override
    public FragmentNotasAsignaturaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_notas,parent,false);
        return new FragmentNotasAsignaturaAdapter.MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentNotasAsignaturaAdapter.MyViewHolder holder, int position) {

        final Nota _objNota = notas.get(position);

        holder.tvNumero_nota.setText(_objNota.getNumero());
        if(Double.parseDouble(_objNota.getNota()) > 3.9){
            holder.tvNota.setText(_objNota.getNota());
            holder.tvNota.setTextColor(Color.parseColor("#2A3888"));
        }else{
            holder.tvNota.setText(_objNota.getNota());
            holder.tvNota.setTextColor(Color.parseColor("#D62424"));
        }
        holder.tvDescripcion_nota.setText(_objNota.getDescripcion());


    }

    @Override
    public int getItemCount() {
        int i = 0;
        if(notas.size() > 0){
            i = notas.size();
        }

        return i;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvNumero_nota, tvNota, tvDescripcion_nota;

        public MyViewHolder(View view){
            super(view);

            tvNumero_nota = view.findViewById(R.id.tvNumero_nota);
            tvNota = view.findViewById(R.id.tvNota);
            tvDescripcion_nota = view.findViewById(R.id.tvDescripcion_nota);


        }

    }
}
