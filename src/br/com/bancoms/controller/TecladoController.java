package br.com.bancoms.controller;

import br.com.bancoms.view.TecladoView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TecladoController implements EventHandler<ActionEvent>
{

    enum Tipo
    {
	INTEGER, DOUBLE;
    }

    public TecladoView tecladoView;
    private Object valorOperacao;
    private TextField field;
    private int limiteCaracter;
    private Tipo tipoTeclado;

    public TecladoController(int limiteCaracter, Tipo tipoTeclado)
    {
	this.limiteCaracter = limiteCaracter;
	this.tipoTeclado = tipoTeclado;

	tecladoView = new TecladoView(this);
	addFieldInterno();
    }

    public TecladoController(Tipo tipoTeclado)
    {
	this.tipoTeclado = tipoTeclado;
	tecladoView = new TecladoView(this);
	addFieldInterno();
    }

    @Override
    public void handle(ActionEvent event)
    {

	if (tipoTeclado == Tipo.DOUBLE)
	{
	    onDoubleEvent(event);
	} else if (tipoTeclado == Tipo.INTEGER)
	{
	    onIntegerEvent(event);
	}

    }

    private void onIntegerEvent(ActionEvent event)
    {

	Button botao = (Button) event.getSource();

	if (botao.getText().equals("Entrar"))
	{
	    setValorOperacao(Integer.parseInt(field.getText()));
	}
	if (botao.getText().equals("Limpar"))
	{
	    field.setText("");
	} else if (botao.getText().length() == 1)
	{

	    if (field.getText().length() < limiteCaracter)
	    {
		inserirValorInteiro(botao.getText(), field.getText());
	    }

	}
    }

    private void onDoubleEvent(ActionEvent event)
    {
	Button botao = (Button) event.getSource();

	if (botao.getText().equals("Entrar"))
	{
	    if (field.getText().length() > 3)
	    {
		setValorOperacao(Double.parseDouble(field.getText().replace(',', '.')));
	    }
	}
	if (botao.getText().equals("Limpar"))
	{
	    field.setText("0,00");
	} else if (botao.getText().length() == 1)
	{

	    if (field.getText().length() < 7)
	    {
		inserirValorDouble(botao.getText(), field.getText());
	    }

	}

    }

    public boolean verificarConfirmacao()
    {

	if (valorOperacao != null)
	{
	    return true;

	} else
	{
	    return false;
	}
    }

    private void inserirValorInteiro(String valorBotao, String valorField)
    {
	field.setText(valorField + valorBotao);
    }

    private void inserirValorDouble(String valorBotao, String valorField)
    {

	if (valorField.charAt(0) == '0')
	{
	    valorField = ",00";
	}

	String novoValorField = "";
	char vetor[] = valorField.toCharArray();

	for (int d = 0; novoValorField.isEmpty(); d++)
	{
	    if (vetor[d] == ',')
	    {
		novoValorField = valorField.substring(0, d) + valorBotao + valorField.substring(d, vetor.length);
	    }
	}

	field.setText(novoValorField);
    }

    public Object getValorOperacao()
    {
	return valorOperacao;
    }

    public void setValorOperacao(Object valor)
    {
	valorOperacao = valor;
    }

    public void addFieldExterno(TextField fieldExterno)
    {
	field = fieldExterno;
	tecladoView.vboxView.getChildren().remove(tecladoView.numeroField);
    }

    public void addFieldInterno()
    {

	if (!tecladoView.vboxView.getChildren().contains(tecladoView.numeroField))
	{
	    tecladoView.vboxView.getChildren().add(0, tecladoView.numeroField);
	}

	field = tecladoView.numeroField;
    }

    public void mudarParaInteiro(int limiteCaracter)
    {
	if (tipoTeclado == Tipo.DOUBLE)
	{
	    this.limiteCaracter = limiteCaracter;
	    tipoTeclado = Tipo.INTEGER;
	}
    }

    public void mudarParaDouble()
    {
	if (tipoTeclado == Tipo.INTEGER)
	{
	    tipoTeclado = Tipo.DOUBLE;
	}
    }
}
