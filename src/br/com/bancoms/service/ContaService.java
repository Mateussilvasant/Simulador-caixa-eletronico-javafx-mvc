package br.com.bancoms.service;

import br.com.bancoms.dao.ContaDAO;
import br.com.bancoms.model.Conta;

public class ContaService {
    private static ContaService contaService;
    private ContaDAO contaDAO;

    private ContaService() {
        contaDAO = new ContaDAO();
    }

    public static ContaService getInstance() {

        if (contaService != null) {
            return contaService;
        }

        contaService = new ContaService();

        return contaService;

    }

    public Conta consultarConta(int numeroConta) {
        return contaDAO.consultarConta(numeroConta);
    }

    private boolean atualizarSaldo(Conta conta) {
        return contaDAO.atualizarSaldo(conta);
    }

    private boolean sacar(Conta contaSessao, double valorOperacao) {
        return contaSessao.sacar(valorOperacao);
    }

    /*
      Realiza deposito na conta passada por parametro.
     */
    public boolean realizarDeposito(Conta conta, double valor) {

        if (conta != null) {
            conta.depositar(valor);
            if (atualizarSaldo(conta)) {
                return true;
            } else {
                sacar(conta, valor);
                return false;
            }
        }
        return false;
    }

}
