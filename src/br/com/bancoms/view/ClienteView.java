package br.com.bancoms.view;

import br.com.bancoms.controller.ClienteController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class ClienteView extends StackPane
{
    private HBox hboxView;
    public Button botaoDepositar;
    public Button botaoSacar;
    public Button botaoTransferencia;
    public Button botaoEmprestimo;
    public Button botaoExtrato;
    public Button botaoEncerrar;
    private Group grupo;

    public ClienteView(ClienteController controller)
    {
	controller.view.labelTituloBar.setText(
		"Sistema Banco MS - Menu Principal - Seja Bem Vindo " + controller.clienteSessao.getNomeCompleto());

	grupo = new Group();

	hboxView = new HBox();
	hboxView.setAlignment(Pos.CENTER);
	hboxView.setSpacing(CaixaView.METRICS.getPX(0.0008));

	grupo.getChildren().add(hboxView);
	getChildren().add(grupo);

	controller.view.getChildren().add(this);
    }

    public void iniciarCorrenteView(ClienteController controller)
    {
	botaoDepositar = new Button();
	botaoDepositar.getStyleClass().add("buttonBanco");
	botaoDepositar.setOnAction(controller.menuDepositoAction());
	botaoDepositar.setGraphic(new ImageView("depositar.png"));

	botaoSacar = new Button();
	botaoSacar.getStyleClass().add("buttonBanco");
	botaoSacar.setGraphic(new ImageView("sacar.png"));

	botaoTransferencia = new Button();
	botaoTransferencia.getStyleClass().add("buttonBanco");
	botaoTransferencia.setGraphic(new ImageView("transferencia.png"));

	botaoEmprestimo = new Button();
	botaoEmprestimo.getStyleClass().add("buttonBanco");
	botaoEmprestimo.setGraphic(new ImageView("emprestimo.png"));

	botaoExtrato = new Button();
	botaoExtrato.getStyleClass().add("buttonBanco");
	botaoExtrato.setGraphic(new ImageView("saldos.png"));

	hboxView.getChildren().addAll(botaoDepositar, botaoSacar, botaoTransferencia, botaoExtrato);
    }

    public void iniciarPoupancaView(ClienteController controller)
    {
	botaoDepositar = new Button("Depósito");
	botaoDepositar.setOnAction(controller.menuDepositoAction());
	botaoSacar = new Button("Saque");
	botaoTransferencia = new Button("Transferência");
	botaoExtrato = new Button("Extratos");
	botaoEncerrar = new Button("Encerrar");

	hboxView.getChildren().addAll(botaoDepositar, botaoSacar, botaoTransferencia, botaoExtrato, botaoEncerrar);
    }

    public void iniciarInvestimentoView()
    {

    }
}
