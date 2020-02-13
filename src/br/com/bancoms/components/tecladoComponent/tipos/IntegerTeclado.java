package br.com.bancoms.components.tecladoComponent.tipos;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Mateussilvasant - Classe que representa o tipo de tecladoView que
 * aceita somente numeros inteiros.
 */
public class IntegerTeclado extends Teclado {

    public IntegerTeclado(TextField fieldAlvo) {
        setLimiteCaracteres(10);
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
        try {
            setTextoField(getFieldVinculado().getText());
        } catch (NumberFormatException e) {
            throw new Exception("Campo vazio!");
        }
    }

    @Override
    protected void setFieldValor(String valor, String valorField) {
        getFieldVinculado().setText(valorField + valor);
    }

    @Override
    public boolean verificarValor() {
        return Integer.parseInt(getTextoField()) != 0;
    }

    @Override
    protected void limpar() {
        getFieldVinculado().setText("");
    }

}
