package br.com.bancoms.dto;

import br.com.bancoms.model.Conta;

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

    public MovimentoTO(double valor, int tipo, String descricao, Conta conta) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.idConta = conta.getId();
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

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }
}
