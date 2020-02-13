package br.com.bancoms.view;

import br.com.bancoms.util.Metrics;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class CaixaView extends Application {

    public static Metrics METRICS;

    public void start(Stage stage) {

        System.setProperty("prism.lcdtext", "false");

        Font.loadFont("/br/com/bancoms/assets/font/RobotoMedium.tff", 30);

        METRICS = new Metrics(Screen.getPrimary().getVisualBounds().getWidth() * 0.98,
                Screen.getPrimary().getVisualBounds().getHeight() * 0.85);
        METRICS.setXWindow(stage.getX());
        METRICS.setYWindow(stage.getY());

        Font applicationFont = new Font("sans-serif", 100);

        StackPane root = new StackPane();
        root.getChildren().add(new MainView());

        Scene scene = new Scene(root, METRICS.getWidth(), METRICS.getHeight());
        scene.getStylesheets().add(getClass().getResource("/br/com/bancoms/assets/css/caixaView.css").toExternalForm());

        // stage.setAlwaysOnTop(true);
        // stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Caixa Eletronico - Banco MS");
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}
