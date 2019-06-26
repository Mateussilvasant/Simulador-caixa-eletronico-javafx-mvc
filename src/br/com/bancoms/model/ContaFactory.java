package br.com.bancoms.model;

public enum ContaFactory
{

    POUPANCA
    {

	@Override
	public Conta getConta()
	{
	    return new ContaPoupanca();
	}

    },

    CORRENTE
    {

	@Override
	public Conta getConta()
	{
	    return new ContaCorrente();
	}

    },

    INVESTIMENTO
    {

	@Override
	public Conta getConta()
	{
	    return new ContaInvestimento();
	}

    };

    public abstract Conta getConta();
}
