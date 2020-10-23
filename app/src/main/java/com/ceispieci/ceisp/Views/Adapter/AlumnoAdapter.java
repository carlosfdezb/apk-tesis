package com.ceispieci.ceisp.Views.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ceispieci.ceisp.Data.Model.Alumno;
import com.ceispieci.ceisp.Data.Model.Curso;
import com.ceispieci.ceisp.R;

import java.util.List;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.MyViewHolder>{

    private List<Alumno> alumnos;

    public AlumnoAdapter(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @NonNull
    @Override
    public AlumnoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_alumno,parent,false);
        return new AlumnoAdapter.MyViewHolder(itemview);
    }


    @Override
    public void onBindViewHolder(@NonNull AlumnoAdapter.MyViewHolder holder, int position) {

        final Alumno _objAlumnos= alumnos.get(position);

        holder.tvNombre_alumno.setText(_objAlumnos.getPrimer_nombre()+" "+_objAlumnos.getSegundo_nombre());
        holder.tvApellidos_alumno.setText(_objAlumnos.getApellido_paterno()+" "+_objAlumnos.getApellido_materno());
        holder.tvRut_alumno.setText(_objAlumnos.getRut());
        holder.tvCorreo_alumno.setText(_objAlumnos.getCorreo());

    }

    @Override
    public int getItemCount() {
        int i = 0;
        if(alumnos.size() > 0){
            i = alumnos.size();
        }

        return i;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvNombre_alumno, tvApellidos_alumno, tvRut_alumno, tvCorreo_alumno;

        public MyViewHolder(View view){
            super(view);

            tvNombre_alumno = view.findViewById(R.id.tvNombre_alumno);
            tvApellidos_alumno = view.findViewById(R.id.tvApellidos_alumno);
            tvRut_alumno = view.findViewById(R.id.tvRut_alumno);
            tvCorreo_alumno = view.findViewById(R.id.tvCorreo_alumno);



        }

    }
}
