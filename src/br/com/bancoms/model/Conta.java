package br.com.bancoms.model;

public abstract class Conta
{

    public static final int CORRENTE = 1;
    public static final int POUPANCA = 2;
    public static final int INVESTIMENTO = 0;

    private int id;
    private String descricao;
    private int tipo;
    private int numero;
    protected double saldo;

    public Conta(int id, String descricao, int tipo, int numero, double saldo)
    {
	this.id = id;
	this.descricao = descricao;
	this.tipo = tipo;
	this.numero = numero;
	this.saldo = saldo;
    }

    public Conta()
    {
    }

    public abstract void gerarTaxas();

    public boolean depositar(double valor)
    {

	if (valor > 0.0)
	{
	    saldo = saldo + valor;
	    return true;
	}

	return false;

    }

    public boolean sacar(double valor)
    {

	if (valor > 0.0)
	{
	    saldo = saldo - valor;
	    return true;
	}
	return false;
    }

    public boolean transferir(double valor, Conta contaDestino)
    {
	boolean retirou = sacar(valor);

	if (retirou)
	{
	    contaDestino.setSaldo(contaDestino.getSaldo() + valor);
	    return true;
	}
	return false;
    }

    public String getDescricao()
    {
	return descricao;
    }

    public void setDescricao(String descricao)
    {
	this.descricao = descricao;
    }

    public int getTipo()
    {
	return tipo;
    }

    public void setTipo(int tipo)
    {
	this.tipo = tipo;
    }

    public int getNumero()
    {
	return numero;
    }

    public void setNumero(int numero)
    {
	this.numero = numero;
    }

    public double getSaldo()
    {
	return saldo;
    }

    public void setSaldo(double saldo)
    {
	this.saldo = saldo;
    }

    public int getId()
    {
	return id;
    }

    public void setId(int id)
    {
	this.id = id;
    }

}
