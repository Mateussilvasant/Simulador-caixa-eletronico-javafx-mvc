package br.com.bancoms.view;

import br.com.bancoms.components.tecladoComponent.TecladoAdapter;
import br.com.bancoms.controller.ClienteController;
import br.com.bancoms.controller.TransferenciaController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TransferenciaView extends VBox {

    public TextField fieldNumeroConta;
    private HBox boxValorOperacao;
    private HBox boxBotoesNumeroConta;
    private Button botaoTransferir;
    private HBox formNumeroConta;
    private HBox boxTitle;
    private Button botaoNumeroConta;
    private Label labelTitle;

    public TransferenciaView(TransferenciaController transferenciaController) {

        double paddingBox = BancoMS.METRICS.getPX(0.0050);

        setSpacing(BancoMS.METRICS.getPX(0.005));
        setAlignment(Pos.CENTER);
        setFillWidth(false);
        setPadding(new Insets(paddingBox, paddingBox, paddingBox, paddingBox));
        getStyleClass().add("boxView");

        View.setSizeElemento(this, 0.60, 0.60);

        Label labelNumerConta = new Label("Informe o valor a ser transferido:");
        labelNumerConta.getStyleClass().add("labelStyleDark");
        labelNumerConta.setFont(Font.font(BancoMS.METRICS.getPX(0.010)));

        formNumeroConta = new HBox();
        formNumeroConta.setSpacing(BancoMS.METRICS.getPX(0.004));
        formNumeroConta.setAlignment(Pos.CENTER);
        View.setSizeElemento(formNumeroConta, 0.60, 0.10);

        formNumeroConta.getChildren().add(labelNumerConta);

        fieldNumeroConta = new TextField();
        fieldNumeroConta.setEditable(false);
        fieldNumeroConta.setFont(Font.font(BancoMS.METRICS.getPX(0.010)));
        fieldNumeroConta.getStyleClass().add("fieldStyle");

        labelTitle = new Label("Informe número da Conta beneficiada: ");
        labelTitle.getStyleClass().add("labelStyleDark");
        labelTitle.setFont(Font.font(BancoMS.METRICS.getPX(0.010)));

        boxTitle = new HBox();
        boxTitle.setSpacing(BancoMS.METRICS.getPX(0.004));
        boxTitle.setAlignment(Pos.CENTER);
        boxTitle.getChildren().add(labelTitle);
        boxTitle.getChildren().add(fieldNumeroConta);

        botaoNumeroConta = View.createButton("Avançar", "buttonPrimary", 0.090, 0.060, 0.0065);
        botaoNumeroConta.setOnAction(transferenciaController.actionNumeroConta());

        botaoTransferir = View.createButton("Transferir", "buttonPrimary", 0.090, 0.060, 0.0065);
        botaoTransferir.setOnAction(transferenciaController.actionTransferir());

        Button botaoCancelar = View.createButton("Cancelar", "buttonPrimary", 0.090, 0.060, 0.0065);
        botaoCancelar.setOnAction(transferenciaController.clienteController.cancelarAction(this));

        Button cancelarBotao = View.createButton("Cancelar", "buttonPrimary", 0.090, 0.060, 0.0065);
        cancelarBotao.setOnAction(transferenciaController.clienteController.cancelarAction(this));

        double pBotoes = BancoMS.METRICS.getPX(0.0050);

        boxBotoesNumeroConta = new HBox();
        boxBotoesNumeroConta.setSpacing(BancoMS.METRICS.getPX(0.005));
        boxBotoesNumeroConta.setAlignment(Pos.CENTER_RIGHT);
        boxBotoesNumeroConta.setPadding(new Insets(pBotoes, pBotoes, pBotoes, pBotoes));
        View.setSizeElemento(boxBotoesNumeroConta, 0.60, 0.18);
        boxBotoesNumeroConta.getStyleClass().add("boxViewSecundary");
        boxBotoesNumeroConta.getChildren().add(botaoNumeroConta);
        boxBotoesNumeroConta.getChildren().add(botaoCancelar);

        boxValorOperacao = new HBox();
        boxValorOperacao.setSpacing(BancoMS.METRICS.getPX(0.005));
        boxValorOperacao.setAlignment(Pos.CENTER_RIGHT);
        boxValorOperacao.setPadding(new Insets(pBotoes, pBotoes, pBotoes, pBotoes));
        View.setSizeElemento(boxValorOperacao, 0.60, 0.18);
        boxValorOperacao.getStyleClass().add("boxViewSecundary");
        boxValorOperacao.getChildren().add(botaoTransferir);
        boxValorOperacao.getChildren().add(cancelarBotao);

    }

    public void iniciarView(ClienteController clienteController, TecladoAdapter tecladoAdapter) {
        getChildren().add(boxTitle);
        getChildren().add(tecladoAdapter.tecladoView);
        getChildren().add(boxBotoesNumeroConta);
        clienteController.view.getChildren().add(this);
    }

    public void iniciarFormInserirValor(TecladoAdapter tecladoAdapter) {
        getChildren().clear();
        getChildren().add(formNumeroConta);
        getChildren().add(tecladoAdapter.tecladoView);
        getChildren().add(boxValorOperacao);
    }
}
