package br.com.bancoms.util;

public class Validador {
    public static final int CAMPO_VAZIO = 1;
    public static final int CAMPO_NUMERAL_COM_PALAVRAS = 2;
    public static final int CAMPO_VALIDO = 3;

    public static class Valor<T> {
        public T valor;
        public int resposta;

        public Valor(T valor, int resposta) {
            this.valor = valor;
            this.resposta = resposta;
        }
    }

    public static Valor validarCampoNumero(String numeroCampo) {

        int valor;

        if (numeroCampo.isEmpty()) {
            return new Valor<>("", CAMPO_VAZIO);
        } else {
            try {
                valor = Integer.parseInt(numeroCampo);
            } catch (Exception e) {
                return new Valor<>("", CAMPO_NUMERAL_COM_PALAVRAS);
            }
        }

        return new Valor<>(valor, CAMPO_VALIDO);
    }

    public static int validarCampoTexto(String texto) {

        if (texto.isEmpty()) {
            return CAMPO_VAZIO;
        }

        return CAMPO_VALIDO;

    }
}
