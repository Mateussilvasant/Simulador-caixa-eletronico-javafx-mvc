package br.com.bancoms.dao;

import br.com.bancoms.dao.QueryControl.RESULT;
import br.com.bancoms.model.contas.Conta;
import br.com.bancoms.model.Emprestimo;

import java.util.Optional;

public class EmprestimoDAO {
    public Optional<Emprestimo> consultarEmprestimo(Conta conta) {
        QueryControl control = new QueryControl();
        Optional<Emprestimo> emprestimo = Optional.empty();
        String sql = "SELECT EMPRESTIMO.VALOR_EMPRESTADO,EMPRESTIMO.VALOR_PARCELADO,EMPRESTIMO.QTD_PARCELAS "
                + "FROM EMPRESTIMO JOIN MOVIMENTO " + "ON MOVIMENTO.ID_MOVIMENTO = EMPRESTIMO.ID_MOVIMENTO "
                + "WHERE MOVIMENTO.ID_CONTA = (?) AND MOVIMENTO.TIPO_MOVIMENTO = 4";

        try {
            control.setSQL(sql);
            control.setInt(1, conta.getId());
            control.executeQuery();

            if (control.next()) {
                double valorMovimento = control.getDouble("EMPRESTIMO.VALOR_EMPRESTADO");
                double valorParcelado = control.getDouble("EMPRESTIMO.VALOR_PARCELADO");
                int quantidadeParcelas = control.getInt("EMPRESTIMO.QTD_PARCELAS");
                emprestimo = Optional.of(new Emprestimo(valorMovimento, valorParcelado, quantidadeParcelas));

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
        return emprestimo;
    }

    public boolean atualizarEmprestimo(double valorEmprestado, int idConta) {
        QueryControl control = new QueryControl();
        String sql = "UPDATE MOVIMENTO SET  VALOR_MOVIMENTO = (?) \n" + "WHERE ID_CONTA = (?) AND TIPO_MOVIMENTO = 4";

        try {

            control.setSQL(sql);
            control.setDouble(1, valorEmprestado);
            control.setInt(2, idConta);

            if (control.executeUpdate() == RESULT.SUCCESS) {
                return true;
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

        return false;
    }

    public boolean cadastrarEmprestimo(Emprestimo emprestimo, int idMovimento) {
        QueryControl control = new QueryControl();
        String sql = "INSERT INTO EMPRESTIMO(VALOR_EMPRESTADO,VALOR_PARCELADO,QTD_PARCELAS,ID_MOVIMENTO) VALUES((?),(?),(?),(?))";

        try {

            control.setSQL(sql);
            control.setDouble(1, emprestimo.getValorEmprestado());
            control.setDouble(2, emprestimo.getValorParcelado());
            control.setInt(3, emprestimo.getParcelas());
            control.setInt(4, idMovimento);

            if (control.executeUpdate() == RESULT.SUCCESS) {
                return true;
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

        return false;
    }

}
