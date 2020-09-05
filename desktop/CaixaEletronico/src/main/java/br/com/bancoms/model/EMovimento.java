package br.com.bancoms.model;

public enum EMovimento {
    SAQUE(1, "Saque"), DEPOSITO(2, "Depósito"), TRANSFERENCIA(3, "Transferência");

    private int value;
    private String key;

    EMovimento(int value, String key) {
        this.value = value;
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}

