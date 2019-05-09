package br.com.bancoms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.bancoms.model.Conta;

public class ContaDAO
{

    public ContaDAO()
    {
    }

    public double consultarSaldo(Conta conta)
    {

	PreparedStatement ps = null;
	ResultSet rs = null;

	try
	{

	    ps = DatabaseConnect.getInstance().getPreparedSQL("SELECT SALDO_TOTAL FROM CONTA WHERE NUMERO_CONTA = (?)");
	    ps.setInt(1, conta.getNumero());
	    rs = ps.executeQuery();

	    if (rs.next())
	    {
		return rs.getDouble("SALDO_TOTAL");
	    }

	} catch (Exception e)
	{
	    e.printStackTrace();

	    try
	    {
		DatabaseConnect.getInstance().rollBack();
	    } catch (Exception e1)
	    {
		e1.printStackTrace();
	    }

	} finally
	{
	    try
	    {
		DatabaseConnect.getInstance().closeConnection(ps, rs);
	    } catch (Exception e)
	    {
		e.printStackTrace();
	    }
	}

	return 0;
    }

    public boolean atualizarSaldo(Conta conta)
    {
	PreparedStatement ps = null;

	try
	{

	    ps = DatabaseConnect.getInstance()
		    .getPreparedSQL("UPDATE CONTA SET SALDO_TOTAL = (?) WHERE NUMERO_CONTA = (?)");
	    ps.setDouble(1, conta.getSaldo());
	    ps.setInt(2, conta.getNumero());

	    if (ps.executeUpdate() != 0)
	    {
		return true;
	    }

	} catch (Exception e)
	{
	    e.printStackTrace();

	    try
	    {
		DatabaseConnect.getInstance().rollBack();
	    } catch (Exception e1)
	    {
		e1.printStackTrace();
	    }

	} finally
	{
	    try
	    {
		DatabaseConnect.getInstance().closeConnection(ps);
	    } catch (Exception e)
	    {
		e.printStackTrace();
	    }
	}

	return false;
    }

}
