package br.com.bancoms.components.tecladoComponent.tipos;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Mateussilvasant - Classe que encapsula a logica do TecladoView
 */
public abstract class Teclado implements EventHandler<ActionEvent> {

    public enum TecladoEvent {
        INTEGER, DOUBLE
    }

    private final String action_entrar = "Entrar";
    private final String action_limpar = "Limpar";

    private TextField fieldVinculado;
    protected int limiteCaracteres;
    private String textoField;

    abstract void inserirValor(Button botaoOrigem);

    abstract void entrar() throws Exception;

    abstract void limpar();

    abstract void setFieldValor(String valor, String valorField);

    public abstract boolean verificarValor();

    @Override
    public void handle(ActionEvent event) {
        eventos((Button) event.getSource());
    }

    private void eventos(Button botaoOrigem) {
        if (botaoOrigem.getText().equals(action_entrar)) {
            aceitarValor();
        } else if (botaoOrigem.getText().equals(action_limpar)) {
            limpar();
        } else {
            inserirValor(botaoOrigem);
        }
    }

    private void aceitarValor() {
        try {
            entrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void anexarEvento(Button botaoAlvo) {
        botaoAlvo.setOnAction(this);
    }

    protected TextField getFieldVinculado() {
        return fieldVinculado;
    }

    protected void setFieldVinculado(TextField fieldVinculado) {
        this.fieldVinculado = fieldVinculado;
    }

    protected int getLimiteCaracteres() {
        return limiteCaracteres;
    }

    protected void setLimiteCaracteres(int limiteCaracteres) {
        this.limiteCaracteres = limiteCaracteres;
    }

    public String getTextoField() {
        return textoField;
    }

    protected void setTextoField(String textoField) {
        this.textoField = textoField;
    }

}
