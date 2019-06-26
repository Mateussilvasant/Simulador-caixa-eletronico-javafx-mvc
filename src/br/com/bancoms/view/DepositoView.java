package br.com.bancoms.view;

import br.com.bancoms.controller.ClienteController;
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

public class DepositoView extends VBox
{

    private Label labelDeposito;
    private Button botaoInserirValor;
    private Button botaoContaPropria;
    private Button botaoOutraConta;
    private Label labelDepositoTipo;
    private HBox opcoesConta;
    private Label labelNumerConta;
    private Button botaoNumeroConta;
    public TextField fieldNumeroConta;
    private HBox formNumeroConta;
    private HBox formValorDeposito;

    public DepositoView(ClienteController clienteController)
    {

	double paddingBox = CaixaView.METRICS.getPX(0.0050);

	setSpacing(CaixaView.METRICS.getPX(0.010));
	setAlignment(Pos.CENTER);
	setPadding(new Insets(paddingBox, paddingBox, paddingBox, paddingBox));
	getStyleClass().add("boxView");

	labelDeposito = new Label("Insira o valor a ser depositado:");
	labelDeposito.getStyleClass().add("labelStyle");
	labelDeposito.setFont(Font.font(CaixaView.METRICS.getPX(0.006)));

	botaoInserirValor = new Button("Próximo");
	botaoInserirValor.setOnAction(clienteController.realizarDepositoAction());
	botaoInserirValor.getStyleClass().add("buttonDark");

	labelDepositoTipo = new Label("Deseja realizar depósito em qual conta?");
	labelDepositoTipo.setFont(Font.font(CaixaView.METRICS.getPX(0.006)));
	labelDepositoTipo.getStyleClass().add("labelStyleDark");

	botaoContaPropria = new Button("Conta Própria");
	botaoContaPropria.getStyleClass().add("buttonStyle");
	botaoContaPropria.setOnAction(clienteController.contaPropriaAction());

	botaoOutraConta = new Button("Outra Conta");
	botaoOutraConta.getStyleClass().add("buttonStyle");
	botaoOutraConta.setOnAction(clienteController.outraContaAction());

	labelNumerConta = new Label("Informe número da Conta beneficiada: ");
	labelNumerConta.getStyleClass().add("labelStyle");
	labelNumerConta.setFont(Font.font(CaixaView.METRICS.getPX(0.006)));

	fieldNumeroConta = new TextField();
	fieldNumeroConta.setEditable(false);
	fieldNumeroConta.getStyleClass().add("numeroField");

	botaoNumeroConta = new Button("Próximo");
	botaoNumeroConta.setOnAction(clienteController.numeroContaAction());
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

    }

    public void iniciarFormTipoDeposito(TecladoController tecladoController, MainView view)
    {
	getChildren().add(labelDepositoTipo);
	getChildren().add(opcoesConta);
	view.getChildren().add(new Group(this));
    }

    public void iniciarFormOutraConta(TecladoController tecladoController)
    {
	getChildren().clear();
	getChildren().add(formNumeroConta);
	getChildren().add(tecladoController.tecladoView);
	getChildren().add(botaoNumeroConta);
    }

    public void iniciarFormValor(TecladoController tecladoController)
    {
	getChildren().clear();
	getChildren().add(formValorDeposito);
	getChildren().add(tecladoController.tecladoView);
	getChildren().add(botaoInserirValor);
    }

}
