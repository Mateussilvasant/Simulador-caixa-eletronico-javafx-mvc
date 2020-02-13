package br.com.bancoms.components.tecladoComponent;

import br.com.bancoms.components.tecladoComponent.tipos.DoubleTeclado;
import br.com.bancoms.components.tecladoComponent.tipos.IntegerTeclado;
import br.com.bancoms.components.tecladoComponent.tipos.Teclado;
import javafx.scene.control.TextField;


/**
 * @author Mateussilvasant - Classe que faz a ponte entre a l�gica do teclado
 * at� a sua visualiza��o na UI.
 */
public class TecladoAdapter {

    public TecladoView tecladoView;
    private Teclado tecladoComponent;

    public TecladoAdapter() {
        tecladoView = new TecladoView(this);
    }

    public void anexarTeclado(Teclado.TecladoEvent tecladoEvent, TextField fieldExterno) {

        TextField field;

        if (fieldExterno == null) {
            field = tecladoView.numeroField;
            tecladoView.addFieldInterno();
        } else {
            field = fieldExterno;
            tecladoView.addFieldExterno();
        }

        if (tecladoEvent == Teclado.TecladoEvent.INTEGER) {
            field.setText("");
            tecladoComponent = new IntegerTeclado(field);
        } else {
            field.setText("0,00");
            tecladoComponent = new DoubleTeclado(field);
        }

        tecladoView.anexarEventos(this);

    }

    public Teclado getTeclado() {
        return tecladoComponent;
    }

}
