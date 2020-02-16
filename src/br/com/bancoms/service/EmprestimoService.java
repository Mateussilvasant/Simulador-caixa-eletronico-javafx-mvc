package br.com.bancoms.service;

import br.com.bancoms.dao.EmprestimoDAO;
import br.com.bancoms.model.contas.Conta;
import br.com.bancoms.model.Emprestimo;

import java.util.Optional;

public class EmprestimoService {
    private static EmprestimoService emprestimoService;
    private EmprestimoDAO emprestimoDAO;

    private EmprestimoService() {
        emprestimoDAO = new EmprestimoDAO();
    }

    public boolean cadastrarEmprestimo(Emprestimo emprestimo, int idMovimento) {
        return emprestimoDAO.cadastrarEmprestimo(emprestimo, idMovimento);
    }

    public Optional<Emprestimo> consultarEmprestimo(Conta conta) {
        return emprestimoDAO.consultarEmprestimo(conta);
    }

    public boolean atualizarEmprestimo(double valorEmprestado, int idConta) {
        return emprestimoDAO.atualizarEmprestimo(valorEmprestado, idConta);
    }

    public static EmprestimoService getInstance() {

        if (emprestimoService != null) {
            return emprestimoService;
        }

        emprestimoService = new EmprestimoService();

        return emprestimoService;

    }
}
