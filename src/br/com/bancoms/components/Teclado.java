package br.com.bancoms.components;


import br.com.bancoms.view.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Mateussilvasant - Classe que encapsula a logica do TecladoView
 */
public abstract class Teclado implements EventHandler<ActionEvent> {
    public enum Tipo {
        INTEGER, DOUBLE
    }

    private final String action_entrar = "Entrar";
    private final String action_limpar = "Limpar";

    private TextField fieldVinculado;
    private int limiteCaracteres;

    @Override
    public void handle(ActionEvent event) {
        Button botaoOrigem = (Button) event.getSource();

        if (botaoOrigem.getText().equals(action_entrar)) {
            try {
                entrar();
            } catch (Exception e) {
                MainView.getAlert("Erro",
                        e.getMessage(), Alert.AlertType.INFORMATION).show();
            }

        } else if (botaoOrigem.getText().equals(action_limpar)) {
            limpar();

        } else {
            inserirValor(botaoOrigem);
        }

    }

    public void anexarEvento(Button botaoAlvo) {
        botaoAlvo.setOnAction(this);
    }

    protected abstract void inserirValor(Button botaoOrigem);

    protected abstract void entrar() throws Exception;

    protected abstract void setFieldValor(String valor, String valorField);

    protected abstract void limpar();

    public abstract boolean verificarValor();

    TextField getFieldVinculado() {
        return fieldVinculado;

    }

    void setFieldVinculado(TextField fieldVinculado) {
        this.fieldVinculado = fieldVinculado;
    }

    int getLimiteCaracteres() {
        return limiteCaracteres;
    }

    void setLimiteCaracteres(int limiteCaracteres) {
        this.limiteCaracteres = limiteCaracteres;
    }
}
