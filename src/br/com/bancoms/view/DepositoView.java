package br.com.bancoms.view;

import br.com.bancoms.controller.DepositoController;
import br.com.bancoms.controller.TecladoController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class DepositoView extends VBox {

    private HBox botoesInserirValor;
    private HBox botoesOutraConta;
    private Button botaoInserirValor;
    private Button botaoNumeroConta;
    private Button botaoCancelar;
    private Label labelDepositoTipo;
    public TextField fieldNumeroConta;
    private HBox opcoesConta;
    private HBox formNumeroConta;
    private HBox formValorDeposito;

    public DepositoView(DepositoController controller) {

        double paddingBox = CaixaView.METRICS.getPX(0.0050);

        setSpacing(CaixaView.METRICS.getPX(0.010));
        setAlignment(Pos.CENTER);
        setPadding(new Insets(paddingBox, paddingBox, paddingBox, paddingBox));
        getStyleClass().add("boxView");

        Label labelDeposito = new Label("Insira o valor a ser depositado:");
        labelDeposito.getStyleClass().add("labelStyle");
        labelDeposito.setFont(Font.font(CaixaView.METRICS.getPX(0.006)));

        botaoInserirValor = new Button("Avançar");
        botaoInserirValor.setOnAction(controller.realizarDepositoAction());
        botaoInserirValor.getStyleClass().add("buttonDark");

        botaoCancelar = new Button("Cancelar");
        botaoCancelar.setOnAction(controller.retornarMenu());
        botaoCancelar.getStyleClass().add("buttonDark");

        labelDepositoTipo = new Label("Deseja realizar depósito em qual conta?");
        labelDepositoTipo.setFont(Font.font(CaixaView.METRICS.getPX(0.006)));
        labelDepositoTipo.getStyleClass().add("labelStyleDark");

        Button botaoContaPropria = new Button("Conta Própria");
        botaoContaPropria.getStyleClass().add("buttonStyle");
        botaoContaPropria.setOnAction(controller.contaPropriaAction());

        Button botaoOutraConta = new Button("Outra Conta");
        botaoOutraConta.getStyleClass().add("buttonStyle");
        botaoOutraConta.setOnAction(controller.outraContaAction());

        Label labelNumerConta = new Label("Informe número da Conta beneficiada: ");
        labelNumerConta.getStyleClass().add("labelStyle");
        labelNumerConta.setFont(Font.font(CaixaView.METRICS.getPX(0.006)));

        fieldNumeroConta = new TextField();
        fieldNumeroConta.setEditable(false);
        fieldNumeroConta.getStyleClass().add("numeroField");

        botaoNumeroConta = new Button("Avançar");
        botaoNumeroConta.setOnAction(controller.numeroContaAction());
        botaoNumeroConta.getStyleClass().add("buttonDark");

        formNumeroConta = new HBox();
        formNumeroConta.setSpacing(CaixaView.METRICS.getPX(0.004));
        formNumeroConta.getStyleClass().add("boxViewDark");
        formNumeroConta.setAlignment(Pos.CENTER);
        formNumeroConta.getChildren().add(labelNumerConta);
        formNumeroConta.getChildren().add(fieldNumeroConta);

        formValorDeposito = new HBox();
        formValorDeposito.setSpacing(CaixaView.METRICS.getPX(0.004));
        formValorDeposito.getStyleClass().add("boxViewDark");
        formValorDeposito.setAlignment(Pos.CENTER);
        formValorDeposito.getChildren().add(labelDeposito);

        opcoesConta = new HBox();
        opcoesConta.setSpacing(CaixaView.METRICS.getPX(0.004));
        opcoesConta.getStyleClass().add("boxViewDark");
        opcoesConta.setAlignment(Pos.CENTER);
        opcoesConta.getChildren().add(botaoContaPropria);
        opcoesConta.getChildren().add(botaoOutraConta);

        botoesOutraConta = new HBox();
        botoesOutraConta.setSpacing(CaixaView.METRICS.getPX(0.005));
        botoesOutraConta.setAlignment(Pos.CENTER);
        botoesOutraConta.getChildren().add(botaoNumeroConta);
        botoesOutraConta.getChildren().add(botaoCancelar);

        botoesInserirValor = new HBox();
        botoesInserirValor.setSpacing(CaixaView.METRICS.getPX(0.005));
        botoesInserirValor.setAlignment(Pos.CENTER);
        botoesInserirValor.getChildren().add(botaoInserirValor);
        botoesInserirValor.getChildren().add(botaoCancelar);
    }

    public void iniciarFormTipoDeposito(TecladoController tecladoController, MainView view) {
        getChildren().add(labelDepositoTipo);
        getChildren().add(opcoesConta);
        view.getChildren().add(new Group(this));
    }

    public void iniciarFormOutraConta(TecladoController tecladoController) {
        getChildren().clear();
        getChildren().add(formNumeroConta);
        getChildren().add(tecladoController.tecladoView);
        getChildren().add(botoesOutraConta);
    }

    public void iniciarFormValor(TecladoController tecladoController) {
        getChildren().clear();
        getChildren().add(formValorDeposito);
        getChildren().add(tecladoController.tecladoView);
        getChildren().add(botoesInserirValor);
    }

}
