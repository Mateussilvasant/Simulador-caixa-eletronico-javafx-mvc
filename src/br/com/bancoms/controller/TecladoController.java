package br.com.bancoms.controller;

import br.com.bancoms.view.TecladoView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class TecladoController implements EventHandler<ActionEvent>
{

    public TecladoView tecladoView;
    private double valorOperacao;

    public TecladoController()
    {
	tecladoView = new TecladoView(this);
    }

    @Override
    public void handle(ActionEvent event)
    {

	Button botao = (Button) event.getSource();

	if (botao.getText().equals("Confirmar"))
	{
	    if (tecladoView.numeroField.getText().length() > 3)
	    {
		setValorOperacao(Double.parseDouble(tecladoView.numeroField.getText().replace(',', '.')));
	    }
	}
	if (botao.getText().equals("Limpar"))
	{
	    tecladoView.numeroField.setText("0,00");
	} else if (botao.getText().length() == 1)
	{

	    if (tecladoView.numeroField.getText().length() < 7)
	    {
		inserirValor(botao.getText(), tecladoView.numeroField.getText());
	    }

	}

    }

    private void inserirValor(String valorBotao, String valorField)
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

	tecladoView.numeroField.setText(novoValorField);
    }

    public double getValorOperacao()
    {
	return valorOperacao;
    }

    public void setValorOperacao(double valor)
    {
	this.valorOperacao = valor;
    }

}
