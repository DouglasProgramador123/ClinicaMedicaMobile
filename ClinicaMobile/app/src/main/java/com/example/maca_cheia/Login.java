package com.example.maca_cheia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maca_cheia.Consulta.Consulta;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class Login extends AppCompatActivity {

    private EditText login;
    private EditText senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.editLogin);
        senha = findViewById(R.id.editSenha);
    }

    public void logar(View view) {
        String HOST = "http://192.168.0.10/ClinicaMedicaWebService/Paciente/Login.php";

         String loginCad = login.getText().toString();
         String senhaCad = senha.getText().toString();


        Ion.with(getBaseContext())
                .load(HOST)
                .setBodyParameter("login", loginCad)
                .setBodyParameter("senha", senhaCad)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        String RETORNO = result.get("LOGIN").getAsString();

                        if (RETORNO.equals("SUCESSO")) {
                        Toast.makeText(Login.this, "Login feito com sucesso!!", Toast.LENGTH_LONG).show();


                            startActivity(new Intent(Login.this, Main_Menu.class));
                        } else if (RETORNO.equals("ERRO")) {
                            Toast.makeText(Login.this, "Login Inválido", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Login.this, "Ocorreu um erro ao Logar-se", Toast.LENGTH_LONG).show();
                        }

                    }
                });


    }

    public void sairLogin(View view) {

        startActivity(new Intent(Login.this, MainActivity.class));

    }

}


