package br.com.bancoms.view;

import br.com.bancoms.model.Movimento;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MovimentoItemView extends VBox {

    private final HBox vboxResumo;

    public MovimentoItemView(Movimento movimento, int index) {
        double paddingBox = CaixaView.METRICS.getPX(0.0050);

        setSpacing(CaixaView.METRICS.getPX(0.005));
        setAlignment(Pos.CENTER);
        setFillWidth(true);
        setPadding(new Insets(paddingBox, paddingBox, paddingBox, paddingBox));
        getStyleClass().add("boxView");


        double padding = CaixaView.METRICS.getPX(0.0050);

        vboxResumo = new HBox();
        vboxResumo.setSpacing(CaixaView.METRICS.getPX(0.005));
        vboxResumo.setAlignment(Pos.CENTER);
        vboxResumo.setPadding(new Insets(padding, padding, padding, padding));
        vboxResumo.getStyleClass().add("boxViewCinza");

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

        Hyperlink detalhes = new Hyperlink("Detalhes");
        detalhes.getStyleClass().add("labelLink");

        vboxResumo.getChildren().addAll(indice, labelDescricao, descricao,labelData,data);

        View.setSizeElemento(this, 0.77, 0.40);

        getChildren().add(vboxResumo);
        getChildren().add(detalhes);

    }


}
