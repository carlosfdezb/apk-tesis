package com.ceispieci.ceisp.Data.Api;

import com.ceispieci.ceisp.Data.Model.Alumno;
import com.ceispieci.ceisp.Data.Model.Asignatura;
import com.ceispieci.ceisp.Data.Model.Calendario;
import com.ceispieci.ceisp.Data.Model.Curso;
import com.ceispieci.ceisp.Data.Model.LoginBody;
import com.ceispieci.ceisp.Data.Model.Nota;
import com.ceispieci.ceisp.Data.Model.Noticia;
import com.ceispieci.ceisp.Data.Model.Promedio;
import com.ceispieci.ceisp.Data.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiRoutes {

    @POST("login")
    Call<User> login(@Body LoginBody loginBody);

    @GET("news")
    Call<List<Noticia>> noticias();

    @GET("asignaturas/{id}")
    Call<List<Asignatura>> getAsignaturas(@Path("id") int id);

    @GET("asignaturas/{user_id}/{asignatura_id}")
    Call<Asignatura> getAsignatura(@Path("user_id") int user, @Path("asignatura_id") int asignatura);

    @GET("notas/{user_id}/{asignatura_id}")
    Call<List<Nota>> getNotas(@Path("user_id") int user, @Path("asignatura_id") int asignatura);

    @GET("promedio/{user_id}/{asignatura_id}")
    Call<Promedio> getPromedio(@Path("user_id") int user, @Path("asignatura_id") int asignatura);

    @GET("calendario/{id}")
    Call<List<Calendario>> getCalendario(@Path("id") int id);

    @GET("curso/{id}")
    Call<List<Curso>> getCurso(@Path("id") int id);

    @GET("alumno/{id}")
    Call<List<Alumno>> getAlumno(@Path("id") int id);


}
