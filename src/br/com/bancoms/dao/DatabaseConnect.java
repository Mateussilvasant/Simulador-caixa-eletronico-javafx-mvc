package br.com.bancoms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnect
{
    private Connection conexao;
    private String nomeBanco = "banco_ms";
    private String usuario = "root";
    private String senha = "pass123";
    public static DatabaseConnect databaseConnect;

    public DatabaseConnect()
    {
	try
	{
	    carregarJDBC();
	    abrirConexaoMYSQL();

	} catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

    public PreparedStatement getPreparedSQL(String sql) throws Exception
    {
	PreparedStatement prepared = null;
	try
	{
	    abrirConexaoMYSQL();
	    prepared = conexao.prepareStatement(sql);
	} catch (Exception e)
	{
	    throw new Exception(
		    "DatabaseConnect -> getPreparedSQL() -> 32 - N�o foi poss�vel preparar a Query -> Erro: " + "\n"
			    + e.getLocalizedMessage());
	}
	return prepared;
    }

    private void abrirConexaoMYSQL() throws Exception
    {
	try
	{
	    setConexao(DriverManager.getConnection(
		    "jdbc:mysql://localhost:3306/" + nomeBanco + "?useTimezone=true&serverTimezone=America/Sao_Paulo",
		    usuario, senha));
	} catch (SQLException e)
	{
	    throw new Exception(
		    "DatabaseConnect -> abrirConexaoMYSQL() -> 32 - Erro ao abrir conexao com o MYSQL -> Erro: " + "\n"
			    + e.getLocalizedMessage());
	}
    }

    private void carregarJDBC() throws Exception
    {
	try
	{
	    Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (Exception e)
	{
	    throw new Exception("DatabaseConnect -> carregarJDBC() -> 45 - N�o foi possivel carregar o JDBC -> Erro: "
		    + "\n" + e.getLocalizedMessage());
	}
    }

    public static DatabaseConnect getInstance()
    {
	if (databaseConnect != null)
	{
	    return databaseConnect;
	}
	return (databaseConnect = new DatabaseConnect());
    }

    public Connection getConexao()
    {
	return conexao;
    }

    public void setConexao(Connection conexao)
    {
	this.conexao = conexao;
    }

    public void closeConnection(PreparedStatement ps) throws Exception
    {
	try
	{

	    if (ps != null)
	    {
		ps.close();
	    }

	    conexao.close();

	} catch (SQLException e)
	{
	    throw new Exception(
		    "DatabaseConnect -> closeConnection() -> 95 - N�o foi possivel fechar a conex�o JDBC -> Erro: "
			    + "\n" + e.getLocalizedMessage());
	}
    }

    public void closeConnection(PreparedStatement ps, ResultSet result) throws Exception
    {
	try
	{
	    if (result != null)
	    {
		result.close();
	    }
	    if (ps != null)
	    {
		ps.close();
	    }

	    conexao.close();

	} catch (SQLException e)
	{
	    throw new Exception(
		    "DatabaseConnect -> closeConnection() -> 95 - N�o foi possivel fechar a conex�o JDBC -> Erro: "
			    + "\n" + e.getLocalizedMessage());
	}
    }

    public void rollBack() throws Exception
    {
	try
	{
	    conexao.rollback();
	} catch (SQLException e)
	{
	    throw new Exception("DatabaseConnect -> rollBack() -> 126 - N�o foi possivel realizar o rollback -> Erro: "
		    + "\n" + e.getLocalizedMessage());
	}
    }

    public PreparedStatement getPreparedSQL(String sql, int returnGeneratedKeys) throws Exception
    {
	PreparedStatement prepared = null;
	try
	{
	    abrirConexaoMYSQL();
	    prepared = conexao.prepareStatement(sql, returnGeneratedKeys);
	} catch (Exception e)
	{
	    throw new Exception(
		    "DatabaseConnect -> getPreparedSQL() -> 32 - N�o foi poss�vel preparar a Query -> Erro: " + "\n"
			    + e.getLocalizedMessage());
	}
	return prepared;
    }

}
