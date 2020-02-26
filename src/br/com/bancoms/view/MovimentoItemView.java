package br.com.bancoms.view;

import br.com.bancoms.controller.ExtratoController;
import br.com.bancoms.model.Cliente;
import br.com.bancoms.model.Movimento;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import static br.com.bancoms.view.MainView.formatarReal;

public class MovimentoItemView extends VBox {

    private final HBox boxResumo;
    private final Hyperlink detalhes;
    private Label clienteDestino;
    private HBox boxDetalhes;
    private Label clienteOrigem;
    private Movimento movimento;

    public MovimentoItemView(ExtratoController controller, Movimento movimento, int index) {

        setMovimento(movimento);

        double paddingBox = CaixaView.METRICS.getPX(0.0050);

        setSpacing(CaixaView.METRICS.getPX(0.005));
        setAlignment(Pos.CENTER);
        setFillWidth(true);
        setPadding(new Insets(paddingBox, paddingBox, paddingBox, paddingBox));
        getStyleClass().add("boxView");


        double padding = CaixaView.METRICS.getPX(0.0050);

        boxResumo = new HBox();
        boxResumo.setSpacing(CaixaView.METRICS.getPX(0.005));
        boxResumo.setAlignment(Pos.CENTER_LEFT);
        boxResumo.setPadding(new Insets(padding, padding, padding, padding));
        boxResumo.getStyleClass().add("boxViewCinza");

        boxDetalhes = new HBox();
        boxDetalhes.setSpacing(CaixaView.METRICS.getPX(0.005));
        boxDetalhes.setAlignment(Pos.CENTER_LEFT);
        boxDetalhes.setPadding(new Insets(padding, padding, padding, padding));
        boxDetalhes.getStyleClass().add("boxViewCinza");

        Label indice = new Label(index + " - ");
        indice.getStyleClass().add("labelLink");
        indice.setFont(Font.font(CaixaView.METRICS.getPX(0.007)));

        Label labelDescricao = new Label("Tipo: ");
        labelDescricao.getStyleClass().add("labelStyleDarkBold");
        labelDescricao.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));

        Label descricao = new Label(Movimento.EMovimento.values()[movimento.getTipo() - 1].getKey());
        descricao.getStyleClass().add("labelStyleDark");
        descricao.setFont(Font.font(CaixaView.METRICS.getPX(0.007)));

        Label labelData = new Label("Data: ");
        labelData.getStyleClass().add("labelStyleDarkBold");
        labelData.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));

        Label data = new Label(movimento.getData());
        data.getStyleClass().add("labelStyleDark");
        data.setFont(Font.font(CaixaView.METRICS.getPX(0.007)));


        Label descricaoCompletaLabel = new Label("Descrição: ");
        descricaoCompletaLabel.getStyleClass().add("labelStyleDarkBold");
        descricaoCompletaLabel.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));

        Label descricaoCompleta = new Label(movimento.getDescricao());
        descricaoCompleta.getStyleClass().add("labelStyleDark");
        descricaoCompleta.setFont(Font.font(CaixaView.METRICS.getPX(0.007)));

        Label clienteOrigemLabel = new Label("Cliente Origem: ");
        clienteOrigemLabel.getStyleClass().add("labelStyleDarkBold");
        clienteOrigemLabel.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));

        clienteOrigem = new Label();
        clienteOrigem.getStyleClass().add("labelStyleDark");
        clienteOrigem.setFont(Font.font(CaixaView.METRICS.getPX(0.007)));

        Label clienteDestinoLabel = new Label("Cliente Destino: ");
        clienteDestinoLabel.getStyleClass().add("labelStyleDarkBold");
        clienteDestinoLabel.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));

        clienteDestino = new Label();
        clienteDestino.getStyleClass().add("labelStyleDark");
        clienteDestino.setFont(Font.font(CaixaView.METRICS.getPX(0.007)));

        Label labelValor = new Label("Valor: ");
        labelValor.getStyleClass().add("labelStyleDarkBold");
        labelValor.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));

        Label valor = new Label(String.valueOf(formatarReal.format(movimento.getValor())));
        valor.getStyleClass().add("labelStyleDark");
        valor.setFont(Font.font(CaixaView.METRICS.getPX(0.007)));

        detalhes = new Hyperlink("Detalhes");
        detalhes.getStyleClass().add("labelLink");
        detalhes.setOnAction(controller.detalhesMovimentoAction(this));

        boxResumo.getChildren().addAll(indice, labelDescricao, descricao, labelData, data, labelValor, valor);
        boxDetalhes.getChildren().addAll(descricaoCompletaLabel, descricaoCompleta, clienteOrigemLabel, clienteOrigem, clienteDestinoLabel, clienteDestino);

        View.setSizeElemento(this, 0.77, 0.40);

        getChildren().add(boxResumo);
        getChildren().add(detalhes);
    }

    public void detalhesView(Cliente origemCliente, Cliente clienteSessao) {

        clienteOrigem.setText(clienteSessao.getNomeCompleto());
        clienteDestino.setText(origemCliente.getNomeCompleto());

        getChildren().clear();
        getChildren().add(boxResumo);
        getChildren().add(boxDetalhes);
        getChildren().add(detalhes);
    }


    public Movimento getMovimento() {
        return movimento;
    }

    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
    }


    public void removerDetalhesView() {
        getChildren().remove(boxDetalhes);
    }
}
