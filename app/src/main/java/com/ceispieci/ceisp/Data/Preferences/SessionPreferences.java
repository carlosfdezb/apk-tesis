package com.ceispieci.ceisp.Data.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.ceispieci.ceisp.Data.Model.User;

public class SessionPreferences {

    private static final String PREFS_NAME = "SESSION";

    //Constantes sesión
    private static final String PREF_NOMBRE = "PREF_NAME";
    private static final String PREF_ID = "PREF_ID";
    private static final String PREF_EMAIL = "PREF_EMAIL";
    private static final String PREF_RUT = "PREF_RUT";
    private static final String PREF_SESSION = "PREF_SESSION";

    private final SharedPreferences prefs;

    public SessionPreferences(Context ctx) {
        prefs = ctx.getApplicationContext().getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
    }

    public boolean estaLogueado(){
        return prefs.getBoolean(PREF_SESSION,false);
    }

    public void guardarUsuario(User user){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREF_NOMBRE,user.getName());
        editor.putInt(PREF_ID,user.getId());
        editor.putString(PREF_EMAIL,user.getEmail());
        editor.putString(PREF_RUT,user.getRut());
        editor.putBoolean(PREF_SESSION,true);

        editor.apply();
    }

    public void cerrarSesion(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREF_NOMBRE,null);
        editor.putInt(PREF_ID,0);
        editor.putString(PREF_EMAIL,null);
        editor.putString(PREF_RUT,null);
        editor.putBoolean(PREF_SESSION,false);

        editor.apply();
    }

    public User getUsuario(){
        User _objUser = new User();
        _objUser.setName(prefs.getString(PREF_NOMBRE,""));
        _objUser.setEmail(prefs.getString(PREF_EMAIL,""));
        _objUser.setRut(prefs.getString(PREF_RUT,""));
        _objUser.setId(prefs.getInt(PREF_ID,0));

        return _objUser;
    }

}
