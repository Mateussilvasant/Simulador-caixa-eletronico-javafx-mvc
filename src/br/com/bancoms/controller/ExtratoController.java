package br.com.bancoms.controller;

import br.com.bancoms.view.ExtratoView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ExtratoController {

    public ClienteController clienteController;
    private ExtratoView extratoView;

    public ExtratoController(ClienteController clienteController) {
        this.clienteController = clienteController;
        extratoView = new ExtratoView(this);
    }

    public void iniciarExtratos() {
        extratoView.iniciarView(clienteController);
    }


    public EventHandler<ActionEvent> cancelarAction() {
        return (event) -> clienteController.retornarMenuAction(extratoView);
    }


}
