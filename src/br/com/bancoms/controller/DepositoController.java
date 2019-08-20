package br.com.bancoms.controller;

import br.com.bancoms.components.DoubleTeclado;
import br.com.bancoms.components.Teclado;
import br.com.bancoms.dto.MovimentoTO;
import br.com.bancoms.model.Conta;
import br.com.bancoms.model.Movimento;
import br.com.bancoms.service.ContaService;
import br.com.bancoms.service.MovimentoService;
import br.com.bancoms.util.Validador;
import br.com.bancoms.view.DepositoView;
import br.com.bancoms.view.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;


public class DepositoController {
    private DepositoView depositoView;
    private TecladoController tecladoController;
    private ClienteController clienteController;

    public DepositoController(ClienteController clienteController) {
        this.tecladoController = new TecladoController();
        this.depositoView = new DepositoView(this);
        this.clienteController = clienteController;
    }

    public void iniciarDeposito(MainView view) {
        depositoView.iniciarFormTipoDeposito(tecladoController, view);
    }

    public EventHandler<ActionEvent> contaPropriaAction() {
        return (event) ->
        {
            tecladoController.anexarTeclado(Teclado.Tipo.DOUBLE, null);
            depositoView.iniciarFormValor(tecladoController);
        };
    }

    public EventHandler<ActionEvent> outraContaAction() {
        return (event) ->
        {
            tecladoController.anexarTeclado(Teclado.Tipo.INTEGER, depositoView.fieldNumeroConta);
            depositoView.iniciarFormOutraConta(tecladoController);
        };
    }

    public EventHandler<ActionEvent> numeroContaAction() {
        return (event) ->
        {
            if (tecladoController.getTeclado().verificarValor()) {
                tecladoController.anexarTeclado(Teclado.Tipo.DOUBLE, null);
                depositoView.iniciarFormValor(tecladoController);
            }
        };
    }

    @SuppressWarnings("unchecked")
    public EventHandler<ActionEvent> realizarDepositoAction() {
        return (event) ->
        {
            if (tecladoController.getTeclado().verificarValor()) {

                double valor = ((DoubleTeclado) tecladoController.getTeclado()).getValor();

                if (depositoView.getChildren().contains(depositoView.fieldNumeroConta)) {

                    Validador.Valor<Integer> valorResposta = Validador.validarCampoNumero(depositoView.fieldNumeroConta.getText());

                    if (valorResposta.resposta == Validador.CAMPO_VALIDO) {
                        realizarDeposito(valor, valorResposta.valor);
                    } else if (valorResposta.resposta == Validador.CAMPO_NUMERAL_COM_PALAVRAS) {
                        MainView.getAlert("Validação",
                                "Número da conta inválido", Alert.AlertType.INFORMATION).show();
                    }

                } else {
                    realizarDeposito(valor, 0);
                }

            } else {
                MainView.getAlert("Validação",
                        "Informe o valor", Alert.AlertType.INFORMATION).show();
            }

        };
    }

    private void realizarDeposito(double valor, int numeroContaBeneficiada) {
        ContaService contaService = ContaService.getInstance();
        Conta conta = clienteController.getContaSessao();
        Conta contaBeneficiada;
        boolean transacaoFeita = false;

        if (numeroContaBeneficiada == 0) {

            if (contaService.realizarDeposito(conta, valor)) {
                transacaoFeita = true;
                registrarMovimento("DEPÓSITO Conta-Própria", valor, conta);
            }

        } else {

            contaBeneficiada = contaService.consultarConta(numeroContaBeneficiada);

            if (contaService.realizarDeposito(conta, valor)) {
                transacaoFeita = true;

                registrarMovimento("DEPÓSITO Conta-Creditada: " + contaBeneficiada.getNumero(), valor,
                        contaBeneficiada);
                registrarMovimento("DEPÓSITO Conta-Beneficiada: " + conta.getNumero(),
                        valor, conta);
            }

        }

        if (transacaoFeita) {
            MainView.getAlert("DEPÓSITO REALIZADO", "Depósito realizado com sucesso!", Alert.AlertType.INFORMATION)
                    .show();
        } else {
            MainView.getAlert("DEPÓSITO NÃO REALIZADO",
                    "Ocorreu um erro, não foi possível realizar o depósito!", Alert.AlertType.INFORMATION).show();
        }

        clienteController.view.retornarMenuPrincipal(depositoView, clienteController.viewClient);
    }

    private boolean registrarMovimento(String titulo, double valor, Conta conta) {

        MovimentoTO movimentoTO = new MovimentoTO(valor, Movimento.DEPOSITO, titulo, conta);

        // registrar o movimento
        return MovimentoService.getInstance().registrarMovimento(movimentoTO) > 0;
    }

    public EventHandler<ActionEvent> cancelarOperacao() {
        return (event) ->{
            clienteController.view.retornarMenuPrincipal(depositoView, clienteController.viewClient);
        };
    }
}
