package br.com.bancoms.view;


import br.com.bancoms.components.dialogAlert.DialogAlert;
import br.com.bancoms.model.Conta;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.text.NumberFormat;
import java.util.Locale;

public class MainView extends StackPane {

    private Label titleBar;
    private Label labelClienteNome;
    private Label labelClienteTipoConta;
    private Label labelClienteSaldo;
    private Label labelMostrarSaldo;
    private Button botaoSair;
    private VBox boxSubMenuCliente;
    private boolean saldoOculto;
    private StackPane topBar;
    private StackPane subMenuCliente;
    public static NumberFormat formatarReal = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));


    public MainView() {
        initBar();
        initLoginView();
    }

    private void initBar() {

        saldoOculto = false;

        getStyleClass().add("background");

        Rectangle rectangle = new Rectangle(CaixaView.METRICS.getPX(CaixaView.METRICS.getWidth()),
                CaixaView.METRICS.getPX(0.024));
        rectangle.getStyleClass().add("barBackground");

        topBar = new StackPane();
        topBar.setPrefSize(rectangle.getWidth(), rectangle.getHeight());
        topBar.getChildren().add(rectangle);
        topBar.setTranslateY(CaixaView.METRICS.getY(-0.475));


        Rectangle rectLogo = new Rectangle();
        StackPane logo = new StackPane();
        rectLogo.setWidth(CaixaView.METRICS.getPX(0.080));
        rectLogo.setHeight(CaixaView.METRICS.getPX(0.017));
        logo.setPrefSize(rectLogo.getWidth(), rectLogo.getHeight());

        rectLogo.setArcHeight(20);
        rectLogo.setArcWidth(20);
        rectLogo.getStyleClass().add("logoRect");
        logo.setPrefSize(rectLogo.getWidth(), rectLogo.getHeight());
        logo.setTranslateX(CaixaView.METRICS.getX(-0.350));

        Text fontLogo = new Text("Banco M&S");
        fontLogo.setFont(Font.font(CaixaView.METRICS.getPX(0.010)));
        fontLogo.setFill(Color.WHITE);


        logo.getChildren().addAll(rectLogo, fontLogo);


        Rectangle rectSubMenuCliente = new Rectangle();
        subMenuCliente = new StackPane();
        rectSubMenuCliente.setWidth(CaixaView.METRICS.getPX(0.080));
        rectSubMenuCliente.setHeight(CaixaView.METRICS.getPX(0.017));
        subMenuCliente.setPrefSize(rectLogo.getWidth(), rectLogo.getHeight());
        subMenuCliente.setOnMouseClicked(mostrarSaldo());

        rectSubMenuCliente.setArcHeight(20);
        rectSubMenuCliente.setArcWidth(20);
        rectSubMenuCliente.getStyleClass().add("logoRect");
        subMenuCliente.setPrefSize(rectLogo.getWidth(), rectLogo.getHeight());
        subMenuCliente.setTranslateX(CaixaView.METRICS.getX(0.320));

        labelClienteNome = new Label();
        labelClienteNome.getStyleClass().add("labelStyle2");
        labelClienteNome.setFont(Font.font(CaixaView.METRICS.getPX(0.007)));
        labelClienteNome.setTranslateX(CaixaView.METRICS.getX(0.190));

        labelClienteTipoConta = new Label();
        labelClienteTipoConta.getStyleClass().add("labelStyle2");
        labelClienteTipoConta.setFont(Font.font(CaixaView.METRICS.getPX(0.0055)));

        labelClienteSaldo = new Label();
        labelClienteSaldo.setFont(Font.font(CaixaView.METRICS.getPX(0.0055)));
        labelClienteSaldo.getStyleClass().add("labelStyle2");

        labelMostrarSaldo = new Label("Mostrar Saldo");
        labelMostrarSaldo.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));
        labelMostrarSaldo.getStyleClass().add("labelStyle2");

        boxSubMenuCliente = new VBox();
        boxSubMenuCliente.getChildren().addAll(labelClienteTipoConta, labelClienteSaldo);
        boxSubMenuCliente.setAlignment(Pos.CENTER);

        subMenuCliente.getChildren().addAll(rectSubMenuCliente, new Group(boxSubMenuCliente));

        titleBar = new Label("Teste");
        titleBar.setFont(Font.font(CaixaView.METRICS.getPX(0.008)));
        titleBar.getStyleClass().add("labelBar");

        botaoSair = View.createButton(
                "Sair",
                "buttonPrimary",
                0.05,
                0.03,
                0.007);

        botaoSair.setTranslateX(CaixaView.METRICS.getX(0.400));
        botaoSair.setFocusTraversable(false);

        botaoSair.setOnAction(e -> {
            DialogAlert alert = onAlertView("Confirmação", "Deseja realmente sair?", DialogAlert.AlertType.OPTION, true);

            alert.setEventConfirmation(
                    event -> System.exit(0),
                    event -> alert.fecharDialog()
            );
        });


        topBar.getChildren().add(logo);
        topBar.getChildren().add(titleBar);
        getChildren().add(topBar);
    }

    public void addSubMenuCliente(Conta conta, String nomeCliente) {
        labelClienteNome.setText(nomeCliente);
        labelClienteSaldo.setText(formatarReal.format(conta.getSaldo()));
        labelClienteTipoConta.setText(conta.getDescricao());

        topBar.getChildren().add(labelClienteNome);
        topBar.getChildren().add(subMenuCliente);
        topBar.getChildren().add(botaoSair);
    }

    public void atualizarSaldoView(double novoSaldo) {
        labelClienteSaldo.setText(formatarReal.format(novoSaldo));
    }

    private EventHandler<? super MouseEvent> mostrarSaldo() {
        return (event) -> {
            if (saldoOculto) {
                boxSubMenuCliente.getChildren().clear();
                boxSubMenuCliente.getChildren().addAll(labelClienteTipoConta, labelClienteSaldo);
                saldoOculto = false;
            } else {
                boxSubMenuCliente.getChildren().clear();
                boxSubMenuCliente.getChildren().addAll(labelMostrarSaldo);
                saldoOculto = true;
            }
        };
    }

    public void setTituloBarText(String texto) {
        titleBar.setText(texto);
    }

    public void transitarMenu(Pane menuAnterior, Pane proximoMenu) {
        menuAnterior.getChildren().clear();
        getChildren().remove(menuAnterior);
        getChildren().add(proximoMenu);
    }

    private void initLoginView() {
        new LoginView(this);
    }

    public DialogAlert onAlertView(String titulo, String conteudo, DialogAlert.AlertType tipo, boolean backgroundVisible) {
        DialogAlert alert = new DialogAlert(tipo, titulo, conteudo, this, backgroundVisible);
        return alert;
    }

}
