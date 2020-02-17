package br.com.bancoms.dto;

public class LoginDTO {

    private int numeroConta;
    private String senha;

    public LoginDTO(int numeroConta, String senha) {
        this.numeroConta = numeroConta;
        this.senha = senha;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
