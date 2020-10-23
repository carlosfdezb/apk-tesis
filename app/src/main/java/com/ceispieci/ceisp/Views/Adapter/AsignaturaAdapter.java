package com.ceispieci.ceisp.Views.Adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ceispieci.ceisp.Data.Model.Asignatura;
import com.ceispieci.ceisp.Data.Model.Noticia;
import com.ceispieci.ceisp.R;
import com.ceispieci.ceisp.Views.Activity.AsignaturaDetalleActivity;
import com.ceispieci.ceisp.Views.Activity.AsignaturasActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AsignaturaAdapter extends RecyclerView.Adapter<AsignaturaAdapter.MyViewHolder>{

    private List<Asignatura> asignaturas;
    private AsignaturaAdapterListener listener;

    public AsignaturaAdapter(List<Asignatura> asignaturas, AsignaturaAdapterListener listener) {
        this.asignaturas = asignaturas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_asignaturas,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull AsignaturaAdapter.MyViewHolder holder, int position) {

        final Asignatura _objAsignatura = asignaturas.get(position);
        holder.tvNombre_asignatura.setText(_objAsignatura.getNombre());
        holder.tvNombre_profesor.setText("Prof. "+_objAsignatura.getPrimer_nombre()+" "+_objAsignatura.getApellido_paterno());
        Picasso.get().load("http://192.168.0.9/Tesis/public/android/resources/"+_objAsignatura.getAsignatura_id()+".png").into(holder.imgAsignatura);


    }

    @Override
    public int getItemCount() {
        int i = 0;
        if(asignaturas.size() > 0){
            i = asignaturas.size();
        }

        return i;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imgAsignatura;
        public TextView tvNombre_asignatura, tvNombre_profesor;

        public MyViewHolder(View view){
            super(view);

            imgAsignatura = view.findViewById(R.id.imgAsignatura);
            tvNombre_asignatura = view.findViewById(R.id.tvNombre_asignatura);
            tvNombre_profesor = view.findViewById(R.id.tvNombre_profesor);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.OnItemClicked(asignaturas.get(getAdapterPosition()).getAsignatura_id());

        }
    }

    public interface AsignaturaAdapterListener{
        void OnItemClicked(int id);
    }
}
