package com.ceispieci.ceisp.Views.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ceispieci.ceisp.Data.Model.Noticia;
import com.ceispieci.ceisp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.MyViewHolder>{

    private List<Noticia> noticias;

    public NoticiaAdapter(List<Noticia> noticias) {
        this.noticias = noticias;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_noticia,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Noticia _objNoticia = noticias.get(position);

        holder.tvTitulo.setText(_objNoticia.getTitulo());
        holder.tvDescripcion.setText(_objNoticia.getDescripcion());
        Picasso.get().load("http://192.168.0.9/Tesis/public/noticias/"+_objNoticia.getImagen()).into(holder.imgNoticia);


    }

    @Override
    public int getItemCount() {
        int i = 0;
        if(noticias.size() > 0){
            i = noticias.size();
        }

        return i;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgNoticia;
        public TextView tvTitulo, tvDescripcion;

        public MyViewHolder(View view){
            super(view);

            imgNoticia = view.findViewById(R.id.imgNoticia);
            tvTitulo = view.findViewById(R.id.tvTitulo);
            tvDescripcion = view.findViewById(R.id.tvDescripcion);


        }

    }
}
