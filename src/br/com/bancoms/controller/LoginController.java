package br.com.bancoms.controller;

import br.com.bancoms.components.dialogAlert.DialogAlert;
import br.com.bancoms.model.Cliente;
import br.com.bancoms.model.contas.Conta;
import br.com.bancoms.service.ClienteService;
import br.com.bancoms.service.ContaService;
import br.com.bancoms.util.Validador;
import br.com.bancoms.view.ClienteViewFactory;
import br.com.bancoms.view.LoginView;
import br.com.bancoms.view.MainView;
import br.com.bancoms.vo.ClienteVO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Optional;


public class LoginController implements EventHandler<ActionEvent> {

    private MainView view;
    private LoginView loginView;

    public LoginController(MainView view, LoginView loginView) {
        this.view = view;
        this.loginView = loginView;
    }

    @Override
    public void handle(ActionEvent event) {
        String numero = "123456"; // loginView.fieldNumeroConta.getText();
        String senha = "123a"; // loginView.fieldSenha.getText();

        Validador.Valor<Integer> valor = Validador.validar(numero);

        if (valor.resposta == Validador.CAMPO_VALIDO
                && Validador.validarCampoTexto(senha) == Validador.CAMPO_VALIDO) {
            realizarLogin(new ClienteVO(valor.valor, senha));
        } else {
            view.setTituloBarText("Banco M&S - Erro de Login: Senha ou número da Conta inválidos");
        }

    }

    private void realizarLogin(ClienteVO clienteVO) {

        Optional<Cliente> clienteOpt = ClienteService.getInstance().realizarLogin(clienteVO);
        Optional<Conta> contaOpt = ContaService.getInstance().consultarConta(clienteVO.getNumeroConta());

        if (clienteOpt.isPresent() && contaOpt.isPresent()) {
            removerLoginView();
            iniciarClienteView(clienteOpt.get(), contaOpt.get());
        } else {

            DialogAlert alert = view.onAlertView("Login - Informação",
                    "Não foi possível realizar o Login, por favor verifique o número da conta ou senha.", DialogAlert.AlertType.INFORMATION, true);
            alert.setEventInformation(e -> {
                alert.fecharDialog();
            });

        }
    }

    private void iniciarClienteView(Cliente clienteSessao, Conta contaSessao) {

        ClienteController controller = new ClienteController(clienteSessao, contaSessao, view);

        controller.view.addSubMenuCliente(contaSessao, clienteSessao.getNomeCompleto());

        ClienteViewFactory.values()[contaSessao.getTipo() - 1].iniciarView(controller);

    }

    private void removerLoginView() {
        view.getChildren().remove(loginView);
    }

}
