package br.com.bancoms.service;

import br.com.bancoms.dao.ContaDAO;
import br.com.bancoms.dto.MovimentoTO;
import br.com.bancoms.model.Conta;
import br.com.bancoms.model.Movimento;

import java.util.ArrayList;
import java.util.Optional;

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

    public Optional<Conta> consultarConta(int numeroConta) {
        return contaDAO.consultarConta(numeroConta);
    }

    private boolean atualizarSaldo(Conta conta) {
        return contaDAO.atualizarSaldo(conta);
    }


    private boolean sacar(Conta contaSessao, double valorOperacao) {
        return contaSessao.sacar(valorOperacao);
    }

    /*Realiza o depósito na conta informada e retorna com resultado o Movimento realizado*/
    public MovimentoTO realizarDeposito(Conta conta, double valor) throws Exception {

        conta.depositar(valor);

        if (atualizarSaldo(conta)) {
            return new MovimentoTO(conta.getId(), valor, MovimentoTO.DEPOSITO, "DEPÓSITO CONTA-PRÓPRIA", conta.getNumero(), conta.getNumero());
        } else {
            conta.sacar(valor);
            throw new Exception("Não foi possível realizar o depósito");
        }

    }

    /*Realiza o depósito na conta do número informado e retorna como resultado os movimentos realizado.*/
    public ArrayList<MovimentoTO> realizarDeposito(Conta contaOrigem, int numeroContaDestino, double valor) throws Exception {

        ArrayList<MovimentoTO> listaMovimentosRealizado = new ArrayList<>();

        Optional<Conta> contaDestinoOpt = consultarConta(numeroContaDestino);

        if (contaDestinoOpt.isPresent()) {

            Conta contaDestino = contaDestinoOpt.get();

            contaDestino.depositar(valor);

            if (atualizarSaldo(contaDestino)) {
                listaMovimentosRealizado.add(new MovimentoTO(contaOrigem.getId(), valor, MovimentoTO.DEPOSITO, "DEPÓSITO OUTRA-CONTA", contaOrigem.getNumero(), contaDestino.getNumero()));
                listaMovimentosRealizado.add(new MovimentoTO(contaDestino.getId(), valor, MovimentoTO.DEPOSITO, "DEPÓSITO OUTRA-CONTA", contaOrigem.getNumero(), contaDestino.getNumero()));
            } else {
                contaDestino.sacar(valor); //Retira o dinheiro da conta
                throw new Exception("Não foi possível realizar o depósito.");
            }

        } else {
            throw new Exception("Conta informada inválida");
        }

        return listaMovimentosRealizado;

    }


    /*Realiza o saque de dinheiro na Conta e retorna o movimento realizado. */
    public MovimentoTO realizarSaque(Conta conta, double valor) throws Exception {

        if (conta.sacar(valor)) {

            if (atualizarSaldo(conta)) {
                return new MovimentoTO(conta.getId(), valor, Movimento.SAQUE, "SAQUE-NORMAL", conta.getNumero(), conta.getNumero());
            } else {
                conta.depositar(valor); //devolve o dinheiro para conta
                throw new Exception("Não foi possível realizar o saque.");
            }

        } else {
            throw new Exception("Valor maior que o saldo.");
        }

    }

    public ArrayList<MovimentoTO> realizarTransferencia(Conta contaOrigem, int numeroContaDestino, double valor) throws Exception {

        ArrayList<MovimentoTO> movimentosRealizados = new ArrayList<>();
        Optional<Conta> contaBeneficiadaOpt = consultarConta(numeroContaDestino);

        if (contaBeneficiadaOpt.isPresent()) {

            Conta contaBeneficiada = contaBeneficiadaOpt.get();

            if (contaOrigem.transferir(valor, contaBeneficiada)) {

                if (atualizarSaldo(contaOrigem)) {
                    movimentosRealizados.add(new MovimentoTO(contaOrigem.getId(), valor, Movimento.TRANSFERENCIA, "TRANSFERÊNCIA", contaOrigem.getNumero(), contaBeneficiada.getNumero()));
                }
                if (atualizarSaldo(contaBeneficiada)) {
                    movimentosRealizados.add(new MovimentoTO(contaBeneficiada.getId(), valor, Movimento.TRANSFERENCIA, "TRANSFERÊNCIA", contaOrigem.getNumero(), contaBeneficiada.getNumero()));
                }

            } else {
                throw new Exception("Não foi possível realizar transferência..");
            }

        } else {
            throw new Exception("Conta informada não existe.");
        }

        return movimentosRealizados;
    }

}
