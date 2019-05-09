package br.com.bancoms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.bancoms.model.Cliente;
import br.com.bancoms.model.Conta;
import br.com.bancoms.model.ContaCorrente;
import br.com.bancoms.model.ContaPoupanca;

public class ClienteDAO
{
    public ClienteDAO()
    {
    }

    public Cliente realizarLogin(int numeroConta, String senhaConta)
    {
	Cliente cliente = null;
	Conta conta = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try
	{
	    String sql = "SELECT CONTA.ID_CONTA,CONTA.TIPO_CONTA,CONTA.DESCRICAO_CONTA, "
		    + "CONTA.SALDO_TOTAL, CLIENTE.NOME, CLIENTE.SOBRENOME "
		    + "from CONTA JOIN CLIENTE ON CONTA.ID_CLIENTE = CLIENTE.ID_CLIENTE "
		    + "WHERE NUMERO_CONTA = (?) and SENHA = (?)";
	    ps = DatabaseConnect.getInstance().getPreparedSQL(sql);
	    ps.setInt(1, numeroConta);
	    ps.setString(2, senhaConta);
	    rs = ps.executeQuery();

	    if (rs.next())
	    {
		int idConta = rs.getInt("CONTA.ID_CONTA");
		int tipoConta = rs.getInt("CONTA.TIPO_CONTA");
		String descricao = rs.getString("CONTA.DESCRICAO_CONTA");
		double saldo = rs.getDouble("CONTA.SALDO_TOTAL");
		String nome = rs.getString("CLIENTE.NOME");
		String sobrenome = rs.getString("CLIENTE.SOBRENOME");

		if (tipoConta == Conta.POUPANCA)
		{
		    conta = new ContaPoupanca(idConta, descricao, tipoConta, numeroConta, saldo);
		}
		if (tipoConta == Conta.CORRENTE)
		{
		    conta = new ContaCorrente(idConta, descricao, tipoConta, numeroConta, saldo);
		}

		cliente = new Cliente(nome, sobrenome, conta);
	    }

	} catch (Exception e)
	{

	    System.out.println(e.toString());

	    try
	    {
		DatabaseConnect.getInstance().rollBack();
	    } catch (Exception rollbackException)
	    {
		System.out.println(rollbackException.toString());
	    }

	} finally
	{
	    try
	    {
		DatabaseConnect.getInstance().closeConnection(ps, rs);
	    } catch (Exception closeException)
	    {
		System.out.println(closeException.toString());
	    }
	}
	return cliente;
    }

}
