package br.com.bancoms.service;

import br.com.bancoms.dao.ContaDAO;
import br.com.bancoms.model.Conta;

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

    public double consultarSaldo(Conta conta)
    {
	return contaDAO.consultarSaldo(conta);
    }

    public boolean atualizarSaldo(Conta conta)
    {
	return contaDAO.atualizarSaldo(conta);
    }

}
