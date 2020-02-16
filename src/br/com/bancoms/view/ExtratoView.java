package br.com.bancoms.view;

import br.com.bancoms.controller.ClienteController;
import br.com.bancoms.controller.ExtratoController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class ExtratoView extends VBox {


    public ExtratoView(ExtratoController extratoController) {

        double paddingBox = CaixaView.METRICS.getPX(0.0050);

        setSpacing(CaixaView.METRICS.getPX(0.005));
        setAlignment(Pos.CENTER);
        setFillWidth(false);
        setPadding(new Insets(paddingBox, paddingBox, paddingBox, paddingBox));
        getStyleClass().add("boxView");

        View.setSizeElemento(this, 0.80, 0.70);
    }

    public void iniciarView(ClienteController clienteController) {
        clienteController.view.getChildren().add(this);
    }

}
