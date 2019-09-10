package br.com.bancoms.controller;

import br.com.bancoms.components.DoubleTeclado;
import br.com.bancoms.components.IntegerTeclado;
import br.com.bancoms.components.Teclado;
import br.com.bancoms.view.TecladoView;
import javafx.scene.control.TextField;


/**
 * @author Mateussilvasant - Classe que faz a ponte entre a l�gica do teclado
 * at� a sua visualiza��o na UI.
 */
public class TecladoController {

    public TecladoView tecladoView;
    private Teclado tecladoComponent;

    public TecladoController() {
        tecladoView = new TecladoView(this);
    }

    public void anexarTeclado(Teclado.Tipo tipo, TextField fieldExterno) {

        TextField field;

        if (fieldExterno == null) {
            field = tecladoView.numeroField;
            tecladoView.addFieldInterno();
        } else {
            field = fieldExterno;
            tecladoView.addFieldExterno(fieldExterno);
        }

        if (tipo == Teclado.Tipo.INTEGER) {
            tecladoComponent = new IntegerTeclado(field);
        } else {
            tecladoComponent = new DoubleTeclado(field);
        }

        tecladoView.anexarEventos(this);

    }

    public Teclado getTeclado() {
        return tecladoComponent;
    }

}
