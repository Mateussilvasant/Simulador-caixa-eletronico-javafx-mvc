package br.com.bancoms.controller;

import br.com.bancoms.model.Cliente;
import br.com.bancoms.model.Conta;
import br.com.bancoms.view.ClienteView;
import br.com.bancoms.view.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class ClienteController {

    public MainView view;
    public ClienteView viewClient;
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
    }

    public EventHandler<ActionEvent> menuDepositoAction() {
        return (event) ->
        {
            view.getChildren().remove(viewClient);
            depositoController.iniciarDeposito(view);
        };
    }

    public Conta getContaSessao() {
        return contaSessao;
    }

    public EventHandler<ActionEvent> menuSaqueAction() {
        return event ->{
            view.getChildren().remove(viewClient);
            saqueController.iniciarSaque(view);
        };
    }
}
