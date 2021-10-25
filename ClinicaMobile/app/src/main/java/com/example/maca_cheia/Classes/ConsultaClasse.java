package com.example.maca_cheia.Classes;

public class ConsultaClasse {

    private int id;
    private String consultaData;
    private String consultaDataMarcada;
    private String consultaTipo;
    private PacienteClasse paciente;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConsultaData() {
        return consultaData;
    }

    public void setConsultaData(String consultaData) {
        this.consultaData = consultaData;
    }

    public String getConsultaDataMarcada() {
        return consultaDataMarcada;
    }

    public void setConsultaDataMarcada(String consultaDataMarcada) {
        this.consultaDataMarcada = consultaDataMarcada;
    }

    public String getConsultaTipo() {
        return consultaTipo;
    }

    public void setConsultaTipo(String consultaTipo) {
        this.consultaTipo = consultaTipo;
    }

    public PacienteClasse getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteClasse paciente) {
        this.paciente = paciente;
    }
}
