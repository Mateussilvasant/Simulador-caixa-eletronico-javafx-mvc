package br.com.bancoms.dao;

import br.com.bancoms.dao.QueryControl.QUERY;
import br.com.bancoms.dao.QueryControl.RESULT;
import br.com.bancoms.dto.MovimentoTO;
import br.com.bancoms.model.Movimento;
import br.com.bancoms.util.DateUtil;

import java.util.ArrayList;
import java.util.Optional;

public class MovimentoDAO {

    public MovimentoDAO() {
    }

    public void registrarMovimento(MovimentoTO movimentoTO) {
        QueryControl control = new QueryControl();
        String sql = "INSERT INTO MOVIMENTO (ID_CONTA,TIPO_MOVIMENTO,DESCRICAO_TIPO,VALOR_MOVIMENTO,DATA_MOVIMENTO,NUMERO_CONTA_ORIGEM,NUMERO_CONTA_DESTINO) VALUES((?),(?),(?),(?),(?),(?),(?))";

        try {

            control.setSQL(sql, QUERY.RETURN_KEY);

            control.setInt(1, movimentoTO.getIdConta());
            control.setInt(2, movimentoTO.getTipo());
            control.setString(3, movimentoTO.getDescricao());
            control.setDouble(4, movimentoTO.getValor());
            control.setTimestamp(5, DateUtil.getCurrentDate());
            control.setInt(6, movimentoTO.getNumeroContaOrigem());
            control.setInt(7, movimentoTO.getNumeroContaDestino());

            if (control.executeUpdate() == RESULT.SUCCESS) {
                control.setGeneratedKeys();

                if (control.next()) {
                    control.getInt(1);
                }

            }

        } catch (Exception e) {

            e.printStackTrace();

            try {
                DatabaseConnect.getInstance().rollBack();
            } catch (Exception rollbackException) {
                rollbackException.printStackTrace();
            }

        } finally {
            try {
                DatabaseConnect.getInstance().closeConnection(control);
            } catch (Exception closeException) {
                closeException.printStackTrace();
            }
        }

    }

    public ArrayList<Optional<Movimento>> listarTodosMovimentos(int numeroConta, String dataInicio, String dataFim) {

        QueryControl control = new QueryControl();

        ArrayList<Optional<Movimento>> listaMovimentos = new ArrayList<>();

        String sql = "SELECT TIPO_MOVIMENTO,DESCRICAO_TIPO,VALOR_MOVIMENTO,DATA_MOVIMENTO, NUMERO_CONTA_ORIGEM, " +
                "NUMERO_CONTA_DESTINO " +
                "from movimento  where  NUMERO_CONTA_ORIGEM = (?)" +
                "AND DATA_MOVIMENTO between (?) AND (?);";

        try {

            control.setSQL(sql);
            control.setInt(1, numeroConta);
            control.setString(2, dataInicio);
            control.setString(3, dataFim);
            control.executeQuery();

            while (control.next()) {
                String data = DateUtil.parseDate(control.getTimestamp("DATA_MOVIMENTO"));
                String descricao = control.getString("DESCRICAO_TIPO");
                int tipo = control.getInt("TIPO_MOVIMENTO");
                double valor = control.getDouble("VALOR_MOVIMENTO");
                int numeroContaOrigem = control.getInt("NUMERO_CONTA_ORIGEM");
                int numeroContaDestino = control.getInt("NUMERO_CONTA_DESTINO");

                Optional<Movimento> movimento = Optional.ofNullable(new Movimento(valor, descricao, tipo, data, numeroContaOrigem, numeroContaDestino));

                listaMovimentos.add(movimento);
            }

        } catch (Exception e) {

            e.printStackTrace();

            try {
                DatabaseConnect.getInstance().rollBack();
            } catch (Exception rollbackException) {
                rollbackException.printStackTrace();
            }

        } finally {
            try {
                DatabaseConnect.getInstance().closeConnection(control);
            } catch (Exception closeException) {
                closeException.printStackTrace();
            }
        }

        return listaMovimentos;

    }

    public ArrayList<Optional<Movimento>> listarMovimentosPorTipo(int numeroConta, int tipoMovimento, String dataInicio, String dataFim) {

        QueryControl control = new QueryControl();

        ArrayList<Optional<Movimento>> listaMovimentos = new ArrayList<>();

        String sql = "SELECT TIPO_MOVIMENTO,DESCRICAO_TIPO,VALOR_MOVIMENTO,DATA_MOVIMENTO, NUMERO_CONTA_ORIGEM, " +
                "NUMERO_CONTA_DESTINO " +
                "from movimento  where TIPO_MOVIMENTO = " +
                "(?) IN (1,2,3)" +
                "AND NUMERO_CONTA_ORIGEM = (?)" +
                "AND DATA_MOVIMENTO between (?) AND (?);";

        try {

            control.setSQL(sql);
            control.setInt(1, tipoMovimento);
            control.setInt(2, numeroConta);
            control.setString(3, dataInicio);
            control.setString(4, dataFim);
            control.executeQuery();

            while (control.next()) {
                String data = DateUtil.parseDate(control.getTimestamp("DATA_MOVIMENTO"));
                String descricao = control.getString("DESCRICAO_TIPO");
                int tipo = control.getInt("TIPO_MOVIMENTO");
                double valor = control.getDouble("VALOR_MOVIMENTO");
                int numeroContaOrigem = control.getInt("NUMERO_CONTA_ORIGEM");
                int numeroContaDestino = control.getInt("NUMERO_CONTA_DESTINO");

                Optional<Movimento> movimento = Optional.ofNullable(new Movimento(valor, descricao, tipo, data, numeroContaOrigem, numeroContaDestino));

                listaMovimentos.add(movimento);
            }

        } catch (Exception e) {

            e.printStackTrace();

            try {
                DatabaseConnect.getInstance().rollBack();
            } catch (Exception rollbackException) {
                rollbackException.printStackTrace();
            }

        } finally {
            try {
                DatabaseConnect.getInstance().closeConnection(control);
            } catch (Exception closeException) {
                closeException.printStackTrace();
            }
        }

        return listaMovimentos;

    }

}
