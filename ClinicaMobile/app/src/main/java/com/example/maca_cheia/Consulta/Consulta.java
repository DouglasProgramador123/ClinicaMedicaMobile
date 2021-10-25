package com.example.maca_cheia.Consulta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.maca_cheia.Classes.ConsultaClasse;
import com.example.maca_cheia.Login;
import com.example.maca_cheia.MainActivity;
import com.example.maca_cheia.Main_Menu;
import com.example.maca_cheia.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Consulta extends AppCompatActivity {

    private String[] datasConsultOrigem = new String[]{"12/09/2021", "20/08/2021"};
    private final String[] datasConsultMarcada = new String[]{"12/09/2021", "20/08/2021"};
    private String[]  consultaTipo = new String[]{"Pediatria", "Homeopatia"};

    private  int itemClicado=-1;
    private  int excluirId=-1;

    private Spinner spConsultaMarcada;
    private Spinner spConsultaTipo;

    private String selectConsultaMarcada;
    private String selectConsultaTipo;

    private ListView listViewContatos;
    private ConsultaAdapter consultaAdapter;
    private List<ConsultaClasse> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        spConsultaMarcada= (Spinner) findViewById(R.id.spDataMarcada);
        spConsultaTipo= (Spinner) findViewById(R.id.spConsultaTipo);

        listViewContatos= findViewById(R.id.listViewContatos);

        ArrayAdapter<String> adapterDataConsulta=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,datasConsultOrigem);
        spConsultaMarcada.setAdapter(adapterDataConsulta);

        ArrayAdapter <String> adapterHorasConsulta=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,consultaTipo);
        spConsultaTipo.setAdapter(adapterHorasConsulta);


        lista = new ArrayList<ConsultaClasse>();
        consultaAdapter = new ConsultaAdapter(Consulta.this,lista);
        listViewContatos.setAdapter(consultaAdapter);



        dataMarcada();
        tipoConsulta();

        listarConsultas();
        selectView();


    }


    public void marcarConsulta(View view)
    {
        String HOST ="http://192.168.0.10/ClinicaMedicaWebService/Consulta/WriteConsulta.php";

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String data =dateFormat.format(date);


        Ion.with(Consulta.this)
                .load(HOST)
                .setBodyParameter("consultaDataMarcada",selectConsultaMarcada)
                .setBodyParameter("consultaTipo",selectConsultaTipo)
                .setBodyParameter("consultaData",data)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error

                        if(result.get("CREATE").getAsString().equals("OK"))
                        {

                            int idRetornado= Integer.parseInt(result.get("ID").getAsString());

                            ConsultaClasse con=new ConsultaClasse();


                            con.setId(idRetornado);
                            con.setConsultaDataMarcada(selectConsultaMarcada);
                            con.setConsultaData(data);
                            con.setConsultaTipo(selectConsultaTipo);


                            lista.add(con);



                            consultaAdapter.notifyDataSetChanged();

                            Toast.makeText(Consulta.this,"Consulta marcada com sucesso",Toast.LENGTH_LONG).show();

                        }else
                        {
                            Toast.makeText(Consulta.this,"Falha a marca uma consulta",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    public void cancelarConsulta(View view)
    {

        if(itemClicado ==-1 && excluirId==-1)
        {
            Toast.makeText(Consulta.this,"Selecione uma consulta na lista",Toast.LENGTH_SHORT).show();
        }else {
            String HOST = "http://192.168.0.10/ClinicaMedicaWebService/Consulta/DeleteConsulta.php";
            //Delete
            Ion.with(Consulta.this)
                    .load(HOST)
                    .setBodyParameter("id", String.valueOf(excluirId))
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            // do stuff with the result or error

                            if (result.get("DELETE").getAsString().equals("OK")) {

                                lista.remove(itemClicado);

                                consultaAdapter.notifyDataSetChanged();

                                itemClicado=-1;
                                excluirId=-1;

                                Toast.makeText(Consulta.this, "Excluido com sucesso", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Consulta.this, "Ocorreu um erro ao excluir", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    public void sairConsulta(View view)
    {
        startActivity(new Intent(Consulta.this, Main_Menu.class));
    }

    public void dataMarcada()
    {
        spConsultaMarcada.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

              selectConsultaMarcada=datasConsultMarcada[position];



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void tipoConsulta()
    {
        spConsultaTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectConsultaTipo= consultaTipo[position];



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void selectView() {
    listViewContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            ConsultaClasse c = (ConsultaClasse) adapterView.getAdapter().getItem(position);

            c.getId();
            c.getConsultaDataMarcada();
            c.getConsultaData();
            c.getConsultaTipo();

            excluirId = c.getId();

            itemClicado = position;




        }
    });
}


    public void listarConsultas()
    {
        String HOST ="http://192.168.0.10/ClinicaMedicaWebService/Consulta/ReadConsulta.php";

        Ion.with(getBaseContext())
                .load(HOST)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        for (int i=0;i < result.size();i++)
                        {
                            JsonObject obj=result.get(i).getAsJsonObject();
                            ConsultaClasse c=new ConsultaClasse();

                            c.setId(obj.get("idConsulta").getAsInt());
                            c.setConsultaData(obj.get("consultaData").getAsString());
                            c.setConsultaDataMarcada(obj.get("consultaDataMarcada").getAsString());
                            c.setConsultaTipo(obj.get("consultaTipo").getAsString());

                            lista.add(c);


                        }

                        consultaAdapter.notifyDataSetChanged();

                    }
                });
    }


}