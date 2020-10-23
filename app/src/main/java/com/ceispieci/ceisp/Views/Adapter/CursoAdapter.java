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
import com.ceispieci.ceisp.Data.Model.Curso;
import com.ceispieci.ceisp.Data.Model.Noticia;
import com.ceispieci.ceisp.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.MyViewHolder>{

    private List<Curso> cursos;

    public CursoAdapter(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @NonNull
    @Override
    public CursoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_curso,parent,false);
        return new CursoAdapter.MyViewHolder(itemview);
    }


    @Override
    public void onBindViewHolder(@NonNull CursoAdapter.MyViewHolder holder, int position) {

        final Curso _objCursos = cursos.get(position);

        holder.tvCurso.setText(_objCursos.getNivel()+"° "+_objCursos.getGrado()+" "+_objCursos.getLetra());
        holder.tvNombre_profesor_jefe.setText(_objCursos.getProfesor().getPrimer_nombre()+" "+_objCursos.getProfesor().getApellido_paterno());
        holder.tvRut_profesor_jefe.setText(_objCursos.getProfesor().getRut());
        holder.tvCorreo_profesor_jefe.setText(_objCursos.getProfesor().getCorreo());

    }

    @Override
    public int getItemCount() {
        int i = 0;
        if(cursos.size() > 0){
            i = cursos.size();
        }

        return i;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvCurso, tvNombre_profesor_jefe, tvRut_profesor_jefe, tvCorreo_profesor_jefe;

        public MyViewHolder(View view){
            super(view);

            tvCurso = view.findViewById(R.id.tvCurso);
            tvNombre_profesor_jefe = view.findViewById(R.id.tvNombre_profesor_jefe);
            tvRut_profesor_jefe = view.findViewById(R.id.tvRut_profesor_jefe);
            tvCorreo_profesor_jefe = view.findViewById(R.id.tvCorreo_profesor_jefe);



        }

    }
}
