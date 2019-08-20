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

    public int registrarMovimento(MovimentoTO movimentoTO) {
        QueryControl control = new QueryControl();
        String sql = "INSERT INTO MOVIMENTO (ID_CONTA,TIPO_MOVIMENTO,DESCRICAO_TIPO,VALOR_MOVIMENTO,DATA_MOVIMENTO) VALUES((?),(?),(?),(?),(?))";

        try {

            control.setSQL(sql, QUERY.RETURN_KEY);

            control.setInt(1, movimentoTO.getIdConta());
            control.setInt(2, movimentoTO.getTipo());
            control.setString(3, movimentoTO.getDescricao());
            control.setDouble(4, movimentoTO.getValor());
            control.setString(5, BancoUtil.getDataAtual());

            if (control.executeUpdate() == RESULT.SUCCESS) {
                control.setGeneratedKeys();

                if (control.next()) {
                    return control.getInt(1);
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

        return 0;
    }

    public ArrayList<Movimento> listarMovimentos(Conta conta) {

        QueryControl control = new QueryControl();
        ArrayList<Movimento> listaMovimentos = new ArrayList<>();
        String sql = "SELECT DATA_MOVIMENTO,DESCRICAO_TIPO,VALOR_MOVIMENTO\r\n"
                + "FROM MOVIMENTO WHERE TIPO_MOVIMENTO in (1,2,3) AND ID_CONTA = (?) order by  DATA_MOVIMENTO desc limit 7";

        try {

            control.setSQL(sql);
            control.setInt(1, conta.getId());
            control.executeQuery();

            while (control.next()) {
                String data = control.getString("DATA_MOVIMENTO");
                String descricao = control.getString("DESCRICAO_TIPO");
                double valor = control.getDouble("VALOR_MOVIMENTO");
                listaMovimentos.add(new Movimento(valor, descricao, data));
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
