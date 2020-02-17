package br.com.bancoms.dto;

public class MovimentoBuscaDTO {

    private int numeroConta;
    private int tipoMovimento;
    private String dataInicio;
    private String dataFim;

    public MovimentoBuscaDTO(int numeroConta, int tipoMovimento, String dataInicio, String dataFim) {
        this.numeroConta = numeroConta;
        this.tipoMovimento = tipoMovimento;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public MovimentoBuscaDTO(int numeroConta, String dataInicio, String dataFim) {
        this.numeroConta = numeroConta;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public int getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(int tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
}
