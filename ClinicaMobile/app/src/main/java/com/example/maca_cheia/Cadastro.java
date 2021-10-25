package com.example.maca_cheia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maca_cheia.Classes.PacienteClasse;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class Cadastro extends AppCompatActivity {

    private EditText log;
    private EditText senha;
    private EditText nome;
    private EditText cpf;
    private EditText tel;
    private EditText end;
    private EditText cep;
    private EditText email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        log=findViewById(R.id.editAlterarLoginCadastro);
        senha=findViewById(R.id.editAlterarSenhaCadastro);
        nome=findViewById(R.id.editAlterarNome);
        cpf=findViewById(R.id.editAlterarCPF);
        tel=findViewById(R.id.editAlterarTel);
        end=findViewById(R.id.editAlterarEndereco);
        cep=findViewById(R.id.editAlterarCep);
        email=findViewById(R.id.editAlterarEmail);

    }

    public void cadastrar(View view)
    {

        String HOST ="http://192.168.0.10/ClinicaMedicaWebService/Paciente/WritePaciente.php";

        final String loginCad = log.getText().toString();
        final String senhaCad = senha.getText().toString();
        final String nomeCad =  nome.getText().toString();
        final String cpfCad = cpf.getText().toString();
        final String telCad = tel.getText().toString();
        final String endCad = end.getText().toString();
        final String cepCad = cep.getText().toString();
        final String emailCad = email.getText().toString();

        if(!loginCad.equals(null) && !senhaCad.equals(null) && !nomeCad.equals(null) && !cpfCad.equals(null) && !telCad.equals(null) && endCad.equals(null) && cepCad.equals(null) && emailCad.equals(null)  ) {

            //CREATE

            Ion.with(Cadastro.this)
                    .load(HOST)
                    .setBodyParameter("login", loginCad)
                    .setBodyParameter("senha", senhaCad)
                    .setBodyParameter("nome", nomeCad)
                    .setBodyParameter("cpf", cpfCad)
                    .setBodyParameter("tel", telCad)
                    .setBodyParameter("end", endCad)
                    .setBodyParameter("cep", cepCad)
                    .setBodyParameter("email", emailCad)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            // do stuff with the result or error

                            if (result.get("CREATE").getAsString().equals("OK")) {

                                PacienteClasse ca = new PacienteClasse();


                                ca.setLogin(loginCad);
                                ca.setSenha(senhaCad);
                                ca.setNome(nomeCad);
                                ca.setCpf(cpfCad);
                                ca.setTel(telCad);
                                ca.setEnd(endCad);
                                ca.setCep(cepCad);
                                ca.setEmail(emailCad);


                                Toast.makeText(Cadastro.this, "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Cadastro.this, MainActivity.class));
                            } else {
                                Toast.makeText(Cadastro.this, "Ocorreu um erro ao se cadastrar", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
else {
            Toast.makeText(Cadastro.this, "Preencha todos os campos com seus dados", Toast.LENGTH_LONG).show();
        }
    }

    public void sairCadastro(View view)
    {
        startActivity(new Intent(Cadastro.this,MainActivity.class));
    }

    public void limparCadastro(View view)
    {
        log.setText("");
        senha.setText("");
        nome.setText("");
        cpf.setText("");
        tel.setText("");
        end.setText("");
        cep.setText("");
        email.setText("");
    }
}