package br.com.bancoms.view;

import br.com.bancoms.controller.TecladoController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TecladoView extends Group
{
    private GridPane gridBotoes;
    public VBox vboxView;
    public TextField numeroField;
    private Button botaoEntrar;
    private Button botaoApagar;

    public TecladoView(TecladoController tecladoController)
    {
	vboxView = new VBox();
	vboxView.setSpacing(CaixaView.METRICS.getPX(0.008));
	vboxView.getStyleClass().add("boxView");

	numeroField = new TextField();
	numeroField.setText("0,00");
	numeroField.setEditable(false);
	numeroField.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));
	numeroField.getStyleClass().add("numeroField");

	gridBotoes = new GridPane();
	gridBotoes.setAlignment(Pos.CENTER);

	HBox hboxButton = new HBox();
	hboxButton.setAlignment(Pos.CENTER_RIGHT);

	botaoEntrar = new Button("Entrar");
	botaoEntrar.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));
	botaoEntrar.getStyleClass().add("buttonConfirmar");
	botaoEntrar.setOnAction(tecladoController);

	botaoApagar = new Button("Limpar");
	botaoApagar.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));
	botaoApagar.getStyleClass().add("buttonLimpar");
	botaoApagar.setOnAction(tecladoController);

	hboxButton.getChildren().addAll(botaoApagar, botaoEntrar);

	int numero = 0;

	for (int linha = 0; linha <= 1; linha++)
	{
	    for (int coluna = 0; coluna <= 4; coluna++)
	    {

		adicionarBotao(linha, coluna, numero, tecladoController);
		numero++;
	    }
	}

	vboxView.getChildren().addAll(numeroField, gridBotoes, hboxButton);

	getChildren().add(vboxView);
    }

    private void adicionarBotao(int l, int c, int indice, TecladoController tecladoController)
    {
	Button botao = new Button(indice + "");
	botao.setFont(Font.font(CaixaView.METRICS.getPX(0.012)));
	botao.getStyleClass().add("buttonTeclado");
	botao.setOnAction(tecladoController);
	gridBotoes.add(botao, c, l);
    }
}
