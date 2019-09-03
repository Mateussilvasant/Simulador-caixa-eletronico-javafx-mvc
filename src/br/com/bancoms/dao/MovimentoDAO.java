package br.com.bancoms.dao;

import br.com.bancoms.dao.QueryControl.QUERY;
import br.com.bancoms.dao.QueryControl.RESULT;
import br.com.bancoms.dto.MovimentoTO;
import br.com.bancoms.model.Conta;
import br.com.bancoms.model.Movimento;
import br.com.bancoms.util.BancoUtil;

import java.util.ArrayList;

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
            control.setString(5, BancoUtil.getDataAtual());
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

    public ArrayList<Movimento> listarMovimentos(Conta conta) {

        QueryControl control = new QueryControl();
        ArrayList<Movimento> listaMovimentos = new ArrayList<>();
        String sql = "SELECT ID_MOVIMENTO, DATA_MOVIMENTO,DESCRICAO_TIPO, TIPO_MOVIMENTO, VALOR_MOVIMENTO,NUMERO_CONTA_ORIGEM, NUMERO_CONTA_DESTINO\r\n"
                + "FROM MOVIMENTO WHERE TIPO_MOVIMENTO in (1,2,3) AND ID_CONTA = (?) order by  DATA_MOVIMENTO desc limit 7";

        try {

            control.setSQL(sql);
            control.setInt(1, conta.getId());
            control.executeQuery();

            while (control.next()) {
                int idMovimento = control.getInt("ID_MOVIMENTO");
                String data = control.getString("DATA_MOVIMENTO");
                String descricao = control.getString("DESCRICAO_TIPO");
                int tipo = control.getInt("TIPO_MOVIMENTO");
                double valor = control.getDouble("VALOR_MOVIMENTO");
                int numeroContaOrigem = control.getInt("NUMERO_CONTA_ORIGEM");
                int numeroContaDestino = control.getInt("NUMERO_CONTA_DESTINO");

                listaMovimentos.add(new Movimento(idMovimento,valor,descricao,tipo,data,numeroContaOrigem,numeroContaDestino));
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
