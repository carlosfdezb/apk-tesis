package com.ceispieci.ceisp.Views.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ceispieci.ceisp.Data.Api.Api;
import com.ceispieci.ceisp.Data.Model.LoginBody;
import com.ceispieci.ceisp.Data.Model.User;
import com.ceispieci.ceisp.Data.Preferences.SessionPreferences;
import com.ceispieci.ceisp.R;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail,etPassword;
    private Button btnLogin;
    private TextView tvRegistro;

    private ProgressDialog pdDialogo;
    private SessionPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs = new SessionPreferences(getApplicationContext());

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnIniciarSesion);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdDialogo = ProgressDialog.show(LoginActivity.this,"Iniciando sesión","Comprobando credenciales...",true,false);
                if(validar()){
                    login(etEmail.getText().toString(),etPassword.getText().toString());
                }else{
                    pdDialogo.dismiss();
                }

            }
        });

    }
    private boolean validar(){
        boolean valido = true;

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if(email.isEmpty() || !validateEmail(email)){
            valido =  false;
            etEmail.setError("El email es requerido");
        }else{
            etEmail.setError(null);
        }

        if(password.isEmpty()){
            valido =  false;
            etPassword.setError("La Contraseña es requerida");
        }else{
            etPassword.setError(null);
        }

        return valido;
    }

    public boolean validateEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private void login(String email, String password){

        LoginBody _objLogin = new LoginBody(email,password);

        Call<User> callLogin = Api.getApi().login(_objLogin);
        callLogin.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                pdDialogo.dismiss();
                if(response.isSuccessful()) {
                    toast("Bienvenido");
                    prefs.guardarUsuario(response.body());
                    Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else{
                    toast("Usuario/Contraseña incorrectos");
                    etPassword.setText("");
                    etPassword.setError("Contraseña incorrecto");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                pdDialogo.dismiss();
                toast("Error al comunicarse con el servidor");
            }
        });

    }

    private void toast(String mensaje){
        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
    }
}
