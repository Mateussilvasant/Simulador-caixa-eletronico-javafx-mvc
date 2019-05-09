package br.com.bancoms.view;

import br.com.bancoms.controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	loginBox.setSpacing(CaixaView.METRICS.getPX(0.004));
	loginBox.setAlignment(Pos.CENTER);
	loginBox.setBorder(new Border(
		new BorderStroke(Color.SLATEGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

	double insets = CaixaView.METRICS.getPX(0.020);
	loginBox.setPadding(new Insets(insets, insets, insets, insets));

	labelLoginTitulo = new Label("Acesse sua Conta");
	labelLoginTitulo.setFont(Font.font(CaixaView.METRICS.getPX(0.0060)));
	labelNumeroConta = new Label("Número Conta: ");
	labelNumeroConta.setFont(Font.font(CaixaView.METRICS.getPX(0.0050)));

	fieldNumeroConta = new TextField();
	labelSenhaConta = new Label("Senha: ");
	labelSenhaConta.setFont(Font.font(CaixaView.METRICS.getPX(0.0050)));

	fieldSenha = new PasswordField();
	botaoEntrar = new Button("Acessar");

	mainView.labelTituloBar.setText("Sistema Banco MS - Login");

	loginBox.getChildren().addAll(labelLoginTitulo, labelNumeroConta, fieldNumeroConta, labelSenhaConta, fieldSenha,
		botaoEntrar);

	botaoEntrar.setOnAction(new LoginController(mainView, this));

	group.getChildren().add(loginBox);
	getChildren().add(group);
	mainView.getChildren().add(this);

    }

}
