package br.com.bancoms.controller;

import br.com.bancoms.components.DoubleTeclado;
import br.com.bancoms.components.Teclado;
import br.com.bancoms.dto.MovimentoTO;
import br.com.bancoms.model.Conta;
import br.com.bancoms.service.ContaService;
import br.com.bancoms.service.MovimentoService;
import br.com.bancoms.view.DepositoView;
import br.com.bancoms.view.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

import java.util.ArrayList;


public class DepositoController {

    enum EDepositoController {

        OUTRA_CONTA(2), CONTA_PROPRIA(1);

        private int value;

        EDepositoController(int value) {
            this.value = value;
        }

    }

    private DepositoView depositoView;
    private TecladoController tecladoController;
    private ClienteController clienteController;
    private EDepositoController opcaoTipoDepositoEscolhido;

    public DepositoController(ClienteController clienteController) {
        this.tecladoController = new TecladoController();
        this.depositoView = new DepositoView(this);
        this.clienteController = clienteController;
    }

    public void iniciarDeposito(MainView view) {
        depositoView.iniciarFormTipoDeposito(tecladoController, view);
    }

    public EventHandler<ActionEvent> contaPropriaAction() {
        return (event) ->
        {
            tecladoController.anexarTeclado(Teclado.Tipo.DOUBLE, null);
            opcaoTipoDepositoEscolhido = EDepositoController.CONTA_PROPRIA;
            depositoView.iniciarFormValor(tecladoController);
        };
    }

    public EventHandler<ActionEvent> outraContaAction() {
        return (event) ->
        {
            tecladoController.anexarTeclado(Teclado.Tipo.INTEGER, depositoView.fieldNumeroConta);
            opcaoTipoDepositoEscolhido = EDepositoController.OUTRA_CONTA;
            depositoView.iniciarFormOutraConta(tecladoController);
        };
    }

    public EventHandler<ActionEvent> numeroContaAction() {
        return (event) ->
        {
            if (tecladoController.getTeclado().verificarValor()) {
                tecladoController.anexarTeclado(Teclado.Tipo.DOUBLE, null);
                depositoView.iniciarFormValor(tecladoController);
            }
        };
    }

    public EventHandler<ActionEvent> realizarDepositoAction() {
        return (event) ->
        {
            if (tecladoController.getTeclado().verificarValor()) {

                double valor = ((DoubleTeclado) tecladoController.getTeclado()).getValor();

                if (opcaoTipoDepositoEscolhido.equals(EDepositoController.CONTA_PROPRIA)) {
                    realizarDeposito(clienteController.getContaSessao(), valor);

                } else {
                    realizarDeposito(clienteController.getContaSessao(), depositoView.fieldNumeroConta.getText(), valor);
                }

            } else {
                MainView.getAlert("Validação",
                        "Informe o valor", Alert.AlertType.INFORMATION).show();
            }

        };
    }


    /*Realiza depósito em outra conta */
    private void realizarDeposito(Conta contaOrigem, String numeroConta, double valor) {
        try {

            MovimentoService movimentoService = MovimentoService.getInstance();
            ArrayList<MovimentoTO> movimentosRealizado;

            if (!((movimentosRealizado = ContaService.getInstance().realizarDeposito(contaOrigem, numeroConta, valor)).isEmpty())) {
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

            MovimentoService movimentoService = MovimentoService.getInstance();
            MovimentoTO movimento;

            if ((movimento = ContaService.getInstance().realizarDeposito(conta, valor)) != null) {
                movimentoService.registrarMovimento(movimento);
                depositoRealizado();
            }
        } catch (Exception e) {
            depositoNaoRealizado(e.getMessage());

        }
    }

    private void depositoRealizado() {
        MainView.getAlert("Depósito Informação",
                "Depósito realizado com sucesso!", Alert.AlertType.INFORMATION).show();
        retornarMenu().handle(null);
    }

    private void depositoNaoRealizado(String mensagem) {
        MainView.getAlert("Erro",
                mensagem, Alert.AlertType.INFORMATION).show();
        retornarMenu().handle(null);
    }


    public EventHandler<ActionEvent> retornarMenu() {
        return (event) -> clienteController.view.retornarMenuPrincipal(depositoView, clienteController.viewClient);
    }

}
