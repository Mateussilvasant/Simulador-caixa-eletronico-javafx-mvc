package br.com.bancoms.view;

import br.com.bancoms.controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class LoginView extends StackPane {
    public TextField fieldNumeroConta;
    public PasswordField fieldSenha;

    public LoginView(MainView mainView) {

        double insets = BancoMS.METRICS.getPX(0.015);

        setPickOnBounds(false);

        Group group = new Group();
        VBox loginBox = new VBox();
        loginBox.setSpacing(BancoMS.METRICS.getPX(0.015));
        loginBox.setAlignment(Pos.CENTER);
        loginBox.getStyleClass().add("loginBox");

        loginBox.setPadding(new Insets(insets, insets, insets, insets));
        View.setSizeElemento(loginBox, 0.25, 0.60);

        Label labelLoginTitulo = new Label("Acesse sua Conta");
        labelLoginTitulo.setFont(Font.font(BancoMS.METRICS.getPX(0.012)));
        labelLoginTitulo.getStyleClass().add("labelStyleDark");

        VBox hbox1 = new VBox();
        Label labelNumeroConta = new Label("Número Conta");
        labelNumeroConta.getStyleClass().add("labelStyleDark");
        labelNumeroConta.setFont(Font.font(BancoMS.METRICS.getPX(0.007)));

        Label labelSenhaConta = new Label("Senha");
        labelSenhaConta.getStyleClass().add("labelStyleDark");
        labelSenhaConta.setFont(Font.font(BancoMS.METRICS.getPX(0.007)));

        fieldNumeroConta = new TextField();
        fieldNumeroConta.getStyleClass().add("fieldStyle");
        fieldNumeroConta.setFont(Font.font(BancoMS.METRICS.getPX(0.007)));


        fieldSenha = new PasswordField();
        fieldSenha.setFont(Font.font(BancoMS.METRICS.getPX(0.007)));
        fieldSenha.getStyleClass().add("fieldStyle");

        View.setSizeElemento(0.20, 0.05, fieldNumeroConta, fieldSenha);


        hbox1.getChildren().addAll(labelNumeroConta, fieldNumeroConta);

        VBox hbox2 = new VBox();

        hbox2.getChildren().addAll(labelSenhaConta, fieldSenha);

        Button botaoEntrar = View.createButton(
                "Acessar",
                "buttonPrimary",
                0.13,
                0.05,
                0.007);

        mainView.setTituloBarText("Login");

        loginBox.getChildren().addAll(labelLoginTitulo, hbox1, hbox2, botaoEntrar);

        botaoEntrar.setOnAction(new LoginController(mainView, this));


        group.getChildren().add(loginBox);
        getChildren().add(group);
        mainView.getChildren().add(this);

    }

}
