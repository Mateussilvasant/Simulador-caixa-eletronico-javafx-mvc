package br.com.bancoms.model;

public abstract class Conta {

    public static final int CORRENTE = 1;
    public static final int POUPANCA = 2;
    public static final int INVESTIMENTO = 0;

    protected int id;
    protected String descricao;
    protected int tipo;
    protected int numero;
    protected double saldo;

    public Conta(int id, String descricao, int tipo, int numero, double saldo) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.numero = numero;
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        saldo = saldo + valor;
    }

    public boolean sacar(double valor) {
        if (valor < saldo) {
            saldo = saldo - valor;
            return true;
        }
        return false;
    }

    public boolean transferir(double valor, Conta contaBeneficiada) {
        boolean retirou = sacar(valor);

        if (retirou) {
            contaBeneficiada.setSaldo(contaBeneficiada.getSaldo() + valor);
            return true;
        }
        return false;
    }

    public abstract void gerarTaxas();

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
