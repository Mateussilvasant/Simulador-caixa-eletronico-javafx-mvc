package br.com.bancoms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.bancoms.model.Conta;
import br.com.bancoms.model.Emprestimo;

public class EmprestimoDAO
{
    public Emprestimo consultarEmprestimo(Conta conta)
    {
	PreparedStatement ps = null;
	ResultSet rs = null;
	Emprestimo emprestimo = null;

	try
	{
	    String sql = "SELECT EMPRESTIMO.VALOR_EMPRESTADO,EMPRESTIMO.VALOR_PARCELADO,EMPRESTIMO.QTD_PARCELAS "
		    + "FROM EMPRESTIMO JOIN MOVIMENTO " + "ON MOVIMENTO.ID_MOVIMENTO = EMPRESTIMO.ID_MOVIMENTO "
		    + "WHERE MOVIMENTO.ID_CONTA = (?) AND MOVIMENTO.TIPO_MOVIMENTO = 4";

	    ps = DatabaseConnect.getInstance().getPreparedSQL(sql);
	    ps.setInt(1, conta.getId());
	    rs = ps.executeQuery();

	    if (rs.next())
	    {
		double valorMovimento = rs.getDouble("EMPRESTIMO.VALOR_EMPRESTADO");
		double valorParcelado = rs.getDouble("EMPRESTIMO.VALOR_PARCELADO");
		int quantidadeParcelas = rs.getInt("EMPRESTIMO.QTD_PARCELAS");
		emprestimo = new Emprestimo(valorMovimento, valorParcelado, quantidadeParcelas);
	    }
	} catch (Exception e)
	{

	    e.printStackTrace();

	    try
	    {
		DatabaseConnect.getInstance().rollBack();
	    } catch (Exception rollbackException)
	    {
		rollbackException.printStackTrace();
	    }

	} finally
	{
	    try
	    {
		DatabaseConnect.getInstance().closeConnection(ps, rs);
	    } catch (Exception closeException)
	    {
		closeException.printStackTrace();
	    }
	}
	return emprestimo;
    }

    public boolean atualizarEmprestimo(double valorEmprestado, int idConta)
    {
	PreparedStatement ps = null;

	try
	{
	    String sql = "UPDATE MOVIMENTO SET  VALOR_MOVIMENTO = (?) \n"
		    + "WHERE ID_CONTA = (?) AND TIPO_MOVIMENTO = 4";

	    ps = DatabaseConnect.getInstance().getPreparedSQL(sql);
	    ps.setDouble(1, valorEmprestado);
	    ps.setInt(2, idConta);

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
	    } catch (Exception rollbackException)
	    {
		rollbackException.printStackTrace();
	    }

	} finally
	{
	    try
	    {
		DatabaseConnect.getInstance().closeConnection(ps);
	    } catch (Exception closeException)
	    {
		closeException.printStackTrace();
	    }
	}

	return false;
    }

    public boolean cadastrarEmprestimo(Emprestimo emprestimo, int idMovimento)
    {
	PreparedStatement ps = null;

	try
	{
	    String sql = "INSERT INTO EMPRESTIMO(VALOR_EMPRESTADO,VALOR_PARCELADO,QTD_PARCELAS,ID_MOVIMENTO) VALUES((?),(?),(?),(?))";

	    ps = DatabaseConnect.getInstance().getPreparedSQL(sql);
	    ps.setDouble(1, emprestimo.getValorEmprestado());
	    ps.setDouble(2, emprestimo.getValorParcelado());
	    ps.setInt(3, emprestimo.getParcelas());
	    ps.setInt(4, idMovimento);

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
	    } catch (Exception rollbackException)
	    {
		rollbackException.printStackTrace();
	    }

	} finally
	{
	    try
	    {
		DatabaseConnect.getInstance().closeConnection(ps);
	    } catch (Exception closeException)
	    {
		closeException.printStackTrace();
	    }
	}

	return false;
    }

}
