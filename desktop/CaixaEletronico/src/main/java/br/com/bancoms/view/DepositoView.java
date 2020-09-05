package br.com.bancoms.view;

import br.com.bancoms.components.tecladoComponent.TecladoAdapter;
import br.com.bancoms.controller.DepositoController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
    private StackPane formValorDeposito;

    public DepositoView(DepositoController controller) {

        double paddingBox = BancoMS.METRICS.getPX(0.0050);

        setAlignment(Pos.CENTER);
        setFillWidth(false);
        setSpacing(BancoMS.METRICS.getPX(0.005));
        setPadding(new Insets(paddingBox, paddingBox, paddingBox, paddingBox));
        getStyleClass().add("boxView");

        View.setSizeElemento(this, 0.60, 0.60);

        Label labelDeposito = new Label("Insira o valor a ser depositado:");
        labelDeposito.getStyleClass().add("labelStyleDark");
        labelDeposito.setFont(Font.font(BancoMS.METRICS.getPX(0.012)));

        botaoInserirValor = View.createButton("Depositar", "buttonPrimary", 0.090, 0.060, 0.0065);
        botaoInserirValor.setOnAction(controller.realizarDepositoAction());

        botaoCancelar = View.createButton("Cancelar", "buttonPrimary", 0.090, 0.060, 0.0065);
        botaoCancelar.setOnAction(controller.clienteController.cancelarAction(this));

        labelDepositoTipo = new Label("Deseja realizar depósito em qual conta?");
        labelDepositoTipo.setFont(Font.font(BancoMS.METRICS.getPX(0.010)));
        labelDepositoTipo.getStyleClass().add("labelStyleDark");

        Button botaoContaPropria = View.createButton("Conta Própria", "buttonPrimary", 0.090, 0.060, 0.0065);
        botaoContaPropria.setOnAction(controller.contaPropriaAction());

        Button botaoOutraConta = View.createButton("Outra Conta", "buttonPrimary", 0.090, 0.060, 0.0065);
        botaoOutraConta.setOnAction(controller.outraContaAction());

        Label labelNumerConta = new Label("Informe número da Conta beneficiada: ");
        labelNumerConta.getStyleClass().add("labelStyleDark");
        labelNumerConta.setFont(Font.font(BancoMS.METRICS.getPX(0.010)));

        fieldNumeroConta = new TextField();
        fieldNumeroConta.setEditable(false);
        fieldNumeroConta.setFont(Font.font(BancoMS.METRICS.getPX(0.010)));
        fieldNumeroConta.getStyleClass().add("fieldStyle");


        formNumeroConta = new HBox();
        formNumeroConta.setSpacing(BancoMS.METRICS.getPX(0.004));
        formNumeroConta.setAlignment(Pos.CENTER);
        View.setSizeElemento(formNumeroConta, 0.60, 0.20);


        formNumeroConta.getChildren().add(labelNumerConta);
        formNumeroConta.getChildren().add(fieldNumeroConta);

        formValorDeposito = new StackPane();
        View.setSizeElemento(formValorDeposito, 0.60, 0.10);

        formValorDeposito.getChildren().add(labelDeposito);

        opcoesConta = new HBox();

        opcoesConta.setAlignment(Pos.CENTER);
        opcoesConta.setSpacing(BancoMS.METRICS.getPX(0.004));
        opcoesConta.getStyleClass().add("boxViewSecundary");
        View.setSizeElemento(opcoesConta, 0.30, 0.15);

        opcoesConta.getChildren().add(botaoContaPropria);
        opcoesConta.getChildren().add(botaoOutraConta);

        double pBotoes = BancoMS.METRICS.getPX(0.0050);


        Button cancelar = View.createButton("Cancelar", "buttonPrimary", 0.090, 0.060, 0.0065);
        cancelar.setOnAction(controller.clienteController.cancelarAction(this));

        botaoNumeroConta = View.createButton("Avançar", "buttonPrimary", 0.090, 0.060, 0.0065);
        botaoNumeroConta.setOnAction(controller.numeroContaAction());

        botoesOutraConta = new HBox();
        botoesOutraConta.setSpacing(BancoMS.METRICS.getPX(0.005));
        botoesOutraConta.setAlignment(Pos.CENTER_RIGHT);
        botoesOutraConta.setPadding(new Insets(pBotoes, pBotoes, pBotoes, pBotoes));
        View.setSizeElemento(botoesOutraConta, 0.60, 0.18);
        botoesOutraConta.getStyleClass().add("boxViewSecundary");
        botoesOutraConta.getChildren().add(botaoNumeroConta);
        botoesOutraConta.getChildren().add(botaoCancelar);

        botoesInserirValor = new HBox();
        botoesInserirValor.setSpacing(BancoMS.METRICS.getPX(0.005));
        botoesInserirValor.setPadding(new Insets(pBotoes, pBotoes, pBotoes, pBotoes));
        botoesInserirValor.setAlignment(Pos.CENTER_RIGHT);
        View.setSizeElemento(botoesInserirValor, 0.60, 0.20);
        botoesInserirValor.getStyleClass().add("boxViewSecundary");
        botoesInserirValor.getChildren().add(botaoInserirValor);
        botoesInserirValor.getChildren().add(cancelar);

    }

    public void iniciarFormTipoDeposito(TecladoAdapter tecladoAdapter, MainView view) {
        getChildren().add(labelDepositoTipo);
        getChildren().add(opcoesConta);
        view.getChildren().add(this);
    }

    public void iniciarFormOutraConta(TecladoAdapter tecladoAdapter) {
        getChildren().clear();
        getChildren().add(formNumeroConta);
        getChildren().add(tecladoAdapter.tecladoView);
        getChildren().add(botoesOutraConta);
    }

    public void iniciarFormValor(TecladoAdapter tecladoAdapter) {
        getChildren().clear();
        getChildren().add(formValorDeposito);
        getChildren().add(tecladoAdapter.tecladoView);
        getChildren().add(botoesInserirValor);
    }

}
