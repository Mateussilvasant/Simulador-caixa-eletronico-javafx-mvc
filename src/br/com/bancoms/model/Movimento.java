package br.com.bancoms.model;

public class Movimento {

    private int idConta;
    private double valorTransacao;
    private double valorDiferencial;
    private int tipo;
    private String descricao;
    private String data;
    private int numeroContaOrigem;
    private int numeroContaDestino;

    public Movimento(int idConta, double valor, int tipo, String descricao, int numeroContaOrigem, int numeroContaDestino,double valorDiferencial) {
        this.idConta = idConta;
        this.valorTransacao = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.numeroContaOrigem = numeroContaOrigem;
        this.numeroContaDestino = numeroContaDestino;
        this.valorDiferencial = valorDiferencial;
    }

    public Movimento(double valor, String descricao, int tipo, String data, int numeroContaOrigem, int numeroContaDestino,double valorDiferencial) {
        this.valorTransacao = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.numeroContaOrigem = numeroContaOrigem;
        this.numeroContaDestino = numeroContaDestino;
        this.data = data;
        this.valorDiferencial = valorDiferencial;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public double getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(double valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public double getValorDiferencial() {
        return valorDiferencial;
    }

    public void setValorDiferencial(double valorDiferencial) {
        this.valorDiferencial = valorDiferencial;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getNumeroContaOrigem() {
        return numeroContaOrigem;
    }

    public void setNumeroContaOrigem(int numeroContaOrigem) {
        this.numeroContaOrigem = numeroContaOrigem;
    }

    public int getNumeroContaDestino() {
        return numeroContaDestino;
    }

    public void setNumeroContaDestino(int numeroContaDestino) {
        this.numeroContaDestino = numeroContaDestino;
    }


    @Override
    public String toString() {
        return "Movimento{" +
                "idConta=" + idConta +
                ", valorTransacao=" + valorTransacao +
                ", valorDiferencial=" + valorDiferencial +
                ", tipo=" + tipo +
                ", descricao='" + descricao + '\'' +
                ", data='" + data + '\'' +
                ", numeroContaOrigem=" + numeroContaOrigem +
                ", numeroContaDestino=" + numeroContaDestino +
                '}';
    }

}
