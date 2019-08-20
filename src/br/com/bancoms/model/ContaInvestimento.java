package br.com.bancoms.model;

public class ContaInvestimento extends Conta {

    private double rendimento;

    public ContaInvestimento(int id, String descricao, int tipo, int numero, double saldo) {
        super(id, descricao, tipo, numero, saldo);
    }

    public ContaInvestimento() {
        super(0, "", 0, 0, 0);
    }

    public double getRendimento() {
        return rendimento;
    }

    @Override
    public void gerarTaxas() {
        rendimento = super.saldo + (super.saldo * 0.08);
        saldo = saldo + rendimento;
    }

}
