package br.com.bancoms.view;

import br.com.bancoms.controller.ClienteController;
import br.com.bancoms.model.Cliente;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
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
	hboxView.setBorder(new Border(
		new BorderStroke(Color.SLATEGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	hboxView.setAlignment(Pos.CENTER);

	grupo.getChildren().add(hboxView);
	getChildren().add(grupo);

	controller.view.getChildren().add(this);
    }

    public void iniciarCorrenteView(ClienteController controller)
    {
	botaoDepositar = new Button("Depósito");
	botaoDepositar.setOnAction(controller.getMenuDepositoAction());

	botaoSacar = new Button("Saque");
	botaoTransferencia = new Button("Transferência");
	botaoEmprestimo = new Button("Empréstimo");
	botaoExtrato = new Button("Extratos");
	botaoEncerrar = new Button("Encerrar");

	hboxView.getChildren().addAll(botaoDepositar, botaoSacar, botaoTransferencia, botaoExtrato, botaoEncerrar);

    }

    public void iniciarPoupancaView(ClienteController controller)
    {
	botaoDepositar = new Button("Depósito");
	botaoDepositar.setOnAction(controller.getMenuDepositoAction());
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
