package br.com.bancoms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.bancoms.model.Conta;
import br.com.bancoms.model.Movimento;
import br.com.bancoms.util.BancoUtil;

public class MovimentoDAO
{

    public MovimentoDAO()
    {
    }

    public int cadastrarMovimento(Movimento movimento, Conta conta)
    {

	PreparedStatement ps = null;
	ResultSet rs = null;

	try
	{

	    String sql = "INSERT INTO MOVIMENTO (ID_CONTA,TIPO_MOVIMENTO,DESCRICAO_TIPO,VALOR_MOVIMENTO,DATA_MOVIMENTO) VALUES((?),(?),(?),(?),(?))";
	    ps = DatabaseConnect.getInstance().getPreparedSQL(sql, Statement.RETURN_GENERATED_KEYS);

	    ps.setInt(1, conta.getId());
	    ps.setInt(2, movimento.getTipo());
	    ps.setString(3, movimento.getDescricao());
	    ps.setDouble(4, movimento.getValor());
	    ps.setString(5, BancoUtil.getDataAtual());

	    if (ps.executeUpdate() != 0)
	    {
		rs = ps.getGeneratedKeys();

		if (rs.next())
		{
		    return rs.getInt(1);
		}

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

	return 0;
    }

    public ArrayList<Movimento> listarMovimentos(Conta conta)
    {

	PreparedStatement ps = null;
	ResultSet rs = null;
	ArrayList<Movimento> listaMovimentos = new ArrayList<Movimento>();

	try
	{

	    String sql = "SELECT DATA_MOVIMENTO,DESCRICAO_TIPO,VALOR_MOVIMENTO\r\n"
		    + "FROM MOVIMENTO WHERE TIPO_MOVIMENTO in (1,2,3) AND ID_CONTA = (?) order by  DATA_MOVIMENTO desc limit 7";
	    ps = DatabaseConnect.getInstance().getPreparedSQL(sql);
	    ps.setInt(1, conta.getId());
	    rs = ps.executeQuery();

	    while (rs.next())
	    {
		String data = rs.getString("DATA_MOVIMENTO");
		String descricao = rs.getString("DESCRICAO_TIPO");
		double valor = rs.getDouble("VALOR_MOVIMENTO");
		listaMovimentos.add(new Movimento(valor, descricao, data));
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

	return listaMovimentos;

    }

}
