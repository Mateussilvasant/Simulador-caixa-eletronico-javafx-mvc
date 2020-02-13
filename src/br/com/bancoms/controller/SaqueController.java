package br.com.bancoms.controller;

import br.com.bancoms.components.dialogAlert.DialogAlert;
import br.com.bancoms.components.tecladoComponent.TecladoAdapter;
import br.com.bancoms.components.tecladoComponent.tipos.Teclado;
import br.com.bancoms.dto.MovimentoTO;
import br.com.bancoms.service.ContaService;
import br.com.bancoms.service.MovimentoService;
import br.com.bancoms.view.MainView;
import br.com.bancoms.view.SaqueView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SaqueController {

    public ClienteController clienteController;
    private TecladoAdapter tecladoAdapter;
    private SaqueView saqueView;

    public SaqueController(ClienteController clienteController) {
        this.clienteController = clienteController;
        saqueView = new SaqueView(this);
        tecladoAdapter = new TecladoAdapter();
    }

    public void iniciarSaque(MainView mainView) {
        tecladoAdapter.anexarTeclado(Teclado.TecladoEvent.DOUBLE, null);
        saqueView.initSaqueView(mainView, tecladoAdapter);
    }

    public EventHandler<ActionEvent> realizarSaqueAction() {
        return event -> {
            if (tecladoAdapter.getTeclado().verificarValor()) {
                realizarSaque(Double.parseDouble(tecladoAdapter.getTeclado().getTextoField()));
            } else {
                validacaoErro("Informe um valor.");
            }
        };
    }

    private void realizarSaque(double valor) {
        try {
            MovimentoService movimentoService = MovimentoService.getInstance();
            MovimentoTO movimentoTO;

            if ((movimentoTO = ContaService.getInstance().realizarSaque(clienteController.getContaSessao(), valor)) != null) {
                movimentoService.registrarMovimento(movimentoTO);
                saqueRealizado();
            }

        } catch (Exception e) {
            saqueNaoRealizado(e.getMessage());
        }
    }

    public EventHandler<ActionEvent> cancelarAction() {
        return (event) -> clienteController.retornarMenuAction(saqueView);
    }

    private void saqueNaoRealizado(String mensagem) {

        DialogAlert alert = clienteController.view.onAlertView("Saque Informação",
                mensagem, DialogAlert.AlertType.INFORMATION, true);
        alert.setEventInformation(e -> {
            clienteController.retornarMenuAction(saqueView);
            alert.fecharDialog();
        });
    }

    private void saqueRealizado() {
        clienteController.view.atualizarSaldoView(clienteController.getContaSessao().getSaldo());

        DialogAlert alert = clienteController.view.onAlertView("Saque Informação",
                "Saque realizado com sucesso!", DialogAlert.AlertType.INFORMATION, true);
        alert.setEventInformation(e -> {
            clienteController.retornarMenuAction(saqueView);
            alert.fecharDialog();
        });
    }

    private void validacaoErro(String mensagem) {
        DialogAlert alert = clienteController.view.onAlertView("Validação - Informação",
                mensagem, DialogAlert.AlertType.INFORMATION, false);
        alert.setEventInformation(e -> {
            alert.fecharDialog();
        });

    }

}
