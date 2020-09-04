package br.com.bancoms.model.contas;

public class ContaCorrente extends Conta {

    private final double taxaCorrente = 0.3588;

    public ContaCorrente() {
        super(0, "", 0, 0, 0);
    }

    @Override
    protected double aplicarTaxas(double valor, double taxa) throws Exception {
        double diferencial = getTaxa(valor, taxa);
        super.sacarConta(diferencial);
        return diferencial;
    }

    @Override
    protected double getTaxa(double valor, double taxa) {
        return ((valor * taxa) / 100);
    }

    @Override
    public double depositar(double valor, Conta contaBeneficiada) throws Exception {

        double taxaGerada = aplicarTaxas(valor, taxaCorrente * 0.6);
        contaBeneficiada.depositarConta(valor);

        return -taxaGerada;
    }

    @Override
    public double sacar(double valor) throws Exception {
        double taxaGerada = aplicarTaxas(valor, taxaCorrente);
        super.sacar(valor);

        return -taxaGerada;
    }

    @Override
    public double transferir(double valor, Conta contaBeneficiada) throws Exception {

        double taxaGerada = aplicarTaxas(valor, taxaCorrente);
        super.transferir(valor, contaBeneficiada);

        return -taxaGerada;
    }

}
