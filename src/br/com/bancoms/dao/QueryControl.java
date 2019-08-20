package br.com.bancoms.dao;

import java.sql.*;

public class QueryControl {

    enum RESULT {
        SUCCESS, FAILED
    }

    enum QUERY {
        RETURN_KEY
    }

    private PreparedStatement ps;
    private ResultSet rs;

    public QueryControl() {

    }

    public void setSQL(String sql) throws Exception {
        ps = DatabaseConnect.getInstance().getPreparedSQL(sql);
    }

    public void setSQL(String sql, QUERY query) throws Exception {

        if (query == QUERY.RETURN_KEY) {
            ps = DatabaseConnect.getInstance().getPreparedSQL(sql, Statement.RETURN_GENERATED_KEYS);
        }

    }

    public void setInt(int index, int value) throws SQLException {
        ps.setInt(index, value);
    }

    public void setDouble(int index, double value) throws SQLException {
        ps.setDouble(index, value);
    }

    public void setString(int index, String value) throws SQLException {
        ps.setString(index, value);
    }

    public void setDate(int index, Date value) throws SQLException {
        ps.setDate(index, value);
    }

    public RESULT executeUpdate() throws SQLException {

        int result = ps.executeUpdate();

        if (result == 0) {
            return RESULT.FAILED;
        } else {
            return RESULT.SUCCESS;
        }
    }

    public void executeQuery() throws SQLException {
        ResultSet result = ps.executeQuery();

        if (result != null) {
            this.rs = (result);
        }

    }

    public boolean next() throws SQLException {
        if (rs != null) {
            return rs.next();
        }
        return false;
    }

    public int getInt(String index) throws SQLException {

        if (rs != null) {
            return rs.getInt(index);
        }

        return 0;
    }

    public double getDouble(String index) throws SQLException {

        if (rs != null) {
            return rs.getDouble(index);
        }

        return 0;
    }

    public String getString(String index) throws SQLException {

        if (rs != null) {
            return rs.getString(index);
        }

        return null;
    }

    public Date getDate(String index) throws SQLException {

        if (rs != null) {
            return rs.getDate(index);
        }

        return null;
    }

    public void setGeneratedKeys() throws SQLException {
        ResultSet result = ps.getGeneratedKeys();

        if (result != null) {
            this.rs = (result);
        }

    }

    public void closePS() throws SQLException {
        ps.close();
    }

    public void closeRS() throws SQLException {
        ps.close();
    }

    public int getInt(int index) throws SQLException {

        if (rs != null) {
            return rs.getInt(index);
        }

        return 0;
    }

}
