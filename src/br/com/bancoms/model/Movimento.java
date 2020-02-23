package br.com.bancoms.model;

public class Movimento {

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


    private int idConta;
    private double valor;
    private int tipo;
    private String descricao;
    private String data;
    private int numeroContaOrigem;
    private int numeroContaDestino;

    public Movimento(int idConta, double valor, int tipo, String descricao, int numeroContaOrigem, int numeroContaDestino) {
        this.idConta = idConta;
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.numeroContaOrigem = numeroContaOrigem;
        this.numeroContaDestino = numeroContaDestino;
    }

    public Movimento(double valor, String descricao, int tipo, String data, int numeroContaOrigem, int numeroContaDestino) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.numeroContaOrigem = numeroContaOrigem;
        this.numeroContaDestino = numeroContaDestino;
        this.data = data;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getNumeroContaOrigem() {
        return numeroContaOrigem;
    }

    public void setNumeroContaOrigem(int numeroContaOrigem) {
        this.numeroContaOrigem = numeroContaOrigem;
    }

    public int getNumeroContaDestino() {
        return numeroContaDestino;
    }

    public void setNumeroContaDestino(int numeroContaDestino) {
        this.numeroContaDestino = numeroContaDestino;
    }

    public int getIdConta() {
        return this.idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    @Override
    public String toString() {
        return "Movimento{" +
                "idConta=" + idConta +
                ", valor=" + valor +
                ", tipo=" + tipo +
                ", descricao='" + descricao + '\'' +
                ", data='" + data + '\'' +
                ", numeroContaOrigem=" + numeroContaOrigem +
                ", numeroContaDestino=" + numeroContaDestino +
                '}';
    }
}
