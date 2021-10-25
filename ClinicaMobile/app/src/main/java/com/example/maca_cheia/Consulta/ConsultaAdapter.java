package com.example.maca_cheia.Consulta;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maca_cheia.Classes.ConsultaClasse;
import com.example.maca_cheia.R;

import java.util.List;

public class ConsultaAdapter extends BaseAdapter {

    private Context ctx;
    private List<ConsultaClasse> lista;

    public ConsultaAdapter(Context ctx2, List<ConsultaClasse> lista2)
    {
        ctx=ctx2;
        lista=lista2;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public ConsultaClasse getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v= null;
        if(convertView ==null)
        {
            LayoutInflater inflater=((Activity)ctx).getLayoutInflater();
            v = inflater.inflate(R.layout.activity_consulta_itens,null);
        }else
        {
            v = convertView;
        }

        ConsultaClasse c = getItem(position);


        TextView itemConsultaData=(TextView) v.findViewById(R.id.itemDataOrigem);
        TextView itemConsultaDataMarcada=(TextView) v.findViewById(R.id.itemDataConsulta);
        TextView itemConsultaTipo=(TextView) v.findViewById(R.id.itemConsultaTipo);


        itemConsultaData.setText("Data de Origem: "+c.getConsultaData());
        itemConsultaDataMarcada.setText("Data da Consulta: "+c.getConsultaDataMarcada());
        itemConsultaTipo.setText("Tipo da Consulta: "+c.getConsultaTipo());

        return v;
    }


}
