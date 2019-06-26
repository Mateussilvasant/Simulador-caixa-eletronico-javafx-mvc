package br.com.bancoms.controller;

import br.com.bancoms.dto.MovimentoTO;
import br.com.bancoms.model.Cliente;
import br.com.bancoms.model.Conta;
import br.com.bancoms.model.Movimento;
import br.com.bancoms.service.ContaService;
import br.com.bancoms.service.MovimentoService;
import br.com.bancoms.view.ClienteView;
import br.com.bancoms.view.DepositoView;
import br.com.bancoms.view.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;

public class ClienteController
{

    public MainView view;
    public ClienteView viewClient;
    private DepositoView depositoView;

    public Cliente clienteSessao;
    private Conta contaSessao;

    private TecladoController tecladoController;

    public ClienteController(Cliente cliente, Conta contaSessao, MainView view)
    {
	this.view = view;
	this.clienteSessao = cliente;
	this.contaSessao = contaSessao;
	this.tecladoController = new TecladoController(10, TecladoController.Tipo.INTEGER);
	this.viewClient = new ClienteView(this);
    }

    public EventHandler<ActionEvent> menuDepositoAction()
    {
	return (event) ->
	{

	    view.getChildren().remove(viewClient);

	    if (depositoView == null)
	    {
		depositoView = new DepositoView(ClienteController.this);
	    }

	    depositoView.iniciarFormTipoDeposito(tecladoController, view);

	};
    }

    public EventHandler<ActionEvent> contaPropriaAction()
    {
	return (event) ->
	{
	    depositoView.iniciarFormValor(tecladoController);
	    tecladoController.mudarParaDouble();
	};
    }

    public EventHandler<ActionEvent> outraContaAction()
    {
	return (event) ->
	{
	    tecladoController.addFieldExterno(depositoView.fieldNumeroConta);
	    depositoView.iniciarFormOutraConta(tecladoController);
	};
    }

    public EventHandler<ActionEvent> numeroContaAction()
    {
	return (event) ->
	{
	    if (tecladoController.verificarConfirmacao())
	    {
		tecladoController.addFieldInterno();
		tecladoController.mudarParaDouble();
		depositoView.iniciarFormValor(tecladoController);
	    }
	};
    }

    public EventHandler<ActionEvent> realizarDepositoAction()
    {
	return (event) ->
	{
	    if (tecladoController.verificarConfirmacao())
	    {
		if (realizarDeposito((double) tecladoController.getValorOperacao()))
		{
		    view.getAlert("DEPÓSITO REALIZADO", "Depósito realizado com sucesso!", AlertType.INFORMATION)
			    .show();
		} else
		{
		    view.getAlert("DEPÓSITO NÃO REALIZADO", "Ocorreu um erro, não foi possível realizar o depósito!",
			    AlertType.INFORMATION).show();
		}
	    }
	};
    }

    private boolean realizarDeposito(double valor)
    {
	ContaService contaService = ContaService.getInstance();
	MovimentoService movimentoService = MovimentoService.getInstance();

	// Adiciona o valor no Model
	contaService.depositar(contaSessao, valor);

	// Atualizar o valor no Banco de Dados
	if (contaService.atualizarSaldo(contaSessao))
	{
	    MovimentoTO movimentoTO = new MovimentoTO(valor, Movimento.DEPOSITO, "Depósito conta-própria", contaSessao);

	    // registrar o movimento
	    if (movimentoService.registrarMovimento(movimentoTO) > 0)
	    {
		return true;
	    }

	} else
	{
	    // Remove o valor no Model
	    contaService.sacar(contaSessao, valor);
	}

	return false;
    }

}
