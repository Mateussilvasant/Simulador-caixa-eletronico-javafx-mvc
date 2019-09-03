package br.com.bancoms.controller;

import br.com.bancoms.model.Cliente;
import br.com.bancoms.model.Conta;
import br.com.bancoms.service.ClienteService;
import br.com.bancoms.service.ContaService;
import br.com.bancoms.util.Validador;
import br.com.bancoms.view.LoginView;
import br.com.bancoms.view.MainView;
import br.com.bancoms.vo.ClienteVO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;


public class LoginController implements EventHandler<ActionEvent> {

    private MainView view;
    private LoginView loginView;

    public LoginController(MainView view, LoginView loginView) {
        this.view = view;
        this.loginView = loginView;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handle(ActionEvent event) {
        String numero = "123456"; // loginView.fieldNumeroConta.getText();
        String senha = "123a"; // loginView.fieldSenha.getText();

        Validador.Valor<Integer> valor = Validador.validar(numero);

        if (valor.resposta == Validador.CAMPO_VALIDO
                && Validador.validarCampoTexto(senha) == Validador.CAMPO_VALIDO) {
            realizarLogin(new ClienteVO(valor.valor, senha));
        } else {
            view.labelTituloBar.setText("Banco M&S - Erro de Login: Senha ou número da Conta inválidos");
        }

    }

    private void realizarLogin(ClienteVO clienteVO) {

        Cliente cliente = ClienteService.getInstance().realizarLogin(clienteVO);
        Conta conta = ContaService.getInstance().consultarConta(clienteVO.getNumeroConta());

        if (cliente != null && conta != null) {
            removerLoginView();
            iniciarClienteView(cliente, conta);
        } else {
            MainView.getAlert("Banco MS - Login Informação",
                    "Não foi possível realizar o Login, por favor verifique o número da conta ou senha.",
                    Alert.AlertType.INFORMATION).showAndWait();
        }
    }

    private void iniciarClienteView(Cliente clienteSessao, Conta contaSessao) {
        ClienteController controller = new ClienteController(clienteSessao, contaSessao, view);

        if (contaSessao.getTipo() == Conta.CORRENTE) {
            controller.viewClient.iniciarCorrenteView(controller);
        }
        if (contaSessao.getTipo() == Conta.POUPANCA) {
            controller.viewClient.iniciarPoupancaView(controller);
        }
        if (contaSessao.getTipo() == Conta.INVESTIMENTO) {
        }

    }

    private void removerLoginView() {
        view.getChildren().remove(loginView);
    }

}
