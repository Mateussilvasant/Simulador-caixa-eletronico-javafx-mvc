package br.com.bancoms.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class MainView extends StackPane
{

    private StackPane topBar;
    public Label labelTituloBar;

    public MainView()
    {
	initBar();
	initLoginView();
    }

    private void initBar()
    {

	this.getStyleClass().add("background");
	
	Rectangle rectangle = new Rectangle(CaixaView.METRICS.getPX(CaixaView.METRICS.getWidth()),
		CaixaView.METRICS.getPX(0.02));
	rectangle.getStyleClass().add("barBackground");
	
	topBar = new StackPane();
	topBar.getChildren().add(rectangle);
	topBar.setTranslateY(CaixaView.METRICS.getX(-0.18));

	labelTituloBar = new Label("Teste");
	labelTituloBar.setTextFill(Color.WHITE);
	labelTituloBar.setFont(Font.font(20));

	topBar.getChildren().add(labelTituloBar);
	super.getChildren().add(topBar);
    }

    public Alert getAlert(String titulo, String conteudo, AlertType tipo)
    {
	Alert alert = new Alert(tipo);
	alert.setTitle(titulo);
	alert.setHeaderText(conteudo);
	return alert;
    }

    public void initLoginView()
    {
	new LoginView(this);
    }

}
