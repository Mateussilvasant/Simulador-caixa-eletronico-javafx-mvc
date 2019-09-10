package br.com.bancoms.components;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Mateussilvasant - Classe que representa o tipo de tecladoView que
 * aceita somente numeros reais.
 */
public class DoubleTeclado extends Teclado {

    private static DoubleTeclado instance;
    private double valor;

    public DoubleTeclado(TextField fieldAlvo) {
        setLimiteCaracteres(7);
        setFieldVinculado(fieldAlvo);
    }

    @Override
    protected void inserirValor(Button botaoOrigem) {

        if (getFieldVinculado().getText().length() < getLimiteCaracteres()) {
            setFieldValor(botaoOrigem.getText(), getFieldVinculado().getText());
        }

    }

    @Override
    protected void entrar() throws Exception {

        if (getFieldVinculado().getText().length() > 3) {
            try {
                valor = Double.parseDouble(getFieldVinculado().getText().replace(",", "."));
            } catch (NumberFormatException e) {
                throw new Exception("Campo vazio!");
            }
        }

    }

    @Override
    protected void limpar() {
        getFieldVinculado().setText("0,00");
    }

    @Override
    protected void setFieldValor(String valor, String valorField) {
        if (valorField.charAt(0) == '0') {
            valorField = ",00";
        }

        String novoValorField = "";
        char[] vetor = valorField.toCharArray();

        for (int d = 0; novoValorField.isEmpty(); d++) {
            if (vetor[d] == ',') {
                novoValorField = valorField.substring(0, d) + valor + valorField.substring(d, vetor.length);
            }
        }

        getFieldVinculado().setText(novoValorField);
    }

    @Override
    public boolean verificarValor() {
        return valor != 0.0;
    }

    public double getValor() {
        return valor;
    }

}
