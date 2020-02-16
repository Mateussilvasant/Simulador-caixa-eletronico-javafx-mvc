package br.com.bancoms.model;

public class Movimento {

    public static int SAQUE = 1;
    public static int DEPOSITO = 2;
    public static int TRANSFERENCIA = 3;
    public static int EMPRESTIMO = 4;

    private int idMovimento;
    private double valor;
    private int tipo;
    private String descricao;
    private String data;
    private int numeroContaOrigem;
    private int numeroContaDestino;

    public Movimento(double valor, String descricao, int tipo, String data, int numeroContaOrigem, int numeroContaDestino) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.data = data;
        this.numeroContaOrigem = numeroContaOrigem;
        this.numeroContaDestino = numeroContaDestino;
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

    public int getIdMovimento() {
        return idMovimento;
    }

    public void setIdMovimento(int idMovimento) {
        this.idMovimento = idMovimento;
    }

    @Override
    public String toString() {
        return "Movimento{" +
                "valor=" + valor +
                ", tipo=" + tipo +
                ", descricao='" + descricao + '\'' +
                ", data='" + data + '\'' +
                ", numeroContaOrigem=" + numeroContaOrigem +
                ", numeroContaDestino=" + numeroContaDestino +
                '}';
    }
}
