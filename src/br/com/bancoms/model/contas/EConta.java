package br.com.bancoms.model.contas;

public enum EConta {
    INVESTIMENTO(0), CORRENTE(1), POUPANCA(2);

    private int value;

    EConta(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
