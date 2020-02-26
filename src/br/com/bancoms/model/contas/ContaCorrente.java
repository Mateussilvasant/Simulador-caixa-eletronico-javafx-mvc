package br.com.bancoms.model.contas;

public class ContaCorrente extends Conta {

    private final double taxaCorrente = 0.3588;

    public ContaCorrente() {
        super(0, "", 0, 0, 0);
    }

    @Override
    protected double aplicarTaxas(double valor, double taxa) {
        return ((valor * taxa) / 100) - valor;
    }

    @Override
    public boolean sacar(double valor) throws Exception {
        return super.sacar(aplicarTaxas(valor, taxaCorrente));
    }

    @Override
    public void transferir(double valor, Conta contaBeneficiada) throws Exception {
        super.transferir(aplicarTaxas(valor, taxaCorrente * 0.5), contaBeneficiada);
    }

}
