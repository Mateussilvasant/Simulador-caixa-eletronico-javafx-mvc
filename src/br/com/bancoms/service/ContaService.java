package br.com.bancoms.service;

import br.com.bancoms.dao.ContaDAO;
import br.com.bancoms.model.Conta;
import br.com.bancoms.vo.ClienteVO;

public class ContaService
{
    private static ContaService contaService;
    private ContaDAO contaDAO;

    public ContaService()
    {
	contaDAO = new ContaDAO();
    }

    public static ContaService getInstance()
    {

	if (contaService != null)
	{
	    return contaService;
	}

	contaService = new ContaService();

	return contaService;

    }

    public Conta consultarConta(ClienteVO clienteVO)
    {
	return contaDAO.consultarConta(clienteVO);
    }

    public boolean atualizarSaldo(Conta conta)
    {
	return contaDAO.atualizarSaldo(conta);
    }

    public void depositar(Conta conta, double valorOperacao)
    {
	conta.depositar(valorOperacao);
    }

    public boolean sacar(Conta contaSessao, double valorOperacao)
    {
	return contaSessao.sacar(valorOperacao);
    }

}
