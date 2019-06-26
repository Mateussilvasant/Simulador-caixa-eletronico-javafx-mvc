package br.com.bancoms.controller;

import br.com.bancoms.model.Cliente;
import br.com.bancoms.model.Conta;
import br.com.bancoms.service.ClienteService;
import br.com.bancoms.service.ContaService;
import br.com.bancoms.util.CampoValidador;
import br.com.bancoms.view.LoginView;
import br.com.bancoms.view.MainView;
import br.com.bancoms.vo.ClienteVO;
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
	String numero = "123456"; // loginView.fieldNumeroConta.getText();
	String senha = "123a"; // loginView.fieldSenha.getText();

	if (CampoValidador.validarCampoNumero(numero) == CampoValidador.CAMPO_VALIDO
		&& CampoValidador.validarCampoTexto(senha) == CampoValidador.CAMPO_VALIDO)
	{
	    realizarLogin(new ClienteVO(Integer.parseInt(numero), senha));
	} else
	{
	    view.labelTituloBar.setText("Banco M&S - Erro de Login: Senha ou número da Conta inválidos");
	}

    }

    private void removerLoginView()
    {
	view.getChildren().remove(loginView);
    }

    private void realizarLogin(ClienteVO clienteVO)
    {

	Cliente cliente = ClienteService.getInstance().realizarLogin(clienteVO);
	Conta conta = ContaService.getInstance().consultarConta(clienteVO);

	if (cliente != null && conta != null)
	{
	    removerLoginView();
	    iniciarClienteView(cliente, conta);
	} else
	{
	    view.getAlert("Banco MS - Login Informação",
		    "Não foi possível realizar o Login, por favor verifique o número da conta ou senha.",
		    AlertType.INFORMATION).showAndWait();
	}
    }

    private void iniciarClienteView(Cliente clienteSessao, Conta contaSessao)
    {
	ClienteController controller = new ClienteController(clienteSessao, contaSessao, view);

	if (contaSessao.getTipo() == Conta.CORRENTE)
	{
	    controller.viewClient.iniciarCorrenteView(controller);
	}
	if (contaSessao.getTipo() == Conta.POUPANCA)
	{
	    controller.viewClient.iniciarPoupancaView(controller);
	}
	if (contaSessao.getTipo() == Conta.INVESTIMENTO)
	{
	    controller.viewClient.iniciarInvestimentoView();
	}

    }

}
