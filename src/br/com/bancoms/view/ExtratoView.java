package br.com.bancoms.view;

import br.com.bancoms.controller.ClienteController;
import br.com.bancoms.controller.ExtratoController;
import br.com.bancoms.model.EMovimento;
import br.com.bancoms.model.Movimento;
import br.com.bancoms.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static br.com.bancoms.view.MainView.formatarReal;

public class ExtratoView extends VBox {

    private Button botaoVoltar;
    private ExtratoController extratoController;
    private MovimentoItemView movimentoItemViewAtual;

    private HashMap<String, Integer> tiposMovimentos;
    private ScrollPane movimentosScrollPane;
    private ComboBox tipoMovimentoOpcao;
    private ComboBox diasOpcao;

    private HBox saldoBox;
    private VBox movimentosListPane;
    private HBox boxFiltro;
    private VBox boxLabelCentral;

    private TextFlow labelExtrato;
    private Button botaoExtratos;

    public ExtratoView(ExtratoController extratoController) {

        this.extratoController = extratoController;
        double paddingBox = CaixaView.METRICS.getPX(0.0050);

        setSpacing(CaixaView.METRICS.getPX(0.005));
        setAlignment(Pos.CENTER);
        setFillWidth(false);
        setTranslateY(getTranslateY() + CaixaView.METRICS.getPX(0.010));
        setPadding(new Insets(paddingBox, paddingBox, paddingBox, paddingBox));
        getStyleClass().add("boxView");


        Label tipoMovimentoLabel = new Label("Tipo de Movimento: ");
        tipoMovimentoLabel.getStyleClass().add("labelStyleDark");
        tipoMovimentoLabel.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));

        Label periodoLabel = new Label("Período: ");
        periodoLabel.getStyleClass().add("labelStyleDark");
        periodoLabel.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));

        labelExtrato = new TextFlow();

        Text ola = new Text("Olá ");
        ola.getStyleClass().add("labelStyleDark");
        ola.setFont(Font.font(CaixaView.METRICS.getPX(0.009)));

        Text nomeClient = new Text(extratoController.clienteController.clienteSessao.getNome());
        nomeClient.getStyleClass().add("labelStyleDarkBold");
        nomeClient.setFont(Font.font(CaixaView.METRICS.getPX(0.009)));

        Text extratoFrase = new Text("  esse é o seu extrato de hoje");
        extratoFrase.getStyleClass().add("labelStyleDark");
        extratoFrase.setFont(Font.font(CaixaView.METRICS.getPX(0.009)));

        Text data = new Text("(" + DateUtil.currentDate() + ")");
        data.getStyleClass().add("labelStyleDarkBold");
        data.setFont(Font.font(CaixaView.METRICS.getPX(0.009)));


        labelExtrato.getChildren().addAll(ola, nomeClient, extratoFrase, data);


        double pBotoes = CaixaView.METRICS.getPX(0.0050);

        movimentosListPane = new VBox();
        movimentosListPane.setSpacing(CaixaView.METRICS.getPX(0.005));
        movimentosListPane.setAlignment(Pos.CENTER_LEFT);
        movimentosListPane.setPadding(new Insets(pBotoes, pBotoes, pBotoes, pBotoes));
        View.setSizeElemento(movimentosListPane, 0.80, 0.40);
        movimentosListPane.getStyleClass().add("background");

        movimentosScrollPane = new ScrollPane() {
            @Override
            public void requestFocus() {
            }
        };
        movimentosScrollPane.setFitToHeight(true);
        movimentosScrollPane.setFitToWidth(true);
        movimentosScrollPane.setFocusTraversable(true);
        movimentosScrollPane.setContent(movimentosListPane);

        boxFiltro = new HBox();
        boxFiltro.setSpacing(CaixaView.METRICS.getPX(0.005));
        boxFiltro.setAlignment(Pos.CENTER_LEFT);
        boxFiltro.setPadding(new Insets(pBotoes, pBotoes, pBotoes, pBotoes));
        View.setSizeElemento(boxFiltro, 0.80, 0.10);
        boxFiltro.getStyleClass().add("boxViewCinza");

        saldoBox = new HBox();
        saldoBox.setSpacing(CaixaView.METRICS.getPX(0.005));
        saldoBox.setAlignment(Pos.CENTER);
        saldoBox.setPadding(new Insets(pBotoes, pBotoes, pBotoes, pBotoes));
        View.setSizeElemento(saldoBox, 0.80, 0.10);
        saldoBox.getStyleClass().add("boxViewCinza");

        boxLabelCentral = new VBox();
        boxLabelCentral.setSpacing(CaixaView.METRICS.getPX(0.004));
        boxLabelCentral.setAlignment(Pos.CENTER_LEFT);
        View.setSizeElemento(boxLabelCentral, 0.80, 0.10);
        boxLabelCentral.getChildren().add(labelExtrato);

        botaoExtratos = View.createButton("Buscar", "buttonSolid", 0.050, 0.050, 0.0065);
        botaoExtratos.setOnAction(extratoController.consultarExtratoAction());

        botaoVoltar = View.createButton("Voltar", "buttonSolid", 0.050, 0.050, 0.0065);
        botaoVoltar.setOnAction(extratoController.cancelarAction());

        tipoMovimentoOpcao = new ComboBox<>();
        criarTiposMovimentosOpcoes();
        tipoMovimentoOpcao.getSelectionModel().selectLast();


        diasOpcao = new ComboBox<>(getDiasOpcoes());
        diasOpcao.getSelectionModel().selectLast();

        criarSaldoBox(extratoController);


        boxFiltro.getChildren().addAll(tipoMovimentoLabel, tipoMovimentoOpcao, periodoLabel, diasOpcao, botaoExtratos);

        View.setSizeElemento(this, 0.90, 0.90);
    }

    private void criarSaldoBox(ExtratoController extratoController) {

        double diferencial = extratoController.getSaldoDiferencial();
        double saldo = extratoController.clienteController.getContaSessao().getSaldo();
        double saldoSemDiferencial = extratoController.getSaldoSemDiferencial();

        boolean tipo = diferencial < 0;

        Label labelSaldoTotal = new Label("Saldo Total(A):");
        labelSaldoTotal.getStyleClass().add("labelStyleDarkBold");
        labelSaldoTotal.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));


        Label saldoTotal = new Label(String.valueOf(formatarReal.format(saldoSemDiferencial)));

        Label labelDiferencial = new Label(tipo ? "Despesas(B): " : "Rendimentos(B): ");
        labelDiferencial.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));
        labelDiferencial.getStyleClass().add("labelStyleDarkBold");

        Label diferencia = new Label(String.valueOf((formatarReal.format(diferencial))));

        Label labelsaldoSemDiferencial = new Label("A + B = ");
        labelsaldoSemDiferencial.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));
        labelsaldoSemDiferencial.getStyleClass().add("labelStyleDarkBold");

        Label semDiferencial = new Label(String.valueOf((formatarReal.format(saldo))));

        labelSaldoTotal.setLabelFor(saldoTotal);
        labelDiferencial.setLabelFor(diferencia);
        labelsaldoSemDiferencial.setLabelFor(semDiferencial);


        saldoBox.getChildren().addAll(labelSaldoTotal,
                saldoTotal,
                labelDiferencial,
                diferencia,
                labelsaldoSemDiferencial,
                semDiferencial,
                botaoVoltar);
    }

    public void iniciarView(ClienteController clienteController) {
        getChildren().add(boxLabelCentral);
        getChildren().add(boxFiltro);
        getChildren().add(movimentosScrollPane);
        getChildren().add(saldoBox);
        clienteController.view.getChildren().add(this);
    }

    private void criarTiposMovimentosOpcoes() {
        setTiposMovimentos();
        addTiposMovimentosComboBox();
    }

    private void setTiposMovimentos() {

        tiposMovimentos = new HashMap<>();

        for (EMovimento movimento : EMovimento.values()) {
            tiposMovimentos.put(movimento.getKey(), movimento.getValue());
        }

    }

    private void addTiposMovimentosComboBox() {
        AtomicInteger index = new AtomicInteger();

        tipoMovimentoOpcao.getItems().add(index.get(), "Selecione");
        tiposMovimentos.keySet().forEach(k -> tipoMovimentoOpcao.getItems().add(index.getAndIncrement(), k));
        tipoMovimentoOpcao.getItems().add(index.get(), "Todos");
    }

    public int getTipoAtualValor() {
        String item = tipoMovimentoOpcao.getSelectionModel().getSelectedItem().toString();

        if (!item.equals("Selecione")) {
            return tiposMovimentos.get(item);
        }
        return 0;
    }

    public int getPeriodo() {
        String item = diasOpcao.getSelectionModel().getSelectedItem().toString();

        if (!item.equals("Selecione")) {
            return Integer.parseInt(item.substring(0, 2));
        }
        return 0;
    }

    private ObservableList<String> getDiasOpcoes() {
        return FXCollections.observableArrayList("02 dias", "05 dias", "30 dias", "60 dias", "Selecione");
    }

    public String getTipoAtual() {
        return tipoMovimentoOpcao.getSelectionModel().getSelectedItem().toString();
    }


    public void adicionarMovimentoItem(Movimento movimento, int index) {
        MovimentoItemView itemView = new MovimentoItemView(extratoController, movimento, index);
        movimentosListPane.getChildren().add(itemView);
    }

    public void trocarDetalhes(MovimentoItemView itemView) {
        if (movimentoItemViewAtual != null) {
            movimentoItemViewAtual.removerDetalhesView();
        }
        movimentoItemViewAtual = itemView;
    }

    public void limparMovimentosView() {
        movimentosListPane.getChildren().clear();
    }


}
