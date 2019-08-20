package br.com.bancoms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnect {
    private Connection conexao;
    private static DatabaseConnect databaseConnect;

    private DatabaseConnect() {
        try {
            carregarJDBC();
            abrirConexaoMYSQL();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getPreparedSQL(String sql) throws Exception {
        PreparedStatement prepared;
        try {
            abrirConexaoMYSQL();
            prepared = conexao.prepareStatement(sql);
        } catch (Exception e) {
            throw new Exception(
                    "DatabaseConnect -> getPreparedSQL() -> 32 - Não foi possível preparar a Query -> Erro: " + "\n"
                            + e.getLocalizedMessage());
        }
        return prepared;
    }

    private void abrirConexaoMYSQL() throws Exception {
        try {
            String senha = "pass123";
            String usuario = "root";
            String nomeBanco = "banco_ms";
            setConexao(DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + nomeBanco + "?useTimezone=true&serverTimezone=America/Sao_Paulo",
                    usuario, senha));
        } catch (SQLException e) {
            throw new Exception(
                    "DatabaseConnect -> abrirConexaoMYSQL() -> 32 - Erro ao abrir conexao com o MYSQL -> Erro: " + "\n"
                            + e.getLocalizedMessage());
        }
    }

    private void carregarJDBC() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            throw new Exception("DatabaseConnect -> carregarJDBC() -> 45 - Não foi possivel carregar o JDBC -> Erro: "
                    + "\n" + e.getLocalizedMessage());
        }
    }

    public static DatabaseConnect getInstance() {
        if (databaseConnect != null) {
            return databaseConnect;
        }
        return (databaseConnect = new DatabaseConnect());
    }

    public Connection getConexao() {
        return conexao;
    }

    private void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    public void closeConnection(PreparedStatement ps) throws Exception {
        try {

            if (ps != null) {
                ps.close();
            }

            conexao.close();

        } catch (SQLException e) {
            throw new Exception(
                    "DatabaseConnect -> closeConnection() -> 95 - Não foi possivel fechar a conexão JDBC -> Erro: "
                            + "\n" + e.getLocalizedMessage());
        }
    }

    public void closeConnection(QueryControl query) throws Exception {
        try {
            if (query != null) {
                query.closePS();
                query.closeRS();
                conexao.close();
            }

        } catch (SQLException e) {
            throw new Exception(
                    "DatabaseConnect -> closeConnection() -> 95 - Não foi possivel fechar a conexão JDBC -> Erro: "
                            + "\n" + e.getLocalizedMessage());
        }
    }

    public void rollBack() throws Exception {
        try {
            conexao.rollback();
        } catch (SQLException e) {
            throw new Exception("DatabaseConnect -> rollBack() -> 126 - Não foi possivel realizar o rollback -> Erro: "
                    + "\n" + e.getLocalizedMessage());
        }
    }

    public PreparedStatement getPreparedSQL(String sql, int returnGeneratedKeys) throws Exception {
        PreparedStatement prepared;
        try {
            abrirConexaoMYSQL();
            prepared = conexao.prepareStatement(sql, returnGeneratedKeys);
        } catch (Exception e) {
            throw new Exception(
                    "DatabaseConnect -> getPreparedSQL() -> 32 - Não foi possível preparar a Query -> Erro: " + "\n"
                            + e.getLocalizedMessage());
        }
        return prepared;
    }

}
