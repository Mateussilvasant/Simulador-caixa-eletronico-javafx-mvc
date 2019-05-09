package br.com.bancoms.model;

public class ContaPoupanca extends Conta
{

    double rendimentos;

    public ContaPoupanca(int id, String descricao, int tipo, int numero, double saldo)
    {
	super(id, descricao, tipo, numero, saldo);
    }

    public double getRendimentos()
    {
	return rendimentos;
    }

    @Override
    public void gerarTaxas()
    {
	rendimentos = super.saldo * 0.06;
	saldo = saldo + rendimentos;
    }

}
