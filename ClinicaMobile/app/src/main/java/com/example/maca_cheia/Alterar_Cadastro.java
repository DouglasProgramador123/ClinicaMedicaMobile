package com.example.maca_cheia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maca_cheia.Classes.ConsultaClasse;
import com.example.maca_cheia.Classes.PacienteClasse;
import com.example.maca_cheia.Consulta.Consulta;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Alterar_Cadastro extends AppCompatActivity {

    private EditText alterarLog;
    private EditText alterarSenha;
    private EditText alterarNome;
    private EditText alterarCpf;
    private EditText alterarTel;
    private EditText alterarEnd;
    private EditText alterarCep;
    private EditText alterarEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar__cadastro);

        alterarLog=findViewById(R.id.editAlterarLoginCadastro);
        alterarSenha=findViewById(R.id.editAlterarSenhaCadastro);
        alterarNome=findViewById(R.id.editAlterarNome);
        alterarCpf=findViewById(R.id.editAlterarCPF);
        alterarTel=findViewById(R.id.editAlterarTel);
        alterarEnd=findViewById(R.id.editAlterarEndereco);
        alterarCep=findViewById(R.id.editAlterarCep);
        alterarEmail=findViewById(R.id.editAlterarEmail);

        exibirCadastro();

    }

    public void alterarCadastro(View view)
    {
        String HOST ="http://192.168.0.10/ClinicaMedicaWebService/Paciente/AlterPaciente.php";

        final String loginCad = alterarLog.getText().toString();
        final String senhaCad =  alterarSenha.getText().toString();
        final String nomeCad =  alterarNome.getText().toString();
        final String cpfCad = alterarCpf.getText().toString();
        final String telCad = alterarTel.getText().toString();
        final String endCad = alterarEnd.getText().toString();
        final String cepCad = alterarCep.getText().toString();
        final String emailCad = alterarEmail.getText().toString();


        //UPDATE

        Ion.with(Alterar_Cadastro.this)
                .load(HOST)
                .setBodyParameter("login",loginCad)
                .setBodyParameter("senha",senhaCad)
                .setBodyParameter("nome",nomeCad)
                .setBodyParameter("cpf",cpfCad)
                .setBodyParameter("tel",telCad)
                .setBodyParameter("end",endCad)
                .setBodyParameter("cep",cepCad)
                .setBodyParameter("email",emailCad)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error

                        if(result.get("UPDATE").getAsString().equals("OK"))
                        {

                            PacienteClasse ca=new PacienteClasse();


                            ca.setLogin(loginCad);
                            ca.setSenha(senhaCad);
                            ca.setNome(nomeCad);
                            ca.setCpf(cpfCad);
                            ca.setTel(telCad);
                            ca.setEnd(endCad);
                            ca.setCep(cepCad);
                            ca.setEmail(emailCad);


                            Toast.makeText(Alterar_Cadastro.this,"Cadastrado atualizado com sucesso",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Alterar_Cadastro.this,Main_Menu.class));
                        }else
                        {
                            Toast.makeText(Alterar_Cadastro.this,"Ocorreu um erro ao atualizar cadastrar",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }





    public void exibirCadastro()
    {

        String HOST ="http://192.168.0.10/ClinicaMedicaWebService/Paciente/ReadPaciente.php";




        Ion.with(getBaseContext())
                .load(HOST)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        for (int i=0;i < result.size();i++)
                        {
                            JsonObject obj=result.get(i).getAsJsonObject();
                            PacienteClasse ca=new PacienteClasse();

                            ca.setId(obj.get("idPaciente").getAsInt());
                            ca.setLogin(obj.get("login").getAsString());
                            ca.setSenha(obj.get("senha").getAsString());
                            ca.setNome(obj.get("nome").getAsString());
                            ca.setCpf(obj.get("cpf").getAsString());
                            ca.setTel(obj.get("tel").getAsString());
                            ca.setEnd(obj.get("end").getAsString());
                            ca.setCep(obj.get("cep").getAsString());
                            ca.setEmail(obj.get("email").getAsString());


                            alterarLog.setText(ca.getLogin());
                            alterarSenha.setText(ca.getSenha());
                            alterarNome.setText(ca.getNome());
                            alterarCpf.setText(ca.getCpf());
                            alterarCep.setText(ca.getCep());
                            alterarTel.setText(ca.getTel());
                            alterarEnd.setText(ca.getEnd());
                            alterarEmail.setText(ca.getEmail());



                        }



                    }
                });



    }

    public void sairAlterarCadastro(View view)
    {
        startActivity(new Intent(Alterar_Cadastro.this,Main_Menu.class));
    }

}