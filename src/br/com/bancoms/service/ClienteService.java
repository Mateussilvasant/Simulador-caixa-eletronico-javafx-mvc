package br.com.bancoms.service;

import br.com.bancoms.dao.ClienteDAO;
import br.com.bancoms.dto.LoginDTO;
import br.com.bancoms.model.Cliente;

import java.util.Optional;

public class ClienteService {

    private static ClienteService clienteService;
    private ClienteDAO clienteDAO;

    private ClienteService() {
        clienteDAO = new ClienteDAO();
    }

    public static ClienteService getInstance() {

        if (clienteService != null) {
            return clienteService;
        }

        clienteService = new ClienteService();

        return clienteService;

    }

    public Optional<Cliente> realizarLogin(LoginDTO loginDTO) {
        return clienteDAO.realizarLogin(loginDTO);
    }

    public Optional<Cliente> consultarCliente(int numeroConta) {
        return clienteDAO.consultarCliente(numeroConta);
    }

}
