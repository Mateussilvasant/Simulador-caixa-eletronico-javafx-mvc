package br.com.bancoms.model.contas;

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

    public double depositar(double valor) {
        depositarConta(valor);
        return 0.0;
    }

    public void depositarConta(double valor) {
        saldo = saldo + valor;
    }

    public double depositar(double valor, Conta contaBeneficiada) throws Exception {
        contaBeneficiada.depositarConta(valor);
        return 0.0;
    }

    protected abstract double getTaxa(double valor, double taxa);

    public double sacar(double valor) throws Exception {
        sacarConta(valor);
        return 0.0;
    }

    public void sacarConta(double valor) throws Exception {
        if (valor <= saldo) {
            saldo = saldo - valor;
        } else {
            throw new Exception("Não foi possível realizar o saque, valor maior que o saldo...");
        }
    }

    public double transferir(double valor, Conta contaBeneficiada) throws Exception {

        try {
            sacarConta(valor);
            contaBeneficiada.setSaldo(contaBeneficiada.getSaldo() + valor);
        } catch (Exception e) {
            throw new Exception("Não foi possível fazer a transferência, valor maior que o saldo...");
        }

        return 0.0;
    }

    protected abstract double aplicarTaxas(double valor, double taxa);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
