package br.com.bancoms.view;

import br.com.bancoms.components.tecladoComponent.TecladoAdapter;
import br.com.bancoms.controller.SaqueController;
import br.com.bancoms.SistemaCaixaEletronico;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

        double paddingBox = SistemaCaixaEletronico.METRICS.getPX(0.0050);

        setSpacing(SistemaCaixaEletronico.METRICS.getPX(0.005));
        setAlignment(Pos.CENTER);
        setPadding(new Insets(paddingBox, paddingBox, paddingBox, paddingBox));
        getStyleClass().add("boxView");
        View.setSizeElemento(this, 0.60, 0.60);

        labelTitle = new Label("Informe o valor a ser sacado: ");
        labelTitle.getStyleClass().add("labelStyleDark");
        labelTitle.setFont(Font.font(SistemaCaixaEletronico.METRICS.getPX(0.012)));

        boxSaqueTitle = new HBox();
        boxSaqueTitle.setSpacing(SistemaCaixaEletronico.METRICS.getPX(0.004));
        boxSaqueTitle.setAlignment(Pos.CENTER);
        boxSaqueTitle.getChildren().add(labelTitle);

        buttonSacar = View.createButton("Sacar", "buttonPrimary", 0.090, 0.060, 0.0065);
        buttonSacar.setOnAction(controller.realizarSaqueAction());

        buttonCancelar = View.createButton("Cancelar", "buttonPrimary", 0.090, 0.060, 0.0065);
        buttonCancelar.setOnAction(controller.cancelarAction());

        double pBotoes = SistemaCaixaEletronico.METRICS.getPX(0.0050);

        buttons = new HBox();
        buttons.setSpacing(SistemaCaixaEletronico.METRICS.getPX(0.005));
        buttons.setPadding(new Insets(pBotoes, pBotoes, pBotoes, pBotoes));
        buttons.setAlignment(Pos.CENTER_RIGHT);
        View.setSizeElemento(buttons, 0.60, 0.20);

        buttons.getStyleClass().add("boxViewSecundary");
        buttons.getChildren().addAll(buttonSacar, buttonCancelar);
    }

    public void initSaqueView(MainView mainView, TecladoAdapter tecladoAdapter) {
        getChildren().add(boxSaqueTitle);
        getChildren().add(tecladoAdapter.tecladoView);
        getChildren().add(buttons);
        mainView.getChildren().add(this);
    }

}
