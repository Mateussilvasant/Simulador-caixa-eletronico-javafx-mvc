package br.com.bancoms.dto;

import br.com.bancoms.model.contas.Conta;

public class TransacaoDTO {

    private Conta contaOrigem;
    private int numeroContaDestino;
    private double valorTransacao;

    public TransacaoDTO(Conta contaOrigem, int numeroContaDestino, double valorTransacao) {
        this.contaOrigem = contaOrigem;
        this.numeroContaDestino = numeroContaDestino;
        this.valorTransacao = valorTransacao;
    }

    public TransacaoDTO(Conta contaOrigem, double valorTransacao) {
        this.contaOrigem = contaOrigem;
        this.valorTransacao = valorTransacao;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public int getNumeroContaDestino() {
        return numeroContaDestino;
    }

    public void setNumeroContaDestino(int numeroContaDestino) {
        this.numeroContaDestino = numeroContaDestino;
    }

    public double getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(double valorTransacao) {
        this.valorTransacao = valorTransacao;
    }
}
