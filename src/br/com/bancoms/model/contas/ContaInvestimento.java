package br.com.bancoms.model.contas;

public class ContaInvestimento extends Conta {

    private final double rendimentoPoupanca = 0.3588;

    public ContaInvestimento() {
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

    @Override
    public boolean sacar(double valor) throws Exception {
        throw new Exception("Operação não autorizada...");
    }

    @Override
    public void transferir(double valor, Conta contaBeneficiada) throws Exception {

        if (contaBeneficiada instanceof ContaPoupanca || contaBeneficiada instanceof ContaInvestimento) {
            super.transferir(valor, contaBeneficiada);
        } else {
            throw new Exception("Não é possível transferir, apenas para contas do tipo investimento ou poupança.");
        }

    }
}
