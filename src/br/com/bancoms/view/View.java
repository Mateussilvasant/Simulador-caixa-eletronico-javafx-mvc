package br.com.bancoms.view;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

import java.util.Arrays;

public class View {


    public static Button createButton(String nome, String style, double width, double height, double size) {
        Button button = new Button(nome);
        button.getStyleClass().add(style);
        button.setFont(Font.font(CaixaView.METRICS.getPX(size)));
        button.setStyle("-fx-font-size: " + CaixaView.METRICS.getPX(size) + ";");
        setSizeElemento(button, width, height);

        return button;
    }

    public static Button createButton(String nome, String style, String imgURL, double width, double height, double size) {
        Button button = new Button(nome);
        button.getStyleClass().add(style);
        button.setGraphic(new ImageView(imgURL));
        button.setFont(Font.font(CaixaView.METRICS.getPX(size)));
        button.setStyle("-fx-font-size: " + CaixaView.METRICS.getPX(size) + ";");
        setSizeElemento(button, width, height);

        return button;
    }


    public static void setSizeElemento(double width, double height, Region... regions) {
        Arrays.asList(regions).forEach(region -> {
            setSizeElemento(region, width, height);
        });
    }

    public static void setSizeElemento(Region region, double width, double height) {
        double w = CaixaView.METRICS.getPX(width, true);
        double h = CaixaView.METRICS.getPX(height, false);

        region.setMaxSize(w, h);
        region.setPrefSize(w, h);
    }


}
