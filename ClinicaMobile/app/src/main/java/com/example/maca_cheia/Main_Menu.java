package com.example.maca_cheia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.maca_cheia.Consulta.Consulta;

public class Main_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);
    }

    public void telaConsulta(View view)
    {
        startActivity(new Intent(Main_Menu.this, Consulta.class));
    }

    public void telaDadosCadastrais(View view)
    {
         startActivity(new Intent(Main_Menu.this,Alterar_Cadastro.class));
    }

    public void sairTelaCadastro(View view)
    {
        startActivity(new Intent(Main_Menu.this, MainActivity.class));
    }
}