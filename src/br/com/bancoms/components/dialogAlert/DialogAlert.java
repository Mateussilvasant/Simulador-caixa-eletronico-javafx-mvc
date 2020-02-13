package br.com.bancoms.components.dialogAlert;

import br.com.bancoms.util.Metrics;
import br.com.bancoms.view.CaixaView;
import br.com.bancoms.view.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class DialogAlert extends VBox {

    private final Pane rootPane;
    private AlertType tipoDialog;
    private Rectangle backgroundDialog;
    private Button buttonOk;
    private Button buttonSim;
    private Button buttonNao;


    public enum AlertType {
        INFORMATION(1),
        OPTION(2);

        private int value;

        AlertType(int value) {
            this.value = value;
        }
    }

    public DialogAlert(AlertType tipo, String titulo, String conteudo, Pane root, boolean background) {

        double paddingBox = CaixaView.METRICS.getPX(0.0040);

        tipoDialog = tipo;
        rootPane = root;

        backgroundDialog = new Rectangle(Metrics.originalWidth, Metrics.originalHeight);
        backgroundDialog.getStyleClass().add("backgroundDialog");
        backgroundDialog.setEffect(new BoxBlur());

        setAlignment(Pos.CENTER);
        setFillWidth(false);
        setSpacing(CaixaView.METRICS.getPX(0.005));
        setPadding(new Insets(paddingBox, paddingBox, paddingBox, paddingBox));
        getStyleClass().add("boxView");
        View.setSizeElemento(this, 0.40, 0.30);

        Label tituloLabel = new Label(titulo);
        tituloLabel.getStyleClass().add("labelStyleDarkSemi");
        tituloLabel.setFont(Font.font(CaixaView.METRICS.getPX(0.010)));

        Label conteudoLabel = new Label(conteudo);
        conteudoLabel.getStyleClass().add("labelStyleDark");
        conteudoLabel.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));

        HBox buttons = new HBox();
        buttons.setSpacing(CaixaView.METRICS.getPX(0.004));

        if (tipo.equals(AlertType.INFORMATION)) {
            buttonOk = View.createButton("Ok", "buttonPrimary", 0.090, 0.060, 0.0065);
            buttons.getChildren().add(buttonOk);
        } else if (tipo.equals(AlertType.OPTION)) {
            buttonSim = View.createButton("Sim", "buttonPrimary", 0.090, 0.060, 0.0065);
            buttonNao = View.createButton("Não", "buttonStyle", 0.090, 0.060, 0.0065);

            buttons.getChildren().addAll(buttonSim, buttonNao);
        }

        if (background) {
            root.getChildren().add(backgroundDialog);
        }

        getChildren().add(tituloLabel);
        getChildren().add(conteudoLabel);
        getChildren().add(buttons);
        root.getChildren().add(this);
    }


    public void setEventInformation(EventHandler<ActionEvent> event) {
        if (tipoDialog.equals(AlertType.INFORMATION)) {
            buttonOk.setOnAction(event);
        }
    }

    public void setEventConfirmation(EventHandler<ActionEvent> eventSim, EventHandler<ActionEvent> eventNao) {
        if (tipoDialog.equals(AlertType.OPTION)) {
            buttonSim.setOnAction(eventSim);
            buttonNao.setOnAction(eventNao);
        }
    }

    public void fecharDialog() {
        rootPane.getChildren().remove(backgroundDialog);
        rootPane.getChildren().remove(this);
    }

}
