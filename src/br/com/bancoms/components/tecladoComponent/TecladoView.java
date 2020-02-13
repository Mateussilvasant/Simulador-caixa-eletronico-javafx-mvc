package br.com.bancoms.components.tecladoComponent;

import br.com.bancoms.view.CaixaView;
import br.com.bancoms.view.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;


public class TecladoView extends Group {
    private GridPane gridBotoes;
    private VBox vboxView;
    public TextField numeroField;
    private Button botaoEntrar;
    private Button botaoApagar;
    private List<Button> botoesNumericos;

    public TecladoView(TecladoAdapter tecladoAdapter) {
        double paddingBox = CaixaView.METRICS.getPX(0.0060);

        vboxView = new VBox();
        vboxView.setSpacing(CaixaView.METRICS.getPX(0.008));
        vboxView.setPadding(new Insets(paddingBox, paddingBox, paddingBox, paddingBox));
        vboxView.getStyleClass().add("boxView");

        View.setSizeElemento(vboxView, 0.20, 0.40);

        numeroField = new TextField();
        numeroField.setText("");
        numeroField.setEditable(false);
        numeroField.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));
        numeroField.getStyleClass().add("numeroField");

        gridBotoes = new GridPane();
        gridBotoes.setAlignment(Pos.CENTER);
        gridBotoes.setHgap(CaixaView.METRICS.getPX(0.002));
        gridBotoes.setVgap(CaixaView.METRICS.getPX(0.002));


        HBox hboxButton = new HBox();
        hboxButton.setAlignment(Pos.CENTER);
        hboxButton.setSpacing(CaixaView.METRICS.getPX(0.002));


        botaoEntrar = View.createButton("Entrar", "buttonDark", 0.085, 0.050, 0.006);
        botaoApagar = View.createButton("Limpar", "buttonDark", 0.085, 0.050, 0.006);

        hboxButton.getChildren().addAll(botaoApagar, botaoEntrar);

        botoesNumericos = new ArrayList<>();

        int numero = 0;

        for (int linha = 0; linha <= 1; linha++) {
            for (int coluna = 0; coluna <= 4; coluna++) {

                adicionarBotao(linha, coluna, numero, tecladoAdapter);
                numero++;
            }
        }

        vboxView.getChildren().addAll(numeroField, gridBotoes, hboxButton);
        getChildren().add(vboxView);
    }

    private void adicionarBotao(int l, int c, int indice, TecladoAdapter tecladoAdapter) {
        Button botao = new Button(indice + "");
        botao.setFont(Font.font(CaixaView.METRICS.getPX(0.012)));
        botao.getStyleClass().add("buttonTeclado");
        botoesNumericos.add(botao);
        gridBotoes.add(botao, c, l);
    }

    public void anexarEventos(TecladoAdapter controller) {
        controller.getTeclado().anexarEvento(botaoEntrar);
        controller.getTeclado().anexarEvento(botaoApagar);

        for (Button button : botoesNumericos) {
            controller.getTeclado().anexarEvento(button);
        }

    }

    public void addFieldExterno() {
        vboxView.getChildren().remove(numeroField);
        View.setSizeElemento(vboxView, 0.20, 0.30);
    }

    public void addFieldInterno() {

        if (!vboxView.getChildren().contains(numeroField)) {
            vboxView.getChildren().add(0, numeroField);
        }

    }
}
