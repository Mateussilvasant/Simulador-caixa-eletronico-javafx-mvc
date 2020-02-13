package br.com.bancoms.controller;

import br.com.bancoms.model.Cliente;
import br.com.bancoms.model.Conta;
import br.com.bancoms.view.ClienteView;
import br.com.bancoms.view.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;


public class ClienteController {

    public MainView view;
    public ClienteView viewClient;

    private TransferenciaController transferenciaController;
    private DepositoController depositoController;
    private SaqueController saqueController;

    public Cliente clienteSessao;
    private Conta contaSessao;

    public ClienteController(Cliente cliente, Conta contaSessao, MainView view) {
        this.view = view;
        this.clienteSessao = cliente;
        this.contaSessao = contaSessao;
        this.viewClient = new ClienteView(this);
        this.depositoController = new DepositoController(this);
        this.saqueController = new SaqueController(this);
        this.transferenciaController = new TransferenciaController(this);
    }

    public EventHandler<ActionEvent> menuDepositoAction() {
        return (event) ->
        {
            view.getChildren().remove(viewClient);
            depositoController.iniciarDeposito(view);
        };
    }

    public EventHandler<ActionEvent> menuTransferenciaAction() {
        return (event) -> {
            view.getChildren().remove(viewClient);
            transferenciaController.iniciarTransferencia();
        };
    }

    public EventHandler<ActionEvent> menuSaqueAction() {
        return event -> {
            view.getChildren().remove(viewClient);
            saqueController.iniciarSaque(view);
        };
    }

    public EventHandler<ActionEvent> cancelarAction(Pane pane) {
        return (event) -> {
            retornarMenuAction(pane);
        };
    }

    public void retornarMenuAction(Pane viewAtual) {
        view.transitarMenu(viewAtual, viewClient);
    }

    public Conta getContaSessao() {
        return contaSessao;
    }

}
