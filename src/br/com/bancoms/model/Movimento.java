package br.com.bancoms.model;

public class Movimento {

    public static int SAQUE = 1;
    public static int DEPOSITO = 2;
    public static int TRANSFERENCIA = 3;
    public static int EMPRESTIMO = 4;

    private double valor;
    private int tipo;
    private String descricao;
    private String data;

    public Movimento(double valor, int tipo, String descricao, String data) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.data = data;
    }

    public Movimento(double valor, String descricao, String data) {
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
    }

    public Movimento() {
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

}
