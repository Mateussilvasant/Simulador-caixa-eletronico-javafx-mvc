package br.com.bancoms.components.tecladoComponent.tipos;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Mateussilvasant - Classe que representa o tipo de tecladoView que
 * aceita somente numeros reais.
 */
public class DoubleTeclado extends Teclado {

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
                setTextoField(getFieldVinculado().getText().replace(",", "."));
            } catch (NumberFormatException e) {
                throw new Exception("Campo vazio!");
            }
        }

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
    public boolean verificarValor() throws NumberFormatException {
        return Double.parseDouble(getTextoField()) != 0.0;
    }

    @Override
    protected void limpar() {
        getFieldVinculado().setText("0,00");
    }

}
