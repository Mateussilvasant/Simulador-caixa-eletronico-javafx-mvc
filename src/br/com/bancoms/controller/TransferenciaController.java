package br.com.bancoms.controller;

import br.com.bancoms.components.dialogAlert.DialogAlert;
import br.com.bancoms.components.tecladoComponent.TecladoAdapter;
import br.com.bancoms.components.tecladoComponent.tipos.Teclado;
import br.com.bancoms.dto.TransacaoDTO;
import br.com.bancoms.model.Movimento;
import br.com.bancoms.service.ContaService;
import br.com.bancoms.service.MovimentoService;
import br.com.bancoms.view.TransferenciaView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class TransferenciaController {

    public ClienteController clienteController;
    private TransferenciaView transferenciaView;
    private TecladoAdapter tecladoAdapter;

    private int numeroConta;

    public TransferenciaController(ClienteController clienteController) {
        this.clienteController = clienteController;
        transferenciaView = new TransferenciaView(this);
        tecladoAdapter = new TecladoAdapter();
    }

    public void iniciarTransferencia() {
        tecladoAdapter.anexarTeclado(Teclado.TecladoEvent.INTEGER, transferenciaView.fieldNumeroConta);
        transferenciaView.iniciarView(clienteController, tecladoAdapter);
    }

    public EventHandler<ActionEvent> actionNumeroConta() {
        return (event) -> {
            if (tecladoAdapter.getTeclado().verificarValor()) {

                numeroConta = Integer.parseInt(tecladoAdapter.getTeclado().getTextoField());

                tecladoAdapter.anexarTeclado(Teclado.TecladoEvent.DOUBLE, null);
                transferenciaView.iniciarFormInserirValor(tecladoAdapter);

            } else {
                validacaoErro("Informe o número da conta.");
            }
        };
    }

    public EventHandler<ActionEvent> actionTransferir() {
        return (event) -> {

            if (tecladoAdapter.getTeclado().verificarValor()) {

                double valorOperacao = Double.parseDouble(tecladoAdapter.getTeclado().getTextoField());

                realizarTransferencia(numeroConta, valorOperacao);

            } else {
                validacaoErro("Informe um valor.");
            }

        };
    }

    private void realizarTransferencia(int numeroContaDestino, double valor) {

        try {

            TransacaoDTO transacaoDTO = new TransacaoDTO(clienteController.getContaSessao(), numeroContaDestino, valor);
            ContaService contaService = ContaService.getInstance();
            MovimentoService movimentoService = MovimentoService.getInstance();

            ArrayList<Movimento> movimentos;

            if (!((movimentos = contaService.realizarTransferencia(transacaoDTO)).isEmpty())) {
                movimentoService.registrarMovimentos(movimentos);
                transferenciaRealizada();
            }

        } catch (Exception e) {
            depositoNaoRealizado(e.getMessage());
        }


    }


    private void validacaoErro(String mensagem) {
        DialogAlert alert = clienteController.view.onAlertView("Validação - Informação",
                mensagem, DialogAlert.AlertType.INFORMATION, false);
        alert.setEventInformation(e -> alert.fecharDialog());

    }

    private void transferenciaRealizada() {
        clienteController.view.atualizarSaldoView(clienteController.getContaSessao().getSaldo());

        DialogAlert alert = clienteController.view.onAlertView("Transferência - Informação",
                "Transferência realizada com sucesso!", DialogAlert.AlertType.INFORMATION, true);
        alert.setEventInformation(e -> {
            clienteController.retornarMenuAction(transferenciaView);
            alert.fecharDialog();
        });

    }

    private void depositoNaoRealizado(String mensagem) {
        DialogAlert alert = clienteController.view.onAlertView("Transferência - Informação",
                mensagem, DialogAlert.AlertType.INFORMATION, true);
        alert.setEventInformation(e -> {
            clienteController.retornarMenuAction(transferenciaView);
            alert.fecharDialog();
        });
    }


}
