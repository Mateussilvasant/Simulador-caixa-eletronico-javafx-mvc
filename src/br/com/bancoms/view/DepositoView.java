package br.com.bancoms.view;

import br.com.bancoms.controller.ClienteController;
import br.com.bancoms.controller.TecladoController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DepositoView extends VBox
{

    private Label labelDeposito;
    private HBox hboxDeposito;
    private Button botaoDepositar;

    public DepositoView(ClienteController clienteController)
    {
	this.setSpacing(CaixaView.METRICS.getPX(0.020));

	setAlignment(Pos.CENTER);

	Group grupo = new Group();

	labelDeposito = new Label("Insira um valor para depósito:");
	hboxDeposito = new HBox();
	hboxDeposito.getChildren().add(labelDeposito);

	botaoDepositar = new Button("Realizar Depósito");
	botaoDepositar.setOnAction(clienteController.realizarDepositoAction());

	grupo.getChildren().add(hboxDeposito);

	getChildren().add(grupo);
    }

    public void iniciarView(TecladoController tecladoController, MainView view)
    {
	getChildren().add(tecladoController.tecladoView);
	getChildren().add(botaoDepositar);
	view.getChildren().add(new Group(this));
    }

}
