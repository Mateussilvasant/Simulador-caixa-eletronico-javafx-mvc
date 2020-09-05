package br.com.bancoms.controller;

import java.util.Optional;

import br.com.bancoms.components.dialogAlert.DialogAlert;
import br.com.bancoms.dto.LoginDTO;
import br.com.bancoms.model.Cliente;
import br.com.bancoms.model.contas.Conta;
import br.com.bancoms.service.ClienteService;
import br.com.bancoms.service.ContaService;
import br.com.bancoms.view.ClienteViewFactory;
import br.com.bancoms.view.LoginView;
import br.com.bancoms.view.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LoginController implements EventHandler<ActionEvent> {

    private ContaService contaService = ContaService.getInstance();
    private ClienteService clienteService = ClienteService.getInstance();

    private MainView view;
    private LoginView loginView;

    public LoginController(MainView view, LoginView loginView) {
        this.view = view;
        this.loginView = loginView;
    }

    @Override
    public void handle(ActionEvent event) {
        String numero = loginView.fieldNumeroConta.getText();
        String senha = loginView.fieldSenha.getText();

        if (!numero.isEmpty() && !senha.isEmpty()) {

            try {

                Integer numeroConta = Integer.parseInt(numero);
                realizarLogin(new LoginDTO(numeroConta, senha));

            } catch (NumberFormatException ex) {

                DialogAlert alert = view.onAlertView("Login - Informação", "Senha inválida deve ser númerica...",
                        DialogAlert.AlertType.INFORMATION, true);
                alert.setEventInformation(e -> alert.fecharDialog());

            }

        } else {
            DialogAlert alert = view.onAlertView("Login - Informação", "Existem campos vazios....",
                    DialogAlert.AlertType.INFORMATION, true);
            alert.setEventInformation(e -> alert.fecharDialog());
        }

    }

    private void realizarLogin(LoginDTO loginDTO) {

        Optional<Cliente> clienteOpt = clienteService.realizarLogin(loginDTO);
        Optional<Conta> contaOpt = contaService.consultarConta(loginDTO.getNumeroConta());

        if (clienteOpt.isPresent() && contaOpt.isPresent()) {
            removerLoginView();
            iniciarClienteView(clienteOpt.get(), contaOpt.get());
        } else {

            DialogAlert alert = view.onAlertView("Login - Informação",
                    "Não foi possível realizar o Login, por favor verifique o número da conta ou senha.",
                    DialogAlert.AlertType.INFORMATION, true);
            alert.setEventInformation(e -> alert.fecharDialog());

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
