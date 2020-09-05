package br.com.bancoms.controller;

import br.com.bancoms.components.dialogAlert.DialogAlert;
import br.com.bancoms.components.tecladoComponent.TecladoAdapter;
import br.com.bancoms.components.tecladoComponent.tipos.Teclado;
import br.com.bancoms.dto.TransacaoDTO;
import br.com.bancoms.model.Movimento;
import br.com.bancoms.model.contas.Conta;
import br.com.bancoms.service.ContaService;
import br.com.bancoms.service.MovimentoService;
import br.com.bancoms.view.DepositoView;
import br.com.bancoms.view.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;


public class DepositoController {

    enum EDepositoController {

        OUTRA_CONTA(2), CONTA_PROPRIA(1);

        private int value;

        EDepositoController(int value) {
            this.value = value;
        }

        public int getValue(){
            return value;
        }

    }

    private MovimentoService movimentoService = MovimentoService.getInstance();
    private ContaService contaService = ContaService.getInstance();

    private DepositoView depositoView;
    private TecladoAdapter tecladoAdapter;
    private EDepositoController opcaoTipoDepositoEscolhido;
    public ClienteController clienteController;


    public DepositoController(ClienteController clienteController) {
        this.tecladoAdapter = new TecladoAdapter();
        this.clienteController = clienteController;
        this.depositoView = new DepositoView(this);
    }

    public void iniciarDeposito(MainView view) {
        depositoView.iniciarFormTipoDeposito(tecladoAdapter, view);
    }

    public EventHandler<ActionEvent> contaPropriaAction() {
        return (event) ->
        {
            tecladoAdapter.anexarTeclado(Teclado.TecladoEvent.DOUBLE, null);
            opcaoTipoDepositoEscolhido = EDepositoController.CONTA_PROPRIA;
            depositoView.iniciarFormValor(tecladoAdapter);
        };
    }

    public EventHandler<ActionEvent> outraContaAction() {
        return (event) ->
        {
            tecladoAdapter.anexarTeclado(Teclado.TecladoEvent.INTEGER, depositoView.fieldNumeroConta);
            opcaoTipoDepositoEscolhido = EDepositoController.OUTRA_CONTA;
            depositoView.iniciarFormOutraConta(tecladoAdapter);
        };
    }

    public EventHandler<ActionEvent> numeroContaAction() {
        return (event) ->
        {
            if (tecladoAdapter.getTeclado().verificarValor()) {
                tecladoAdapter.anexarTeclado(Teclado.TecladoEvent.DOUBLE, null);
                depositoView.iniciarFormValor(tecladoAdapter);
            } else {
                validacaoErro("Informe o número da conta.");
            }
        };
    }

    public EventHandler<ActionEvent> realizarDepositoAction() {
        return (event) ->
        {
            if (tecladoAdapter.getTeclado().verificarValor()) {

                double valor = Double.parseDouble(tecladoAdapter.getTeclado().getTextoField());

                if (opcaoTipoDepositoEscolhido.equals(EDepositoController.CONTA_PROPRIA)) {
                    realizarDeposito(clienteController.getContaSessao(), valor);
                } else {
                    realizarDeposito(clienteController.getContaSessao(), Integer.parseInt(depositoView.fieldNumeroConta.getText()), valor);
                }

            } else {
                validacaoErro("Informe um valor.");
            }

        };
    }


    /*Realiza depósito em outra conta */
    private void realizarDeposito(Conta contaOrigem, int numeroConta, double valor) {
        try {

            TransacaoDTO transacaoDTO = new TransacaoDTO(contaOrigem, numeroConta, valor);
            ArrayList<Movimento> movimentosRealizado;

            if (!((movimentosRealizado = contaService.realizarDeposito(transacaoDTO)).isEmpty())) {
                movimentoService.registrarMovimentos(movimentosRealizado);
                depositoRealizado();
            }

        } catch (Exception e) {
            depositoNaoRealizado(e.getMessage());
        }
    }

    /*Realiza depósito na conta Própria */
    private void realizarDeposito(Conta conta, double valor) {
        try {

            TransacaoDTO transacaoDTO = new TransacaoDTO(conta, valor);
            Movimento movimento;

            if ((movimento = contaService.realizarDepositoPropria(transacaoDTO)) != null) {
                movimentoService.registrarMovimento(movimento);
                depositoRealizado();
            }
        } catch (Exception e) {
            depositoNaoRealizado(e.getMessage());

        }
    }


    private void depositoRealizado() {
        clienteController.view.atualizarSaldoView(clienteController.getContaSessao().getSaldo());

        DialogAlert alert = clienteController.view.onAlertView("Depósito Informação",
                "Depósito realizado com sucesso!", DialogAlert.AlertType.INFORMATION, true);
        alert.setEventInformation(e -> {
            clienteController.retornarMenuAction(depositoView);
            alert.fecharDialog();
        });
    }

    private void depositoNaoRealizado(String mensagem) {
        DialogAlert alert = clienteController.view.onAlertView("Depósito Informação",
                mensagem, DialogAlert.AlertType.INFORMATION, true);
        alert.setEventInformation(e -> {
            clienteController.retornarMenuAction(depositoView);
            alert.fecharDialog();
        });
    }

    private void validacaoErro(String mensagem) {
        DialogAlert alert = clienteController.view.onAlertView("Validação - Informação",
                mensagem, DialogAlert.AlertType.INFORMATION, false);
        alert.setEventInformation(e -> alert.fecharDialog());

    }
}
