package br.com.bancoms.dao;

import br.com.bancoms.model.Cliente;
import br.com.bancoms.vo.ClienteVO;

public class ClienteDAO {
    public ClienteDAO() {
    }

    public Cliente realizarLogin(ClienteVO clienteVO) {
        QueryControl query = null;
        Cliente cliente = null;

        try {
            String sql = "SELECT CLIENTE.NOME, CLIENTE.SOBRENOME "
                    + "from CONTA JOIN CLIENTE ON CONTA.ID_CLIENTE = CLIENTE.ID_CLIENTE "
                    + "WHERE NUMERO_CONTA = (?) and SENHA = (?)";

            query = new QueryControl();
            query.setSQL(sql);
            query.setInt(1, clienteVO.getNumeroConta());
            query.setString(2, clienteVO.getSenha());
            query.executeQuery();

            if (query.next()) {
                cliente = new Cliente(query.getString("CLIENTE.NOME"), query.getString("CLIENTE.SOBRENOME"));
            }

        } catch (Exception e) {

            System.out.println(e.toString());

            try {
                DatabaseConnect.getInstance().rollBack();
            } catch (Exception rollbackException) {
                System.out.println(rollbackException.toString());
            }

        } finally {
            try {

                if (query != null) {
                    DatabaseConnect.getInstance().closeConnection(query);
                }

            } catch (Exception closeException) {
                System.out.println(closeException.toString());
            }
        }
        return cliente;
    }

}
