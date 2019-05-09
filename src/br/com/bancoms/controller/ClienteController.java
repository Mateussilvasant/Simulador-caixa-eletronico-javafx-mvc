package br.com.bancoms.controller;

import br.com.bancoms.model.Cliente;
import br.com.bancoms.view.ClienteView;
import br.com.bancoms.view.DepositoView;
import br.com.bancoms.view.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ClienteController
{

    public MainView view;
    public Cliente clienteSessao;
    public ClienteView viewClient;

    private TecladoController tecladoController;
    private DepositoView depositoView;

    public ClienteController(Cliente clienteSessao, MainView view)
    {
	this.view = view;
	this.clienteSessao = clienteSessao;
	this.viewClient = new ClienteView(this);
	this.tecladoController = new TecladoController();
    }

    public EventHandler<ActionEvent> getMenuDepositoAction()
    {
	return new EventHandler<ActionEvent>()
	{

	    @Override
	    public void handle(ActionEvent event)
	    {

		view.getChildren().remove(viewClient);

		if (depositoView == null)
		{
		    depositoView = new DepositoView(ClienteController.this);
		}

		depositoView.iniciarView(tecladoController, view);

	    }
	};
    }

    public EventHandler<ActionEvent> realizarDepositoAction()
    {
	return new EventHandler<ActionEvent>()
	{

	    @Override
	    public void handle(ActionEvent event)
	    {

	    }
	};
    }

}
