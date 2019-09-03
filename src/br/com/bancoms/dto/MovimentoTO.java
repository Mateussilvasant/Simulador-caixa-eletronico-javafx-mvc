package br.com.bancoms.dto;

public class MovimentoTO {
    public static int SAQUE = 1;
    public static int DEPOSITO = 2;
    public static int TRANSFERENCIA = 3;
    public static int EMPRESTIMO = 4;

    private int idConta;
    private double valor;
    private int tipo;
    private String descricao;
    private String data;
    private int numeroContaOrigem;
    private int numeroContaDestino;

    public MovimentoTO(int idConta, double valor, int tipo, String descricao, int numeroContaOrigem, int numeroContaDestino) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.numeroContaOrigem = numeroContaOrigem;
        this.numeroContaDestino = numeroContaDestino;
        this.idConta = idConta;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }
}
