package br.com.bancoms.model;

public class Emprestimo {

    private double valorEmprestado;
    private double valorParcelado;
    private int parcelas;

    public Emprestimo(double valorEmprestado, double valorParcelado, int parcelas) {
        this.valorEmprestado = valorEmprestado;
        this.valorParcelado = valorParcelado;
        this.parcelas = parcelas;
    }

    public double getValorEmprestado() {
        return valorEmprestado;
    }

    public void setValorEmprestado(double valorEmprestado) {
        this.valorEmprestado = valorEmprestado;
    }

    public double getValorParcelado() {
        return valorParcelado;
    }

    public void setValorParcelado(double valorParcelado) {
        this.valorParcelado = valorParcelado;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }
}
