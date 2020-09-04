package br.com.bancoms.dao;

import br.com.bancoms.dto.LoginDTO;
import br.com.bancoms.model.Cliente;

import java.util.Optional;

public class ClienteDAO {
    public ClienteDAO() {
    }

    public Optional<Cliente> realizarLogin(LoginDTO loginDTO) {
        QueryControl query = null;
        Optional<Cliente> cliente = Optional.empty();

        try {
            String sql = "SELECT CLIENTE.NOME, CLIENTE.SOBRENOME "
                    + "from CONTA JOIN CLIENTE ON CONTA.ID_CLIENTE = CLIENTE.ID_CLIENTE "
                    + "WHERE NUMERO_CONTA = (?) and SENHA = (?)";

            query = new QueryControl();
            query.setSQL(sql);
            query.setInt(1, loginDTO.getNumeroConta());
            query.setString(2, loginDTO.getSenha());
            query.executeQuery();

            if (query.next()) {
                cliente = Optional.of(new Cliente(query.getString("CLIENTE.NOME"), query.getString("CLIENTE.SOBRENOME")));
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

    public Optional<Cliente> consultarCliente(int numeroConta) {
        QueryControl query = null;
        Optional<Cliente> cliente = Optional.empty();

        try {
            String sql = "SELECT CLIENTE.NOME, CLIENTE.SOBRENOME "
                    + "from CONTA JOIN CLIENTE ON CONTA.ID_CLIENTE = CLIENTE.ID_CLIENTE "
                    + "WHERE NUMERO_CONTA = (?)";

            query = new QueryControl();
            query.setSQL(sql);
            query.setInt(1, numeroConta);
            query.executeQuery();

            if (query.next()) {
                cliente = Optional.of(new Cliente(query.getString("CLIENTE.NOME"), query.getString("CLIENTE.SOBRENOME")));
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
