package br.com.bancoms.controller;

import br.com.bancoms.model.Cliente;
import br.com.bancoms.model.Conta;
import br.com.bancoms.service.ClienteService;
import br.com.bancoms.util.CampoValidador;
import br.com.bancoms.view.LoginView;
import br.com.bancoms.view.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;

public class LoginController implements EventHandler<ActionEvent>
{

    public MainView view;
    public LoginView loginView;

    public LoginController(MainView view, LoginView loginView)
    {
	this.view = view;
	this.loginView = loginView;
    }

    @Override
    public void handle(ActionEvent event)
    {
	String numero = "123456" ; //loginView.fieldNumeroConta.getText();
	String senha = "123a" ; //loginView.fieldSenha.getText();

	if (CampoValidador.validarCampoNumero(numero) == CampoValidador.CAMPO_VALIDO
		&& CampoValidador.validarCampoTexto(senha) == CampoValidador.CAMPO_VALIDO)
	{
	    int numeroConta = Integer.parseInt(numero);
	    Cliente clienteSessao = null;

	    if ((clienteSessao = realizarLogin(numeroConta, senha)) != null)
	    {
		removerLoginView();
		adicionarClienteView(clienteSessao);
	    } else
	    {
		view.labelTituloBar.setText("Banco M&S - Erro de Login: Senha ou número da Conta inválidos");
	    }

	} else
	{
	    view.getAlert("Banco MS - Login Informação",
		    "Não foi possível realizar o Login, por favor verifique o número da conta ou senha.",
		    AlertType.INFORMATION).showAndWait();
	}

    }

    private void removerLoginView()
    {
	view.getChildren().remove(loginView);
    }

    private Cliente realizarLogin(int numeroConta, String senha)
    {
	Cliente clienteSessao = ClienteService.getInstance().realizarLogin(numeroConta, senha);

	if (clienteSessao != null)
	{
	    return clienteSessao;
	}
	return null;
    }

    private void adicionarClienteView(Cliente clienteSessao)
    {
	ClienteController controller = new ClienteController(clienteSessao, view);

	if (clienteSessao.getConta().getTipo() == Conta.CORRENTE)
	{
	    controller.viewClient.iniciarCorrenteView(controller);
	}
	if (clienteSessao.getConta().getTipo() == Conta.POUPANCA)
	{
	    controller.viewClient.iniciarPoupancaView(controller);
	}
	if (clienteSessao.getConta().getTipo() == Conta.INVESTIMENTO)
	{
	    controller.viewClient.iniciarInvestimentoView();
	}

    }

}
