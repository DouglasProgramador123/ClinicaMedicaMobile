package com.example.maca_cheia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.koushikdutta.ion.Ion;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void Login(View view)
    {
     startActivity(new Intent(MainActivity.this,Login.class));
    }


    public void Cadastro(View view)
    {
        startActivity(new Intent(MainActivity.this,Cadastro.class));
    }



}
