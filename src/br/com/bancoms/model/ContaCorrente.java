package br.com.bancoms.model;

public class ContaCorrente extends Conta {
    private double despesas;

    public ContaCorrente(int id, String descricao, int tipo, int numero, double saldo) {
        super(id, descricao, tipo, numero, saldo);
        this.despesas = 15.30;
    }

    public ContaCorrente() {
        super(0, "", 0, 0, 0);
    }

    @Override
    public void gerarTaxas() {
        saldo = saldo - despesas;
    }

    public double getDespesasMensal() {
        return despesas;
    }

}
