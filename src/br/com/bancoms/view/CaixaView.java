package br.com.bancoms.view;

import br.com.bancoms.util.Metrics;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class CaixaView extends Application
{

    private Scene scene;
    private StackPane root;
    public static Metrics METRICS;

    public void start(Stage stage) throws Exception
    {

	METRICS = new Metrics(Screen.getPrimary().getVisualBounds().getWidth(),
		Screen.getPrimary().getVisualBounds().getHeight());

	root = new StackPane();
	root.getChildren().add(new MainView());

	scene = new Scene(root, METRICS.getPX(0.45), METRICS.getPX(0.22));

	// stage.setAlwaysOnTop(true);
	// stage.initStyle(StageStyle.UTILITY);
	stage.setScene(scene);
	stage.setResizable(false);
	stage.setTitle("Caixa Eletronico - Banco MS");
	stage.show();
    }

    public static void main(String[] args)
    {
	launch(args);
    }

}
