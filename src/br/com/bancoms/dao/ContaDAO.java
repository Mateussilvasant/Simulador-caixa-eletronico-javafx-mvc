package br.com.bancoms.dao;

import br.com.bancoms.dao.QueryControl.RESULT;
import br.com.bancoms.model.Conta;
import br.com.bancoms.model.ContaFactory;

import java.util.Optional;

public class ContaDAO {

    public ContaDAO() {
    }

    public boolean atualizarSaldo(Conta conta) {
        String sql = "UPDATE CONTA SET SALDO_TOTAL = (?) WHERE NUMERO_CONTA = (?)";
        QueryControl control = new QueryControl();

        try {

            control.setSQL(sql);
            control.setDouble(1, conta.getSaldo());
            control.setInt(2, conta.getNumero());

            if (control.executeUpdate() == RESULT.SUCCESS) {
                return true;
            }

        } catch (Exception e) {
            try {
                DatabaseConnect.getInstance().rollBack();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                DatabaseConnect.getInstance().closeConnection(control);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Optional<Conta> consultarConta(int numeroConta) {
        Optional<Conta> contaOpt = Optional.empty();
        QueryControl control = new QueryControl();
        String sql = "SELECT ID_CONTA,TIPO_CONTA,DESCRICAO_CONTA,SALDO_TOTAL FROM CONTA WHERE NUMERO_CONTA = (?)";

        try {
            control.setSQL(sql);
            control.setInt(1, numeroConta);
            control.executeQuery();

            if (control.next()) {

                int tipo = control.getInt("TIPO_CONTA");

                Conta conta = ContaFactory.values()[tipo - 1].getConta();

                conta.setTipo(tipo);
                conta.setId(control.getInt("ID_CONTA"));
                conta.setNumero(numeroConta);
                conta.setSaldo(control.getDouble("SALDO_TOTAL"));
                conta.setDescricao(control.getString("DESCRICAO_CONTA"));

                contaOpt = Optional.ofNullable(conta);
            }

        } catch (Exception e) {
            try {
                DatabaseConnect.getInstance().rollBack();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                DatabaseConnect.getInstance().closeConnection(control);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return contaOpt;
    }

}
