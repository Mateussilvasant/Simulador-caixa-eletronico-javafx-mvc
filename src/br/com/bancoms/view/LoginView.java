package br.com.bancoms.view;

import br.com.bancoms.controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LoginView extends StackPane
{
    private VBox loginBox;
    private Label labelLoginTitulo;
    private Label labelNumeroConta;
    public TextField fieldNumeroConta;
    private Label labelSenhaConta;
    public PasswordField fieldSenha;
    private Button botaoEntrar;

    public LoginView(MainView mainView)
    {
	Group group = new Group();
	loginBox = new VBox();
	loginBox.setSpacing(CaixaView.METRICS.getPX(0.008));
	loginBox.setAlignment(Pos.CENTER);

	loginBox.getStyleClass().add("loginBox");

	double insets = CaixaView.METRICS.getPX(0.025);
	loginBox.setPadding(new Insets(insets, insets, insets, insets));

	labelLoginTitulo = new Label("Acesse sua Conta");
	labelLoginTitulo.setFont(Font.font(CaixaView.METRICS.getPX(0.0090)));
	labelLoginTitulo.getStyleClass().add("labelStyle");

	VBox hbox1 = new VBox();
	labelNumeroConta = new Label("Número da Conta: ");
	labelNumeroConta.getStyleClass().add("labelStyle");
	labelNumeroConta.setFont(Font.font(CaixaView.METRICS.getPX(0.0055)));
	fieldNumeroConta = new TextField();
	fieldNumeroConta.getStyleClass().add("fieldStyle");

	hbox1.getChildren().addAll(labelNumeroConta, fieldNumeroConta);

	VBox hbox2 = new VBox();
	labelSenhaConta = new Label("Senha: ");
	labelSenhaConta.getStyleClass().add("labelStyle");
	labelSenhaConta.setFont(Font.font(CaixaView.METRICS.getPX(0.0055)));
	fieldSenha = new PasswordField();
	fieldSenha.getStyleClass().add("fieldStyle");
	hbox2.getChildren().addAll(labelSenhaConta, fieldSenha);

	botaoEntrar = new Button("Acessar");
	botaoEntrar.getStyleClass().add("buttonStyle");
	botaoEntrar.setCursor(Cursor.CLOSED_HAND);

	mainView.labelTituloBar.setText("Sistema Banco MS - Login");

	loginBox.getChildren().addAll(labelLoginTitulo, hbox1, hbox2, botaoEntrar);

	botaoEntrar.setOnAction(new LoginController(mainView, this));

	group.getChildren().add(loginBox);
	getChildren().add(group);
	mainView.getChildren().add(this);

    }

}
