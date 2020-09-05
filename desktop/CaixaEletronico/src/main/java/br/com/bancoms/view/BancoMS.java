package br.com.bancoms.view;

import br.com.bancoms.util.Metrics;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class BancoMS extends Application{
    public static Metrics METRICS;
    
	@Override
	public void start(Stage stage) throws Exception {

        System.setProperty("prism.lcdtext", "false");

        Font.loadFont("/assets/font/RobotoMedium.tff", 30);

        METRICS = new Metrics(Screen.getPrimary().getVisualBounds().getWidth() * 0.98,
                Screen.getPrimary().getVisualBounds().getHeight() * 0.90);
        METRICS.setXWindow(stage.getX());
        METRICS.setYWindow(stage.getY());

        StackPane root = new StackPane();
        root.getChildren().add(new MainView());

        Scene scene = new Scene(root, METRICS.getWidth(), METRICS.getHeight());
        scene.getStylesheets().add(getClass().getResource("/assets/css/caixaView.css").toExternalForm());

        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("BancoMS");
        stage.show();
    }

}
