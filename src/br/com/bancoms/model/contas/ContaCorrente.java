package br.com.bancoms.model.contas;

public class ContaCorrente extends Conta {

    private final double taxaCorrente = 0.3588;

    public ContaCorrente() {
        super(0, "", 0, 0, 0);
    }

    @Override
    protected double aplicarTaxas(double valor, double taxa) {
        return taxa - valor;
    }

    @Override
    protected double getTaxa(double valor, double taxa) {
        return ((valor * taxa) / 100);
    }

    @Override
    public double depositar(double valor, Conta contaBeneficiada) throws Exception {

        double taxa = getTaxa(valor, taxaCorrente * 0.6);
        super.sacar(taxa);
        contaBeneficiada.depositarConta(valor);

        return -taxa;
    }

    @Override
    public double sacar(double valor) throws Exception {
        double taxa = getTaxa(valor, taxaCorrente);
        super.sacar(aplicarTaxas(valor, taxa));
        return -taxa;
    }

    @Override
    public double transferir(double valor, Conta contaBeneficiada) throws Exception {
        double taxa = getTaxa(valor, taxaCorrente * 0.5);
        super.transferir(aplicarTaxas(valor, taxa), contaBeneficiada);
        return -taxa;
    }

}
