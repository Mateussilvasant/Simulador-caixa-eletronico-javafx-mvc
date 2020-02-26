package br.com.bancoms.model.contas;

public class ContaPoupanca extends Conta {

    private final double rendimentoPoupanca = 0.2588;

    public ContaPoupanca() {
        super(0, "", 0, 0, 0);
    }

    @Override
    protected double aplicarTaxas(double valor, double rendimento) {
        return ((valor * rendimento) / 100) + valor;
    }

    @Override
    public void depositar(double valor) {
        super.depositar(aplicarTaxas(valor, rendimentoPoupanca));
    }
}
