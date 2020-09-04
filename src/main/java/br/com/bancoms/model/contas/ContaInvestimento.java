package br.com.bancoms.model.contas;

public class ContaInvestimento extends Conta {

    private final double rendimentoInvestimento = 0.3588;

    public ContaInvestimento() {
        super(0, "", 0, 0, 0);
    }

    @Override
    protected double aplicarTaxas(double valor, double rendimento) {
        return rendimento + valor;
    }

    @Override
    public double depositar(double valor) {
        double rendimento = getTaxa(valor, rendimentoInvestimento);
        super.depositar(aplicarTaxas(valor, rendimento));
        return rendimento;
    }

    @Override
    protected double getTaxa(double valor, double rendimento) {
        return ((valor * rendimento) / 100);
    }

    @Override
    public double sacar(double valor) throws Exception {
        throw new Exception("Operação não autorizada...");
    }

    @Override
    public double transferir(double valor, Conta contaBeneficiada) throws Exception {

        if (contaBeneficiada instanceof ContaPoupanca || contaBeneficiada instanceof ContaInvestimento) {
            return super.transferir(valor, contaBeneficiada);
        } else {
            throw new Exception("Não é possível transferir, apenas para contas do tipo investimento ou poupança.");
        }

    }
}
