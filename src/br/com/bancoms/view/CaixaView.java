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

        METRICS = new Metrics(Screen.getPrimary().getVisualBounds().getWidth(),
                Screen.getPrimary().getVisualBounds().getHeight());

        Font applicationFont = new Font("sans-serif", 100);

        StackPane root = new StackPane();
        root.getChildren().add(new MainView());

        Scene scene = new Scene(root, METRICS.getPX(0.50), METRICS.getPX(0.25));
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
