package br.com.bancoms.view;

import br.com.bancoms.controller.ClienteController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class ClienteView extends StackPane {
    private HBox hboxView;
    private Button botaoDepositar;
    private Button botaoSacar;
    private Button botaoTransferencia;
    private Button botaoExtrato;

    public ClienteView(ClienteController controller) {

        setPickOnBounds(false);


        controller.view.setTituloBarText("Menu Principal");

        Group grupo = new Group();

        hboxView = new HBox();
        hboxView.getStyleClass().add("boxView");
        hboxView.setAlignment(Pos.CENTER);
        hboxView.setSpacing(BancoMS.METRICS.getPX(0.005));
        View.setSizeElemento(hboxView, 0.70, 0.70);

        grupo.getChildren().add(hboxView);
        getChildren().add(grupo);

        controller.view.getChildren().add(this);
    }

    public void iniciarCorrenteView(ClienteController controller) {

        botaoDepositar = View.createButton("buttonDark", 0.15, 0.15, "depositar.png");
        botaoDepositar.setOnAction(controller.menuDepositoAction());

        botaoSacar = View.createButton("buttonDark", 0.15, 0.15, "sacar.png");
        botaoSacar.setOnAction(controller.menuSaqueAction());

        botaoTransferencia = View.createButton("buttonDark", 0.15, 0.15, "transferencia.png");
        botaoTransferencia.setOnAction(controller.menuTransferenciaAction());

        botaoExtrato = View.createButton("buttonDark", 0.15, 0.15, "saldos.png");
        botaoExtrato.setOnAction(controller.menuExtratos());

        hboxView.getChildren().addAll(botaoDepositar, botaoSacar, botaoTransferencia, botaoExtrato);
    }

    public void iniciarPoupancaView(ClienteController controller) {
        botaoDepositar = View.createButton("buttonDark", 0.15, 0.15, "depositar.png");
        botaoDepositar.setOnAction(controller.menuDepositoAction());

        botaoSacar = View.createButton("buttonDark", 0.15, 0.15, "sacar.png");
        botaoSacar.setOnAction(controller.menuSaqueAction());

        botaoTransferencia = View.createButton("buttonDark", 0.15, 0.15, "transferencia.png");
        botaoTransferencia.setOnAction(controller.menuTransferenciaAction());

        botaoExtrato = View.createButton("buttonDark", 0.15, 0.15, "saldos.png");
        botaoExtrato.setOnAction(controller.menuExtratos());

        hboxView.getChildren().addAll(botaoDepositar, botaoSacar, botaoTransferencia, botaoExtrato);
    }

    public void iniciarInvestimento(ClienteController controller) {
        botaoDepositar = View.createButton("buttonDark", 0.15, 0.15, "depositar.png");
        botaoDepositar.setOnAction(controller.menuDepositoAction());

        botaoTransferencia = View.createButton("buttonDark", 0.15, 0.15, "transferencia.png");
        botaoTransferencia.setOnAction(controller.menuTransferenciaAction());

        botaoExtrato = View.createButton("buttonDark", 0.15, 0.15, "saldos.png");
        botaoExtrato.setOnAction(controller.menuExtratos());

        hboxView.getChildren().addAll(botaoDepositar, botaoTransferencia, botaoExtrato);
    }
}
