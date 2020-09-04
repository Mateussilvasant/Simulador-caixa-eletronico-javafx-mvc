package br.com.bancoms.model.contas;

public class ContaPoupanca extends Conta {

    private final double rendimentoPoupanca = 0.2588;

    public ContaPoupanca() {
        super(0, "", 0, 0, 0);
    }

    @Override
    protected double aplicarTaxas(double valor, double rendimento) {
        return rendimento + valor;
    }

    @Override
    public double depositar(double valor) {
        double rendimento = getTaxa(valor, rendimentoPoupanca);
        super.depositar(aplicarTaxas(valor, rendimento));
        return rendimento;
    }

    @Override
    protected double getTaxa(double valor, double rendimento) {
        return ((valor * rendimento) / 100);
    }
}
