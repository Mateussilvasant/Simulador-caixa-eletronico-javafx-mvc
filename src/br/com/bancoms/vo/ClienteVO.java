package br.com.bancoms.vo;

public class ClienteVO
{

    private int numeroConta;
    private String senha;

    public ClienteVO(int numeroConta, String senha)
    {
	this.numeroConta = numeroConta;
	this.senha = senha;
    }

    public int getNumeroConta()
    {
	return numeroConta;
    }

    public void setNumeroConta(int numeroConta)
    {
	this.numeroConta = numeroConta;
    }

    public String getSenha()
    {
	return senha;
    }

    public void setSenha(String senha)
    {
	this.senha = senha;
    }
}
