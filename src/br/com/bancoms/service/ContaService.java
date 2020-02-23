package br.com.bancoms.service;

import br.com.bancoms.dao.ContaDAO;
import br.com.bancoms.dto.TransacaoDTO;
import br.com.bancoms.model.Movimento;
import br.com.bancoms.model.contas.Conta;

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

    /*Realiza o depósito na conta informada e retorna com resultado o Movimento realizado*/
    public Movimento realizarDepositoPropria(TransacaoDTO transacao) throws Exception {

        Conta conta = transacao.getContaOrigem();
        double valor = transacao.getValorTransacao();

        conta.depositar(valor);

        if (atualizarSaldo(conta)) {
            return new Movimento(conta.getId(), valor, Movimento.EMovimento.DEPOSITO.getValue(), "DEPÓSITO CONTA-PRÓPRIA", conta.getNumero(), conta.getNumero());
        } else {
            conta.sacar(valor);
            throw new Exception("Não foi possível realizar o depósito");
        }

    }

    /*Realiza o depósito na conta do número informado e retorna como resultado os movimentos realizado.*/
    public ArrayList<Movimento> realizarDeposito(TransacaoDTO transacao) throws Exception {

        ArrayList<Movimento> listaMovimentosRealizado = new ArrayList<>();

        Optional<Conta> contaDestinoOpt = consultarConta(transacao.getNumeroContaDestino());

        Conta contaOrigem = transacao.getContaOrigem();
        double valor = transacao.getValorTransacao();

        if (contaDestinoOpt.isPresent()) {

            Conta contaDestino = contaDestinoOpt.get();

            contaDestino.depositar(valor);

            if (atualizarSaldo(contaDestino)) {
                listaMovimentosRealizado.add(new Movimento(contaOrigem.getId(), valor, Movimento.EMovimento.DEPOSITO.getValue(), "DEPÓSITO OUTRA-CONTA", contaOrigem.getNumero(), contaDestino.getNumero()));
                listaMovimentosRealizado.add(new Movimento(contaDestino.getId(), valor, Movimento.EMovimento.DEPOSITO.getValue(), "DEPÓSITO OUTRA-CONTA", contaOrigem.getNumero(), contaDestino.getNumero()));
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
    public Movimento realizarSaque(TransacaoDTO transacao) throws Exception {

        Conta conta = transacao.getContaOrigem();
        double valor = transacao.getValorTransacao();

        if (conta.sacar(valor)) {

            if (atualizarSaldo(conta)) {
                return new Movimento(conta.getId(), valor, Movimento.EMovimento.SAQUE.getValue(), "SAQUE-NORMAL", conta.getNumero(), conta.getNumero());
            } else {
                conta.depositar(valor); //devolve o dinheiro para conta
                throw new Exception("Não foi possível realizar o saque.");
            }

        } else {
            throw new Exception("Valor maior que o saldo.");
        }

    }

    public ArrayList<Movimento> realizarTransferencia(TransacaoDTO transacao) throws Exception {

        ArrayList<Movimento> movimentosRealizados = new ArrayList<>();
        Optional<Conta> contaBeneficiadaOpt = consultarConta(transacao.getNumeroContaDestino());

        double valor = transacao.getValorTransacao();
        Conta contaOrigem = transacao.getContaOrigem();


        if (contaBeneficiadaOpt.isPresent()) {

            Conta contaBeneficiada = contaBeneficiadaOpt.get();

            if (contaOrigem.transferir(valor, contaBeneficiada)) {

                if (atualizarSaldo(contaOrigem)) {
                    movimentosRealizados.add(new Movimento(contaOrigem.getId(), valor, Movimento.EMovimento.TRANSFERENCIA.getValue(), "TRANSFERÊNCIA", contaOrigem.getNumero(), contaBeneficiada.getNumero()));
                }
                if (atualizarSaldo(contaBeneficiada)) {
                    movimentosRealizados.add(new Movimento(contaBeneficiada.getId(), valor,Movimento.EMovimento.TRANSFERENCIA.getValue(), "TRANSFERÊNCIA", contaOrigem.getNumero(), contaBeneficiada.getNumero()));
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
