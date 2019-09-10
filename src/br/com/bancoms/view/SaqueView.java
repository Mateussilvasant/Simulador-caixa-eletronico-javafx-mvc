package br.com.bancoms.view;

import br.com.bancoms.controller.SaqueController;
import br.com.bancoms.controller.TecladoController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SaqueView extends VBox {

    private HBox boxSaqueTitle;
    private Button buttonSacar;
    private Button buttonCancelar;
    private Label labelTitle;
    private HBox buttons;

    public SaqueView(SaqueController controller) {

        double paddingBox = CaixaView.METRICS.getPX(0.0050);

        setSpacing(CaixaView.METRICS.getPX(0.010));
        setAlignment(Pos.CENTER);
        setPadding(new Insets(paddingBox, paddingBox, paddingBox, paddingBox));
        getStyleClass().add("boxView");

        buttons = new HBox();
        buttons.setSpacing(CaixaView.METRICS.getPX(0.005));
        buttons.setAlignment(Pos.CENTER);

        labelTitle = new Label("Informe o valor a ser sacado: ");
        labelTitle.getStyleClass().add("labelStyle");
        labelTitle.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));

        boxSaqueTitle = new HBox();
        boxSaqueTitle.setSpacing(CaixaView.METRICS.getPX(0.004));
        boxSaqueTitle.getStyleClass().add("boxViewDark");
        boxSaqueTitle.setAlignment(Pos.CENTER);
        boxSaqueTitle.getChildren().add(labelTitle);

        buttonSacar = new Button("Avançar");
        buttonSacar.getStyleClass().add("buttonDark");
        buttonSacar.setOnAction(controller.realizarSaqueAction());

        buttonCancelar = new Button("Cancelar");
        buttonCancelar.getStyleClass().add("buttonDark");
        buttonCancelar.setOnAction(controller.retornarMenu());

        buttons.getChildren().addAll(buttonSacar, buttonCancelar);

    }

    public void initSaqueView(MainView mainView, TecladoController tecladoController) {
        getChildren().add(boxSaqueTitle);
        getChildren().add(tecladoController.tecladoView);
        getChildren().add(buttons);
        mainView.getChildren().add(new Group(this));
    }

}
